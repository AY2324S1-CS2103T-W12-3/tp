package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Address;
import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Industry;
import seedu.application.model.job.Job;
import seedu.application.model.job.JobType;
import seedu.application.model.job.Role;
import seedu.application.model.job.Status;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY,
                    PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_JOB_TYPE, PREFIX_INDUSTRY, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_COMPANY)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROLE, PREFIX_COMPANY, PREFIX_DEADLINE, PREFIX_STATUS,
                PREFIX_JOB_TYPE, PREFIX_INDUSTRY, PREFIX_ADDRESS);
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());
        Status status = Status.DEFAULT_STATUS; // Status is an optional field for add command
        Deadline deadline = Deadline.EMPTY_DEADLINE; // Deadline is an optional field for add command
        JobType jobType = JobType.EMPTY_JOB_TYPE; // Job Type is an optional field for add command
        Industry industry = Industry.DEFAULT_INDUSTRY; // Industry is an optional field for add command
        Address address = Address.DEFAULT_ADDRESS; // Address is an optional field for add command

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        }

        if (argMultimap.getValue(PREFIX_JOB_TYPE).isPresent()) {
            jobType = ParserUtil.parseJobType(argMultimap.getValue(PREFIX_JOB_TYPE).get());
        }

        if (argMultimap.getValue(PREFIX_INDUSTRY).isPresent()) {
            industry = ParserUtil.parseIndustry(argMultimap.getValue(PREFIX_INDUSTRY).get());
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }

        Job job = new Job(role, company, deadline, status, jobType, industry, address);

        return new AddCommand(job);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                              Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
