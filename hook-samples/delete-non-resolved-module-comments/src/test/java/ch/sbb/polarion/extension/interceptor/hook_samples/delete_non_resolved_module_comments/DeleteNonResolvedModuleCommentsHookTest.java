package ch.sbb.polarion.extension.interceptor.hook_samples.delete_non_resolved_module_comments;

import ch.sbb.polarion.extension.interceptor_manager.settings.HookModel;
import ch.sbb.polarion.extension.interceptor_manager.util.HookManifestUtils;
import ch.sbb.polarion.extension.interceptor_manager.util.SettingEntriesValidator;
import com.polarion.alm.tracker.model.IModuleComment;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteNonResolvedModuleCommentsHookTest {

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
        DeleteNonResolvedModuleCommentsHook hook = new DeleteNonResolvedModuleCommentsHook();

        assertEquals(List.of(), SettingEntriesValidator.validateForSave(hook, defaultSettings(hook)));
    }

    @Test
    void aResolvedCommentIsRefusedWithTheConfiguredMessage() {
        DeleteNonResolvedModuleCommentsHook hook = hookWith("CommentMessage=configured message");
        IModuleComment comment = mock(IModuleComment.class);
        when(comment.isResolvedComment()).thenReturn(true);

        assertEquals("configured message", hook.getExecutor().preAction(comment));
    }

    @Test
    void anUnresolvedCommentIsLetThrough() {
        DeleteNonResolvedModuleCommentsHook hook = hookWith("CommentMessage=configured message");
        IModuleComment comment = mock(IModuleComment.class);
        when(comment.isResolvedComment()).thenReturn(false);

        assertNull(hook.getExecutor().preAction(comment));
    }

    private DeleteNonResolvedModuleCommentsHook hookWith(String properties) {
        DeleteNonResolvedModuleCommentsHook hook = new DeleteNonResolvedModuleCommentsHook();
        hook.setSettings(new HookModel(true, "1.0.0", properties));
        return hook;
    }

    private HookModel defaultSettings(DeleteNonResolvedModuleCommentsHook hook) {
        return new HookModel(true, "1.0.0", hook.getDefaultSettings());
    }
}
