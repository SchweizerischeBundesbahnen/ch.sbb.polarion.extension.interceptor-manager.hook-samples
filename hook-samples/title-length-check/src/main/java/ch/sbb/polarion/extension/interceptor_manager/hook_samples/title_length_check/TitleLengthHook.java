package ch.sbb.polarion.extension.interceptor_manager.hook_samples.title_length_check;

import ch.sbb.polarion.extension.interceptor_manager.model.ActionHook;
import ch.sbb.polarion.extension.interceptor_manager.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor_manager.util.PropertiesUtils;
import com.polarion.alm.tracker.model.IModule;
import com.polarion.alm.tracker.model.IWorkItem;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class TitleLengthHook extends ActionHook implements HookExecutor {

    private static final String SETTINGS_ERROR_MESSAGE = "errorMessage";
    private static final String SETTINGS_MAX_LENGTH = "titleMaxLength";
    private static final String MAX_LENGTH_VARIABLE = "{%s}".formatted(SETTINGS_MAX_LENGTH);
    private static final String DEFAULT_ERROR_MESSAGE = "Title length is over the limit (" + MAX_LENGTH_VARIABLE + " symbols). Please correct it before saving";
    private static final int DEFAULT_MAX_LENGTH = 256;

    public static final String DESCRIPTION = "Validates title length.";

    private static final Logger logger = Logger.getLogger(TitleLengthHook.class);

    public TitleLengthHook() {
        super(List.of(ItemType.WORKITEM, ItemType.MODULE), ActionType.SAVE, DESCRIPTION);
    }

    @Override
    public String preAction(@NotNull IPObject object) {
        int maxLength = getMaxLength();

        @Nullable String title = null;
        if (object instanceof IWorkItem workItem) {
            title = workItem.getTitle();
        } else if (object instanceof IModule module) {
            title = module.getTitle();
        }

        return title != null && title.length() > maxLength ? createErrorMessage(maxLength) : null;
    }

    private @NotNull String createErrorMessage(int maxLength) {
        return getSettingsValue(SETTINGS_ERROR_MESSAGE)
                .replace(MAX_LENGTH_VARIABLE, String.valueOf(maxLength));
    }

    private int getMaxLength() {
        int maxLength = DEFAULT_MAX_LENGTH;
        String maxLengthStringValue = getSettingsValue(SETTINGS_MAX_LENGTH);
        try {
            maxLength = Integer.parseInt(maxLengthStringValue);
        } catch (NumberFormatException e) {
            logger.error("Cannot parse max length value '%s'".formatted(maxLengthStringValue), e);
        }
        return maxLength;
    }

    @Override
    public @NotNull HookExecutor getExecutor() {
        return this; //there is no need to create a separate executor instance coz only 'pre' action used
    }

    @Override
    public String getDefaultSettings() {
        return PropertiesUtils.build(
                SETTINGS_ERROR_MESSAGE, DEFAULT_ERROR_MESSAGE,
                SETTINGS_MAX_LENGTH, String.valueOf(DEFAULT_MAX_LENGTH)
        );
    }
}
