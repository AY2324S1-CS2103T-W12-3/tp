package seedu.application.testutil;

import static seedu.application.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.model.job.Job;

/**
 * A utility class for Job.
 */
public class JobUtil {

    /**
     * Returns an add command string for adding the {@code job}.
     */
    public static String getAddCommand(Job job) {
        return AddCommand.COMMAND_WORD + " " + getJobDetails(job);
    }

    /**
     * Returns the part of command string for the given {@code job}'s details.
     */
    public static String getJobDetails(Job job) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ROLE + job.getRole().description + " ");
        sb.append(PREFIX_COMPANY + job.getCompany().name + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditJobDescriptor}'s details.
     */
    public static String getEditJobDescriptorDetails(EditJobDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getRole().ifPresent(role -> sb.append(PREFIX_ROLE).append(role.description).append(" "));
        descriptor.getCompany()
            .ifPresent(company -> sb.append(PREFIX_COMPANY).append(company.name).append(" "));
        descriptor.getDeadline()
            .ifPresent(deadline -> sb.append(PREFIX_DEADLINE).append(deadline.deadline).append(" "));
        descriptor.getStatus()
            .ifPresent(status -> sb.append(PREFIX_STATUS).append(status.status).append(" "));
        descriptor.getJobType()
            .ifPresent(jobType -> sb.append(PREFIX_JOB_TYPE).append(jobType.jobType).append(" "));
        descriptor.getIndustry()
                .ifPresent(industry -> sb.append(PREFIX_INDUSTRY).append(industry.industry).append(" "));
        descriptor.getAddress()
            .ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.address).append(" "));
        return sb.toString();
    }
}
