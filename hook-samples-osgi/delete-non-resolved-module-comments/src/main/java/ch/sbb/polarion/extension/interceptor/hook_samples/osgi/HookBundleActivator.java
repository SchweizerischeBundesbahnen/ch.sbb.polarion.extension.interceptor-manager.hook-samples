package ch.sbb.polarion.extension.interceptor.hook_samples.osgi;

import ch.sbb.polarion.extension.interceptor_manager.model.IActionHook;
import com.polarion.core.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

public class HookBundleActivator implements BundleActivator {
    private static final Logger logger = Logger.getLogger(HookBundleActivator.class);

    private ServiceRegistration<IActionHook> actionHookRegistration;

    @Override
    public void start(BundleContext context) {
        logger.info("Registering action hooks services.");
        Hashtable<String, Object> keys = new Hashtable<>();
        keys.put("hook-name", "delete-non-resolved-module-comments");

        actionHookRegistration = context.registerService(
                IActionHook.class,
                new DeleteNonResolvedModuleCommentsOSGiHook(),
                keys);

        logger.info("Sample action hook service have been registered.");
    }

    @Override
    public void stop(BundleContext context) {
        logger.info("Unregistering action hook service.");

       actionHookRegistration.unregister();

        logger.info("Action hook service have been unregistered.");
    }
}