package ch.sbb.polarion.extension.interceptor.hook_samples.osgi;

import ch.sbb.polarion.extension.interceptor_manager.model.ActionHook;
import ch.sbb.polarion.extension.interceptor_manager.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor_manager.util.PropertiesUtils;
import com.polarion.alm.tracker.model.IModuleComment;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class DeleteNonResolvedModuleCommentsHook extends ActionHook implements HookExecutor {
    private static final Logger logger = Logger.getLogger(DeleteNonResolvedModuleCommentsHook.class);

    private static final String DESCRIPTION = "Allow the removal of only unresolved comments from the document. Loaded using OSGi services";
    private static final String RESOLVED_CANNOT_BE_DELETED = "'Resolved' comments can not be deleted.";

    public DeleteNonResolvedModuleCommentsHook() {
        super(ItemType.MODULE_COMMENT, ActionType.DELETE, DESCRIPTION);
    }

    @Override
    public String preAction(@NotNull IPObject object) {
        if (((IModuleComment) object).isResolvedComment()) {
            return RESOLVED_CANNOT_BE_DELETED;
        } else {
            return null;
        }
    }

    @Override
    public String getDefaultSettings() {
        return PropertiesUtils.build();
    }

    @Override
    public @NotNull HookExecutor getExecutor() {
        return this;
    }
}
