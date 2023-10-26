package seedu.application.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.storage.JsonAdaptedJob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;

import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.model.job.*;

public class JsonAdaptedJobTest {
    private static final String INVALID_ROLE = "Softw@re";
    private static final String INVALID_COMPANY = "      ";
    private static final String INVALID_STATUS = "submitted";
    private static final String INVALID_DEADLINE = "Nov 12 2023";
    private static final String INVALID_JOB_TYPE = "OTHER";
    private static final String INVALID_INDUSTRY = "   ";
    private static final String INVALID_ADDRESS = " ";

    private static final String VALID_ROLE = CLEANER.getRole().toString();
    private static final String VALID_COMPANY = CLEANER.getCompany().toString();
    private static final String VALID_STATUS = CLEANER.getStatus().toString();
    private static final String VALID_DEADLINE = CLEANER.getDeadline().toString();
    private static final String VALID_JOB_TYPE = CLEANER.getJobType().toString();
    private static final String VALID_INDUSTRY = CLEANER.getIndustry().toString();
    private static final String VALID_ADDRESS = CLEANER.getAddress().toString();

    @Test
    public void toModelType_validJobDetails_returnsJob() throws Exception {
        JsonAdaptedJob job = new JsonAdaptedJob(CLEANER);
        assertEquals(CLEANER, job.toModelType());
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(INVALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                        VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(null, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, INVALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                        VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, null, VALID_STATUS, VALID_DEADLINE,
                VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, INVALID_STATUS, VALID_DEADLINE,
                        VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, null, VALID_DEADLINE,
                VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, INVALID_DEADLINE,
                        VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, null,
                VALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidJobType_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                        INVALID_JOB_TYPE, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = JobType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullJobType_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                null, VALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidIndustry_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                        VALID_JOB_TYPE, INVALID_INDUSTRY, VALID_ADDRESS);
        String expectedMessage = Industry.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullIndustry_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                VALID_JOB_TYPE, null, VALID_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Industry.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                        VALID_JOB_TYPE, VALID_INDUSTRY, INVALID_ADDRESS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_ROLE, VALID_COMPANY, VALID_STATUS, VALID_DEADLINE,
                VALID_JOB_TYPE, VALID_INDUSTRY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

}
