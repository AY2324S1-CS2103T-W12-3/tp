package seedu.application.model.job;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ADDRESS_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INDUSTRY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_JOB_TYPE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_CLEANER;
import static seedu.application.testutil.TypicalJobs.CHEF;
import static seedu.application.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;

import seedu.application.testutil.JobBuilder;

public class JobTest {

    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(CHEF.isSameJob(CHEF));

        // null -> returns false
        assertFalse(CHEF.isSameJob(null));

        // To be implemented after non-identity fields are created
        // same role and company, all other attributes different -> returns true
        Job editedChef = new JobBuilder(CHEF).withCompany(VALID_COMPANY_CHEF).build();
        assertTrue(CHEF.isSameJob(editedChef));

        // different role, all other attributes same -> returns false
        editedChef = new JobBuilder(CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertFalse(CHEF.isSameJob(editedChef));

        // role differs in case, all other attributes same -> returns false
        Job editedCleaner = new JobBuilder(CLEANER).withRole(VALID_ROLE_CLEANER.toLowerCase()).build();
        assertFalse(CLEANER.isSameJob(editedCleaner));

        // role has trailing spaces, all other attributes same -> returns false
        String roleWithTrailingSpaces = VALID_ROLE_CLEANER + " ";
        editedCleaner = new JobBuilder(CLEANER).withRole(roleWithTrailingSpaces).build();
        assertFalse(CLEANER.isSameJob(editedCleaner));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Job chefCopy = new JobBuilder(CHEF).build();
        assertEquals(CHEF, chefCopy);

        // same object -> returns true
        assertEquals(CHEF, CHEF);

        // null -> returns false
        assertNotEquals(CHEF, null);

        // different type -> returns false
        assertNotEquals(CHEF, 5.0f);

        // different job -> returns false
        assertNotEquals(CHEF, CLEANER);

        // different role -> returns false
        Job editedChef = new JobBuilder(CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertNotEquals(CHEF, editedChef);

        // different company -> returns false
        editedChef = new JobBuilder(CHEF).withCompany(VALID_COMPANY_CLEANER).build();
        assertNotEquals(CHEF, editedChef);

        // different status -> returns false
        editedChef = new JobBuilder(CHEF).withStatus(VALID_STATUS_CLEANER).build();
        assertNotEquals(CHEF, editedChef);

        // different deadline -> returns false
        editedChef = new JobBuilder(CHEF).withDeadline(VALID_DEADLINE_CLEANER).build();
        assertNotEquals(CHEF, editedChef);

        // different job type -> returns false
        editedChef = new JobBuilder(CHEF).withJobType(VALID_JOB_TYPE_CLEANER).build();
        assertNotEquals(CHEF, editedChef);

        // different industry -> returns false
        editedChef = new JobBuilder(CHEF).withIndustry(VALID_INDUSTRY_CLEANER).build();
        assertNotEquals(CHEF, editedChef);

        // different address -> returns false
        editedChef = new JobBuilder(CHEF).withAddress(VALID_ADDRESS_CLEANER).build();
        assertNotEquals(CHEF, editedChef);
    }

    @Test
    public void toStringMethod() {
        String expected = Job.class.getCanonicalName()
                + "{role=" + CHEF.getRole()
                + ", company=" + CHEF.getCompany()
                + ", deadline=" + CHEF.getDeadline()
                + ", status=" + CHEF.getStatus()
                + ", jobType=" + CHEF.getJobType()
                + ", industry=" + CHEF.getIndustry()
                + ", address=" + CHEF.getAddress()
                + "}";
        assertEquals(expected, CHEF.toString());
    }
}
