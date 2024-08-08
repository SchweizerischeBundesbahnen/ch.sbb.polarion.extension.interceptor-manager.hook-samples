# Hook samples for Polarion Interceptor Manager extension

This project contains samples of hooks for Polarion [Interceptor Manager extension](https://github.com/SchweizerischeBundesbahnen/ch.sbb.polarion.extension.interceptor-manager).

The following hooks are available:
- [Delete Non-resolved Module Comments](hook-samples/delete-non-resolved-module-comments/README.md)
- [Delete Work Records](hook-samples/delete-work-records/README.md)
- [Module Save Time Logger](hook-samples/module-save-time-logger/README.md)
- [Only Assignee Can Delete](hook-samples/only-assignee-can-delete/README.md)
- [Plan Save](hook-samples/plan-save/README.md)
- [Single Assignee](hook-samples/single-assignee/README.md)
- [TestRun](hook-samples/testrun/README.md)
- [Title Length Check](hook-samples/title-length-check/README.md)

## Build

These hooks can be produced using maven:
```bash
mvn clean package
```

## Installation to Polarion

To install these hooks to Polarion `ch.sbb.polarion.extension.interceptor-manager.hook-samples.<hookname>-<version>.jar` files should be copied to `<polarion_home>/polarion/extensions/ch.sbb.polarion.extension.interceptor-manager/eclipse/plugins/hooks`.
It can be done manually or automated using maven build:

```bash
mvn clean install -P install-to-local-polarion
```

For automated installation with maven env variable `POLARION_HOME` should be defined and point to folder where Polarion is installed.
