[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=bugs)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=SchweizerischeBundesbahnen_ch.sbb.polarion.extension.interceptor.hook-samples)

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

## Quick start

The latest version of the hooks can be downloaded from the [releases page](../../releases/latest) and installed to Polarion instance without necessity to be compiled from the sources.
Required hook(s) jar(s) should be copied to `<polarion_home>/polarion/extensions/ch.sbb.polarion.extension.interceptor-manager/eclipse/plugins/hooks` folder.

As jars copied please go to Interceptor Manager -> Settings page on admin pane and click "Reload hooks list" link in top-right corner. Now installed sample hooks are ready to be used.

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
