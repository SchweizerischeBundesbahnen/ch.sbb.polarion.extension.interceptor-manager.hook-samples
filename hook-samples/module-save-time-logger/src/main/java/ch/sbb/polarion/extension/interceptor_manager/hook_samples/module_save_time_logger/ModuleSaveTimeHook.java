package ch.sbb.polarion.extension.interceptor_manager.hook_samples.module_save_time_logger;

import ch.sbb.polarion.extension.interceptor_manager.model.ActionHook;
import ch.sbb.polarion.extension.interceptor_manager.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor_manager.util.PropertiesUtils;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ModuleSaveTimeHook extends ActionHook implements HookExecutor {

    public static final String SETTINGS_LOG_MESSAGE = "logMessage";
    public static final String SETTINGS_TIME = "time";
    public static final String TIME_VARIABLE = "{%s}".formatted(SETTINGS_TIME);
    public static final String DEFAULT_LOG_MESSAGE = "Module saved in " + TIME_VARIABLE + "ms";

    public static final String DESCRIPTION = "Logs module save time.";

    public static final Logger logger = Logger.getLogger(ModuleSaveTimeHook.class);

    public ModuleSaveTimeHook() {
        super(ItemType.MODULE, ActionType.SAVE, DESCRIPTION);
    }

    @Override
    public @NotNull HookExecutor getExecutor() {
        return new SaveTimeLoggerExecutor();
    }

    @Override
    public String getDefaultSettings() {
        return PropertiesUtils.build(
                SETTINGS_LOG_MESSAGE, DEFAULT_LOG_MESSAGE
        );
    }

    public class SaveTimeLoggerExecutor implements HookExecutor {

        private long startTime;

        @Override
        public String preAction(@NotNull IPObject polarionObject) {
            startTime = System.currentTimeMillis();
            return null;
        }

        @Override
        public void postAction(@NotNull IPObject polarionObject) {
            logger.info(getSettingsValue(SETTINGS_LOG_MESSAGE)
                    .replace(TIME_VARIABLE, String.valueOf(System.currentTimeMillis() - startTime)));
        }
    }
}
