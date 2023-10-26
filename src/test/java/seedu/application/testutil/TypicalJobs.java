package seedu.application.testutil;

import static seedu.application.logic.commands.CommandTestUtil.VALID_ADDRESS_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ADDRESS_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INDUSTRY_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_INDUSTRY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_JOB_TYPE_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_JOB_TYPE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.VALID_STATUS_CLEANER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.model.ApplicationBook;
import seedu.application.model.job.*;

/**
 * A utility class containing a list of {@code Job} objects to be used in tests.
 */
public class TypicalJobs {

    public static final Job SOFTWARE_ENGINEER = new JobBuilder().withRole("Software Engineer")
        .withCompany("Google").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    public static final Job TEACHER = new JobBuilder().withRole("Teacher")
        .withCompany("NUS").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    public static final Job WAITER = new JobBuilder().withRole("Waiter")
        .withCompany("McDonalds").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    public static final Job PILOT = new JobBuilder().withRole("Pilot")
        .withCompany("Singapore Airlines").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    public static final Job POLICE_OFFICER = new JobBuilder().withRole("Police Officer")
        .withCompany("SPF").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    public static final Job FRUIT_SELLER = new JobBuilder().withRole("Fruit Seller")
        .withCompany("ABC Fruits").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    public static final Job GRASS_CUTTER = new JobBuilder().withRole("Grass Cutter")
        .withCompany("XYZ Gardening").withStatus(Status.IN_PROGRESS)
        .withDeadline(Deadline.TO_ADD_DEADLINE).withJobType(JobType.TO_ADD_JOB_TYPE)
        .withIndustry(Industry.TO_ADD_INDUSTRY).withAddress(Address.TO_ADD_ADDRESS).build();
    // Manually added
    public static final Job ARTIST = new JobBuilder().withRole("Artist")
        .withCompany("Van Gogh Paintings").build();
    public static final Job SPRINTER = new JobBuilder().withRole("Sprinter")
        .withCompany("Nike").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Job CHEF = new JobBuilder().withRole(VALID_ROLE_CHEF)
        .withCompany(VALID_COMPANY_CHEF).withStatus(VALID_STATUS_CHEF)
        .withDeadline(VALID_DEADLINE_CHEF).withJobType(VALID_JOB_TYPE_CHEF)
        .withIndustry(VALID_INDUSTRY_CHEF).withAddress(VALID_ADDRESS_CHEF).build();
    public static final Job CLEANER = new JobBuilder().withRole(VALID_ROLE_CLEANER)
        .withCompany(VALID_COMPANY_CLEANER).withStatus(VALID_STATUS_CLEANER)
        .withDeadline(VALID_DEADLINE_CLEANER).withJobType(VALID_JOB_TYPE_CLEANER)
        .withIndustry(VALID_INDUSTRY_CLEANER).withAddress(VALID_ADDRESS_CLEANER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalJobs() {
    } // prevents instantiation

    /**
     * Returns an {@code ApplicationBook} with all the typical jobs.
     */
    public static ApplicationBook getTypicalApplicationBook() {
        ApplicationBook ab = new ApplicationBook();
        for (Job job : getTypicalJobs()) {
            ab.addJob(job);
        }
        return ab;
    }

    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(SOFTWARE_ENGINEER, TEACHER, WAITER, PILOT,
            POLICE_OFFICER, FRUIT_SELLER, GRASS_CUTTER));
    }
}
