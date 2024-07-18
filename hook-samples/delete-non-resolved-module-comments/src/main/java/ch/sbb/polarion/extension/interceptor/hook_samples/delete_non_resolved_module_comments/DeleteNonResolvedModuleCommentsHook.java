package ch.sbb.polarion.extension.interceptor.hook_samples.delete_non_resolved_module_comments;

import ch.sbb.polarion.extension.interceptor.model.ActionHook;
import ch.sbb.polarion.extension.interceptor.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor.util.PropertiesUtils;
import com.polarion.alm.tracker.model.IModuleComment;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DeleteNonResolvedModuleCommentsHook extends ActionHook implements HookExecutor {

    public static final String DESCRIPTION = "Allow the removal of only unresolved comments from the document.";

    public static final Logger logger = Logger.getLogger(DeleteNonResolvedModuleCommentsHook.class);

    public DeleteNonResolvedModuleCommentsHook() {
        super(ItemType.MODULE_COMMENT, ActionType.DELETE, DESCRIPTION);
    }

    @Override
    public String preAction(@NotNull IPObject object) {
        IModuleComment moduleComment = (IModuleComment) object;

        if (moduleComment.isResolvedComment()) {
            return "'Resolved' comments can not be deleted.";
        } else {
            return null;
        }
    }

    @Override
    public @NotNull HookExecutor getExecutor() {
        return this; //there is no need to create a separate executor instance coz only 'pre' action used
    }

    @Override
    public String getDefaultSettings() {
        return PropertiesUtils.build();
    }
}
