# Hook from Another Polarion Extension

This hook allows for the removal of unresolved comments from the document only. The example demonstrates the technique for registering a hook in a different Polarion extension. In this case, the hook implementation should be registered as an OSGi service and will be discovered by the interceptor-manager.
To register a hook as an OSGi service in any Polarion extension, follow these steps:
1. Add the dependency org.osgi/org.osgi.framework in your Maven or Gradle build with the provided scope.
2. Create a BundleActivator class that implements the org.osgi.framework.BundleActivator interface. This class must register the hook implementation as an OSGi service under the IActionHook interface. See HookBundleActivator.java for details.
3. Add the following three entries to MANIFEST.MF:
`   Bundle-Activator: <BUNDLE_ACTIVATOR_CLASS>
   Bundle-ActivationPolicy: lazy
   Import-Package: org.osgi.framework
`
