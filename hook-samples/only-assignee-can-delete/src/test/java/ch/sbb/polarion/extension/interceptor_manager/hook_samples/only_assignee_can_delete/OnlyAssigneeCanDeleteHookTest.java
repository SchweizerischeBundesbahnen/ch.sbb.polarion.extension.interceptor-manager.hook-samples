package ch.sbb.polarion.extension.interceptor_manager.hook_samples.only_assignee_can_delete;

import ch.sbb.polarion.extension.interceptor_manager.settings.HookModel;
import ch.sbb.polarion.extension.interceptor_manager.util.HookManifestUtils;
import ch.sbb.polarion.extension.interceptor_manager.util.SettingEntriesValidator;
import com.polarion.core.util.logging.Logger;
import com.polarion.platform.core.PlatformContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class OnlyAssigneeCanDeleteHookTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    MockedStatic<Logger> loggerMockedStatic;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    MockedStatic<HookManifestUtils> hookManifestUtilsMockedStatic;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    MockedStatic<PlatformContext> platformContextMockedStatic;

    @BeforeEach
    void setUp() {
        hookManifestUtilsMockedStatic.when(() -> HookManifestUtils.getHookVersion(any())).thenReturn("1.0.0");
    }

    @Test
    void defaultSettingsSatisfyTheDeclaration() {
        // An entry the hook requires but does not default would make a freshly installed hook unsavable.
        OnlyAssigneeCanDeleteHook hook = new OnlyAssigneeCanDeleteHook();

        assertEquals(List.of(), SettingEntriesValidator.validateForSave(hook, new HookModel(true, "1.0.0", hook.getDefaultSettings())));
    }

    @Test
    void deleteUnassignedIsReadFromTheSettings() {
        assertTrue(hookWithDeleteUnassigned("deleteUnassigned=true").isDeleteUnassignedAllowed());
        assertFalse(hookWithDeleteUnassigned("deleteUnassigned=FALSE").isDeleteUnassignedAllowed());
    }

    @Test
    void invalidOrAbsentDeleteUnassignedKeepsTheDefault() {
        // Boolean.parseBoolean would have turned an invalid value into false.
        assertTrue(hookWithDeleteUnassigned("deleteUnassigned=maybe").isDeleteUnassignedAllowed());
        assertTrue(hookWithDeleteUnassigned("").isDeleteUnassignedAllowed());
    }

    private static OnlyAssigneeCanDeleteHook hookWithDeleteUnassigned(String deleteUnassignedEntry) {
        OnlyAssigneeCanDeleteHook hook = spy(new OnlyAssigneeCanDeleteHook());
        String properties = String.join("\n", "projects=*", deleteUnassignedEntry);
        doReturn(new HookModel(true, "1.0.0", properties)).when(hook).loadSettings(anyBoolean());
        return hook;
    }
}
