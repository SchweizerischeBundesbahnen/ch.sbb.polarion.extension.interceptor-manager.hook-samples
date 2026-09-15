package ch.sbb.polarion.extension.interceptor.hook_samples.delete_work_records;

import ch.sbb.polarion.extension.interceptor_manager.settings.HookModel;
import ch.sbb.polarion.extension.interceptor_manager.util.HookManifestUtils;
import ch.sbb.polarion.extension.interceptor_manager.util.SettingEntriesValidator;
import com.polarion.alm.tracker.model.IWorkRecord;
import com.polarion.core.util.logging.Logger;
import com.polarion.core.util.types.DateOnly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteWorkRecordsHookTest {

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
        DeleteWorkRecordsHook hook = new DeleteWorkRecordsHook();

        assertEquals(List.of(), SettingEntriesValidator.validateForSave(hook, new HookModel(true, "1.0.0", hook.getDefaultSettings())));
    }

    @Test
    void aRecordFromAnEarlierMonthIsRefusedWithTheConfiguredMessage() {
        DeleteWorkRecordsHook hook = hookWith("errorMessage=configured message");

        assertEquals("configured message", hook.getExecutor().preAction(workRecordMonthsAgo(2)));
    }

    @Test
    void aRecordFromTheCurrentMonthIsLetThrough() {
        DeleteWorkRecordsHook hook = hookWith("errorMessage=configured message");

        assertNull(hook.getExecutor().preAction(workRecordMonthsAgo(0)));
    }

    private DeleteWorkRecordsHook hookWith(String properties) {
        DeleteWorkRecordsHook hook = new DeleteWorkRecordsHook();
        hook.setSettings(new HookModel(true, "1.0.0", properties));
        return hook;
    }

    private IWorkRecord workRecordMonthsAgo(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -months);

        IWorkRecord workRecord = mock(IWorkRecord.class);
        when(workRecord.getDate()).thenReturn(new DateOnly(calendar.getTime()));
        return workRecord;
    }
}
