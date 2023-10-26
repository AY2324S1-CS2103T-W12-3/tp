package seedu.application.model.job;

import java.util.Objects;

import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;

/**
 * Represents a Job in the application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    // Identity fields
    private final Role role;
    private final Company company;
    private final Deadline deadline;
    private final Status status;
    private final JobType jobType;
    private final Industry industry;
    private final Address address;

    /**
     * Not all fields are compulsory
     */
    public Job(Role role, Company company, Deadline deadline, Status status, JobType jobType,
               Industry industry, Address address) {
        CollectionUtil.requireAllNonNull(role, company);
        this.role = role;
        this.company = company;
        this.deadline = deadline;
        this.status = status;
        this.jobType = jobType;
        this.industry = industry;
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public Company getCompany() {
        return company;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Status getStatus() {
        return status;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns true if both jobs have the same role and company.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
            && otherJob.getRole().equals(getRole())
            && otherJob.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both jobs have the same role, company and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return role.equals(otherJob.role)
            && company.equals(otherJob.company)
            && deadline.equals(otherJob.deadline)
            && status.equals(otherJob.status)
            && jobType.equals(otherJob.jobType)
            && industry.equals(otherJob.industry)
            && address.equals(otherJob.address);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(role, company);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("role", role)
            .add("company", company)
            .add("deadline", deadline)
            .add("status", status)
            .add("jobType", jobType)
            .add("industry", industry)
            .add("address", address)
            .toString();
    }
}
