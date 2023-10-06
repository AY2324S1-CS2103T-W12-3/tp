package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.job.Job;

/**
 * A utility class containing a list of {@code Job} objects to be used in tests.
 */
public class TypicalJobs {

    public static final Job SOFTWARE_ENGINEER = new JobBuilder().withRole("Software Engineer")
            .withCompany("Google").build();
    public static final Job TEACHER = new JobBuilder().withRole("Teacher")
            .withCompany("NUS").build();
    public static final Job WAITER = new JobBuilder().withRole("Waiter")
            .withCompany("McDonalds").build();
    public static final Job PILOT = new JobBuilder().withRole("Pilot")
            .withCompany("Singapore Airlines").build();
    public static final Job POLICE_OFFICER = new JobBuilder().withRole("Police Officer")
            .withCompany("SPF").build();
    public static final Job FRUIT_SELLER = new JobBuilder().withRole("Fruit Seller")
            .withCompany("ABC Fruits").build();
    public static final Job GRASS_CUTTER = new JobBuilder().withRole("Grass Cutter")
            .withCompany("XYZ Gardening").build();

    // Manually added
    public static final Job ARTIST = new JobBuilder().withRole("Artist")
            .withCompany("Van Gogh Paintings").build();
    public static final Job SPRINTER = new JobBuilder().withRole("Sprinter")
            .withCompany("Nike").build();

    public static final Job STUDENT = new JobBuilder().withRole("Student")
            .withCompany("Sparkletots").withRemark("Pays not bad").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Job CHEF = new JobBuilder().withRole(VALID_ROLE_CHEF)
            .withCompany(VALID_COMPANY_CHEF).build();
    public static final Job CLEANER = new JobBuilder().withRole(VALID_ROLE_CLEANER)
            .withCompany(VALID_COMPANY_CLEANER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalJobs() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical jobs.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Job job : getTypicalJobs()) {
            ab.addJob(job);
        }
        return ab;
    }

    public static List<Job> getTypicalJobs() {
        return new ArrayList<>(Arrays.asList(SOFTWARE_ENGINEER, TEACHER, WAITER, PILOT,
                POLICE_OFFICER, FRUIT_SELLER, GRASS_CUTTER, STUDENT));
    }
}
