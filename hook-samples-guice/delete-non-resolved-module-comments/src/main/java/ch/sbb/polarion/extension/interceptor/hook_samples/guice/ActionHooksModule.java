package ch.sbb.polarion.extension.interceptor.hook_samples.guice;

import ch.sbb.polarion.extension.interceptor_manager.model.IActionHook;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.polarion.core.util.logging.Logger;

public class ActionHooksModule extends AbstractModule {
    public static final Logger logger = Logger.getLogger(ActionHooksModule.class);

    @Override
    protected void configure() {
        Multibinder.newSetBinder(this.binder(), IActionHook.class).addBinding().toInstance(new DeleteNonResolvedModuleCommentsHookJuice());
        logger.info("Registered DeleteNonResolvedModuleCommentsHookJuice in Google Guice module");
    }
}
