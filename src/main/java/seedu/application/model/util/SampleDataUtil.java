package seedu.application.model.util;

import seedu.application.model.ApplicationBook;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.job.*;

/**
 * Contains utility methods for populating {@code ApplicationBook} with sample data.
 */
public class SampleDataUtil {


    public static Job[] getSampleJobs() {
        return new Job[]{
            new Job(new Role("Software Engineer"), new Company("Google"),
                    Deadline.EMPTY_DEADLINE, Status.DEFAULT_STATUS, JobType.EMPTY_JOB_TYPE,
                    Industry.DEFAULT_INDUSTRY, Address.DEFAULT_ADDRESS),
            new Job(new Role("Cleaner"), new Company("NUS"),
                    Deadline.EMPTY_DEADLINE, Status.DEFAULT_STATUS, JobType.EMPTY_JOB_TYPE,
                    Industry.DEFAULT_INDUSTRY, Address.DEFAULT_ADDRESS),
            new Job(new Role("Chef"), new Company("McDonalds"),
                    Deadline.EMPTY_DEADLINE, Status.DEFAULT_STATUS, JobType.EMPTY_JOB_TYPE,
                    Industry.DEFAULT_INDUSTRY, Address.DEFAULT_ADDRESS),
        };
    }

    public static ReadOnlyApplicationBook getSampleApplicationBook() {
        ApplicationBook sampleAb = new ApplicationBook();
        for (Job sampleJob : getSampleJobs()) {
            sampleAb.addJob(sampleJob);
        }
        return sampleAb;
    }

}
