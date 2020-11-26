package com.chidra.presence;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.chidra.presence");

        noClasses()
            .that()
                .resideInAnyPackage("com.chidra.presence.service..")
            .or()
                .resideInAnyPackage("com.chidra.presence.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.chidra.presence.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
