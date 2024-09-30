# Hook in Custom Polarion Extension

This hook allows for the removal of unresolved comments from the document only. The example demonstrates the technique for registering a hook in a custom Polarion extension. In this case, the hook implementation should be registered as Guice Module and will be discovered and injected in the interceptor-manager.
To register a hook as Guice Module in any Polarion extension, follow these steps:
1. Add the dependency com.google.inject/guice in your Maven or Gradle build with the provided scope.
2. Create a ActionHookModule class that implements the com.google.inject.AbstractModule interface. This class must add binding to the hook implementation class using IActionHook interface. See [HookBundleActivator.java](https://github.com/SchweizerischeBundesbahnen/ch.sbb.polarion.extension.interceptor-manager.hook-samples/blob/main/hook-samples-juice/delete-non-resolved-module-comments/src/main/java/ch/sbb/polarion/extension/interceptor/hook_samples/guice/ActionHooksModule.java) for details.
3. Specify your module class including java package in the following entry to MANIFEST.MF:
`   Guice-Modules: <MODULE_CLASS_FULLY_QUALIFIED_NAME>
`
