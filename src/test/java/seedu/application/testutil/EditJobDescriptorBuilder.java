package seedu.application.testutil;

import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.model.job.*;

/**
 * A utility class to help with building EditJobDescriptor objects.
 */
public class EditJobDescriptorBuilder {

    private EditJobDescriptor descriptor;

    public EditJobDescriptorBuilder() {
        descriptor = new EditJobDescriptor();
    }

    public EditJobDescriptorBuilder(EditJobDescriptor descriptor) {
        this.descriptor = new EditJobDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditJobDescriptor} with fields containing {@code job}'s details
     */
    public EditJobDescriptorBuilder(Job job) {
        descriptor = new EditJobDescriptor();
        descriptor.setRole(job.getRole());
        descriptor.setCompany(job.getCompany());
        descriptor.setDeadline(job.getDeadline());
        descriptor.setStatus(job.getStatus());
        descriptor.setJobType(job.getJobType());
        descriptor.setIndustry(job.getIndustry());
        descriptor.setAddress(job.getAddress());
    }

    /**
     * Sets the {@code Role} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Sets the {@code JobType} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withJobType(String jobType) {
        descriptor.setJobType(new JobType(jobType));
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withIndustry(String industry) {
        descriptor.setIndustry(new Industry(industry));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    public EditJobDescriptor build() {
        return descriptor;
    }
}
