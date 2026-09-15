package ch.sbb.polarion.extension.interceptor.hook_samples.osgi;

import ch.sbb.polarion.extension.interceptor_manager.settings.HookModel;
import ch.sbb.polarion.extension.interceptor_manager.util.SettingEntriesValidator;
import com.polarion.alm.tracker.model.IModuleComment;
import com.polarion.core.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteNonResolvedModuleCommentsHookOSGiTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    MockedStatic<Logger> loggerMockedStatic;

    @Test
    void defaultSettingsSatisfyTheDeclaration() {
        // An entry the hook requires but does not default would make a freshly installed hook unsavable.
        DeleteNonResolvedModuleCommentsHookOSGi hook = new DeleteNonResolvedModuleCommentsHookOSGi();

        assertEquals(List.of(), SettingEntriesValidator.validateForSave(hook, new HookModel(true, "1.0.0", hook.getDefaultSettings())));
    }

    @Test
    void aResolvedCommentIsRefusedWithTheConfiguredMessage() {
        DeleteNonResolvedModuleCommentsHookOSGi hook = hookWith("CommentMessage=configured message");
        IModuleComment comment = mock(IModuleComment.class);
        when(comment.isResolvedComment()).thenReturn(true);

        assertEquals("configured message", hook.getExecutor().preAction(comment));
    }

    @Test
    void anUnresolvedCommentIsLetThrough() {
        DeleteNonResolvedModuleCommentsHookOSGi hook = hookWith("CommentMessage=configured message");
        IModuleComment comment = mock(IModuleComment.class);
        when(comment.isResolvedComment()).thenReturn(false);

        assertNull(hook.getExecutor().preAction(comment));
    }

    private DeleteNonResolvedModuleCommentsHookOSGi hookWith(String properties) {
        DeleteNonResolvedModuleCommentsHookOSGi hook = new DeleteNonResolvedModuleCommentsHookOSGi();
        hook.setSettings(new HookModel(true, "1.0.0", properties));
        return hook;
    }
}
