package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Address;
import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Industry;
import seedu.application.model.job.Job;
import seedu.application.model.job.JobType;
import seedu.application.model.job.Role;
import seedu.application.model.job.Status;


/**
 * Edits the details of an existing job in the application book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the job identified "
        + "by the index number used in the displayed job list.\n"
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_ROLE + "ROLE] "
        + "[" + PREFIX_COMPANY + "COMPANY] "
        + "[" + PREFIX_DEADLINE + "DEADLINE] "
        + "[" + PREFIX_STATUS + "STATUS] "
        + "[" + PREFIX_JOB_TYPE + "JOB TYPE] "
        + "[" + PREFIX_INDUSTRY + "INDUSTRY] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] \n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_ROLE + "Software Engineer "
        + PREFIX_COMPANY + "Google"
        + PREFIX_DEADLINE + "Dec 31 2023 1200"
        + PREFIX_STATUS + "Pending "
        + PREFIX_JOB_TYPE + "INTERNSHIP"
        + PREFIX_INDUSTRY + "Technology"
        + PREFIX_ADDRESS + "70 Pasir Panjang Road, Singapore 117371";

    public static final String MESSAGE_EDIT_JOB_SUCCESS = "Edited Job: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided. \n"
        + PREFIX_COMPANY + " for Company\n"
        + PREFIX_ROLE + " for Role\n"
        + PREFIX_STATUS + " for Status\n"
        + PREFIX_DEADLINE + " for Deadline\n"
        + PREFIX_JOB_TYPE + " for Job Type\n"
        + PREFIX_INDUSTRY + " for Industry\n"
        + PREFIX_ADDRESS + " for Address\n";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the application book.";

    private final Index index;
    private final EditJobDescriptor editJobDescriptor;

    /**
     * @param index             of the job in the filtered job list to edit
     * @param editJobDescriptor details to edit the job with
     */
    public EditCommand(Index index, EditJobDescriptor editJobDescriptor) {
        requireNonNull(index);
        requireNonNull(editJobDescriptor);

        this.index = index;
        this.editJobDescriptor = new EditJobDescriptor(editJobDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToEdit = lastShownList.get(index.getZeroBased());
        Job editedJob = createEditedJob(jobToEdit, editJobDescriptor);

        if (!jobToEdit.isSameJob(editedJob) && model.hasJob(editedJob)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.setJob(jobToEdit, editedJob);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(String.format(MESSAGE_EDIT_JOB_SUCCESS, Messages.format(editedJob)));
    }

    /**
     * Creates and returns a {@code Job} with the details of {@code jobToEdit}
     * edited with {@code editJobDescriptor}.
     */
    private static Job createEditedJob(Job jobToEdit, EditJobDescriptor editJobDescriptor) {
        assert jobToEdit != null;

        Role updatedRole = editJobDescriptor.getRole().orElse(jobToEdit.getRole());
        Company updatedCompany = editJobDescriptor.getCompany().orElse(jobToEdit.getCompany());
        Deadline updatedDeadline = editJobDescriptor.getDeadline().orElse(jobToEdit.getDeadline());
        Status updatedStatus = editJobDescriptor.getStatus().orElse(jobToEdit.getStatus());
        JobType updatedJobType = editJobDescriptor.getJobType().orElse(jobToEdit.getJobType());
        Industry updatedIndustry = editJobDescriptor.getIndustry().orElse(jobToEdit.getIndustry());
        Address updatedAddress = editJobDescriptor.getAddress().orElse(jobToEdit.getAddress());

        return new Job(updatedRole, updatedCompany, updatedDeadline, updatedStatus,
                updatedJobType, updatedIndustry, updatedAddress);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
            && editJobDescriptor.equals(otherEditCommand.editJobDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("index", index)
            .add("editJobDescriptor", editJobDescriptor)
            .toString();
    }

    /**
     * Stores the details to edit the job with. Each non-empty field value will replace the
     * corresponding field value of the job.
     */
    public static class EditJobDescriptor {
        private Company company;
        private Role role;
        private Deadline deadline;
        private Status status;
        private JobType jobType;
        private Industry industry;
        private Address address;

        public EditJobDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditJobDescriptor(EditJobDescriptor toCopy) {
            setCompany(toCopy.company);
            setRole(toCopy.role);
            setDeadline(toCopy.deadline);
            setStatus(toCopy.status);
            setJobType(toCopy.jobType);
            setIndustry(toCopy.industry);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(company, role, deadline, status, jobType, industry, address);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setJobType(JobType jobType) {
            this.jobType = jobType;
        }

        public Optional<JobType> getJobType() {
            return Optional.ofNullable(jobType);
        }

        public void setIndustry(Industry industry) {
            this.industry = industry;
        }

        public Optional<Industry> getIndustry() {
            return Optional.ofNullable(industry);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditJobDescriptor)) {
                return false;
            }

            EditJobDescriptor otherEditJobDescriptor = (EditJobDescriptor) other;
            return Objects.equals(company, otherEditJobDescriptor.company)
                && Objects.equals(role, otherEditJobDescriptor.role)
                && Objects.equals(deadline, otherEditJobDescriptor.deadline)
                && Objects.equals(status, otherEditJobDescriptor.status)
                && Objects.equals(jobType, otherEditJobDescriptor.jobType)
                && Objects.equals(industry, otherEditJobDescriptor.industry)
                && Objects.equals(address, otherEditJobDescriptor.address);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                .add("company", company)
                .add("role", role)
                .add("deadline", deadline)
                .add("status", status)
                .add("jobType", jobType)
                .add("industry", industry)
                .add("address", address)
                .toString();
        }
    }
}
