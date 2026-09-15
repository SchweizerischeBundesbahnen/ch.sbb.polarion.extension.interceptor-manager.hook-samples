package ch.sbb.polarion.extension.interceptor_manager.hook_samples.plan_save;

import ch.sbb.polarion.extension.interceptor_manager.settings.HookModel;
import ch.sbb.polarion.extension.interceptor_manager.util.HookManifestUtils;
import ch.sbb.polarion.extension.interceptor_manager.util.SettingEntriesValidator;
import com.polarion.core.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PlanSaveHookTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    MockedStatic<Logger> loggerMockedStatic;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    MockedStatic<HookManifestUtils> hookManifestUtilsMockedStatic;

    @BeforeEach
    void setUp() {
        hookManifestUtilsMockedStatic.when(() -> HookManifestUtils.getHookVersion(any())).thenReturn("1.0.0");
    }

    @Test
    void defaultSettingsSatisfyTheDeclaration() {
        // An entry the hook requires but does not default would make a freshly installed hook unsavable.
        PlanSaveHook hook = new PlanSaveHook();

        assertEquals(List.of(), SettingEntriesValidator.validateForSave(hook, new HookModel(true, "1.0.0", hook.getDefaultSettings())));
    }
}
