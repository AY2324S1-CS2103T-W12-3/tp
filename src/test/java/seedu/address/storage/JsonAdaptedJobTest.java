package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedJob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.job.Company;
import seedu.address.model.job.Role;

public class JsonAdaptedJobTest {
    private static final String INVALID_ROLE = "Softw@re";
    private static final String INVALID_COMPANY = "+651234";

    private static final String VALID_ROLE = CLEANER.getRole().toString();
    private static final String VALID_COMPANY = CLEANER.getCompany().toString();
    private static final String VALID_REMARK = CLEANER.getRemark().toString();

    @Test
    public void toModelType_validJobDetails_returnsJob() throws Exception {
        JsonAdaptedJob job = new JsonAdaptedJob(CLEANER);
        assertEquals(CLEANER, job.toModelType());
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(INVALID_ROLE, VALID_COMPANY, VALID_REMARK);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(null, VALID_COMPANY, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, INVALID_COMPANY, VALID_REMARK);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }
}
