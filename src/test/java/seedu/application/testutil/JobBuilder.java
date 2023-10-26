package seedu.application.testutil;

import seedu.application.model.job.*;

/**
 * A utility class to help with building Job objects.
 */
public class JobBuilder {

    public static final String DEFAULT_ROLE = "Student";
    public static final String DEFAULT_COMPANY = "Sparkletots";
    public static final String DEFAULT_DEADLINE = Deadline.TO_ADD_DEADLINE;
    public static final String DEFAULT_JOBTYPE = JobType.TO_ADD_JOB_TYPE;
    public static final String DEFAULT_STATUS = Status.IN_PROGRESS;
    public static final String DEFAULT_INDUSTRY = Industry.TO_ADD_INDUSTRY;
    public static final String DEFAULT_ADDRESS = Address.TO_ADD_ADDRESS;

    private Role role;
    private Company company;
    private Status status;
    private Deadline deadline;
    private JobType jobType;
    private Industry industry;
    private Address address;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        role = new Role(DEFAULT_ROLE);
        company = new Company(DEFAULT_COMPANY);
        deadline = new Deadline(DEFAULT_DEADLINE);
        jobType = new JobType(DEFAULT_JOBTYPE);
        status = new Status(DEFAULT_STATUS);
        industry = new Industry(DEFAULT_INDUSTRY);
        address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the JobBuilder with the data of {@code jobToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        role = jobToCopy.getRole();
        company = jobToCopy.getCompany();
        deadline = jobToCopy.getDeadline();
        jobType = jobToCopy.getJobType();
        status = jobToCopy.getStatus();
        industry = jobToCopy.getIndustry();
        address = jobToCopy.getAddress();
    }

    /**
     * Sets the {@code role} of the {@code Job} that we are building.
     */
    public JobBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Job} that we are building.
     */
    public JobBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Job} that we are building.
     */
    public JobBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Job} that we are building.
     */
    public JobBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code JobType} of the {@code Job} that we are building.
     */
    public JobBuilder withJobType(String jobType) {
        this.jobType = new JobType(jobType);
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code Job} that we are building.
     */
    public JobBuilder withIndustry(String industry) {
        this.industry = new Industry(industry);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Job} that we are building.
     */
    public JobBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }


    public Job build() {
        return new Job(role, company, deadline, status, jobType, industry, address);
    }

}
