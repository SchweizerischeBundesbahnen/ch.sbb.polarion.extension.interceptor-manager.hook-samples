package ch.sbb.polarion.extension.interceptor.hook_samples.juice;

import ch.sbb.polarion.extension.interceptor_manager.model.ActionHook;
import ch.sbb.polarion.extension.interceptor_manager.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor_manager.util.PropertiesUtils;
import com.polarion.alm.tracker.model.IModuleComment;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DeleteNonResolvedModuleCommentsHookJuice extends ActionHook implements HookExecutor {
    private static final Logger logger = Logger.getLogger(DeleteNonResolvedModuleCommentsHookJuice.class);

    private static final String JUICE_HOOK_DESCRIPTION = "Allow the removal of only unresolved comments from the document. Loaded using Google Guice";
    private static final String MESSAGE_TITLE = "CommentMessage";
    private static final String CANNOT_BE_DELETED_MESSAGE = "'Resolved' comments can not be deleted.";
    private static final String JOICE_HOOK_VERSION = "0.0.1";

    public DeleteNonResolvedModuleCommentsHookJuice() {
        super(ItemType.MODULE_COMMENT, ActionType.DELETE, JOICE_HOOK_VERSION, JUICE_HOOK_DESCRIPTION);
    }

    @Override
    public String preAction(@NotNull IPObject object) {
        return (((IModuleComment) object).isResolvedComment()) ? CANNOT_BE_DELETED_MESSAGE : null;
    }

    @Override
    public String getDefaultSettings() {
        return PropertiesUtils.build(MESSAGE_TITLE, CANNOT_BE_DELETED_MESSAGE);
    }

    @Override
    public @NotNull HookExecutor getExecutor() {
        return this;
    }
}
