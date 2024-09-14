package ch.sbb.polarion.extension.interceptor.hook_samples.osgi;

import ch.sbb.polarion.extension.interceptor_manager.model.ActionHook;
import ch.sbb.polarion.extension.interceptor_manager.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor_manager.util.PropertiesUtils;
import com.polarion.alm.tracker.model.IModuleComment;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DeleteNonResolvedModuleCommentsOSGiHook extends ActionHook implements HookExecutor {

    public static final String DESCRIPTION = "Allow the removal of only unresolved comments from the document. Loaded using OSGi services";

    public static final Logger logger = Logger.getLogger(DeleteNonResolvedModuleCommentsOSGiHook.class);

    public DeleteNonResolvedModuleCommentsOSGiHook() {
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
