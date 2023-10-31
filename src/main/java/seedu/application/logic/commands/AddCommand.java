package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.application.commons.util.ToStringBuilder;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.Model;
import seedu.application.model.job.Job;

/**
 * Adds a person to the application book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the application book. "
        + "Parameters: "
        + PREFIX_COMPANY + "COMPANY "
        + PREFIX_ROLE + "ROLE\n"
        + "[Optional] "
        + PREFIX_STATUS + "STATUS "
        + PREFIX_DEADLINE + "DEADLINE "
        + PREFIX_JOB_TYPE + "JOB TYPE "
        + PREFIX_INDUSTRY + "INDUSTRY\n\n"
        + "Example: \n"
        + COMMAND_WORD + " "
        + PREFIX_COMPANY + "Google "
        + PREFIX_ROLE + "Software Engineer\n"
        + "OR\n" + COMMAND_WORD + " "
        + PREFIX_COMPANY + "Google "
        + PREFIX_ROLE + "Software Engineer "
        + PREFIX_STATUS + "PENDING "
        + PREFIX_DEADLINE + "Dec 31 2030 1200 "
        + PREFIX_JOB_TYPE + "INTERNSHIP"
        + PREFIX_INDUSTRY + "Technology\n";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the application book";

    private final Job toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Job}
     */
    public AddCommand(Job job) {
        requireNonNull(job);
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("toAdd", toAdd)
            .toString();
    }
}
