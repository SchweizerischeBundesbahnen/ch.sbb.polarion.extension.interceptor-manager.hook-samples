package ch.sbb.polarion.extension.interceptor.hook_samples.delete_work_records;

import ch.sbb.polarion.extension.interceptor_manager.model.ActionHook;
import ch.sbb.polarion.extension.interceptor_manager.model.HookExecutor;
import ch.sbb.polarion.extension.interceptor_manager.util.PropertiesUtils;
import com.polarion.alm.tracker.model.IWorkRecord;
import com.polarion.core.util.logging.Logger;
import com.polarion.core.util.types.DateOnly;
import com.polarion.platform.persistence.model.IPObject;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("unused")
public class DeleteWorkRecordsHook extends ActionHook implements HookExecutor {

    public static final String DESCRIPTION = "Allow deletion of work records only from the current month.";

    public static final Logger logger = Logger.getLogger(DeleteWorkRecordsHook.class);
    public static final String WORK_RECORDS_WARNING = "Only work records added in the current month can be deleted.";

    public DeleteWorkRecordsHook() {
        super(ItemType.WORK_RECORD, ActionType.DELETE, DESCRIPTION);
    }

    @Override
    public String preAction(@NotNull IPObject object) {
        IWorkRecord workRecord = (IWorkRecord) object;
        DateOnly workRecordDate = workRecord.getDate();

        if (!isDateInCurrentMonth(workRecordDate.getDate())) {
            return WORK_RECORDS_WARNING;
        } else {
            return null;
        }
    }

    private static boolean isDateInCurrentMonth(@NotNull Date date) {
        Calendar currentCalendar = Calendar.getInstance();

        Calendar checkCalendar = Calendar.getInstance();
        checkCalendar.setTime(date);

        return currentCalendar.get(Calendar.YEAR) == checkCalendar.get(Calendar.YEAR) &&
                currentCalendar.get(Calendar.MONTH) == checkCalendar.get(Calendar.MONTH);
    }

    @Override
    public @NotNull HookExecutor getExecutor() {
        return this; //there is no need to create a separate executor instance coz only 'pre' action used
    }

    @Override
    public String getDefaultSettings() {
        return PropertiesUtils.build(WORK_RECORDS_WARNING);
    }
}
