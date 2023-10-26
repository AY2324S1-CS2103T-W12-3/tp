package seedu.application.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.EditCommand;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_COMPANY, PREFIX_DEADLINE, PREFIX_STATUS,
                    PREFIX_JOB_TYPE, PREFIX_INDUSTRY, PREFIX_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ROLE, PREFIX_COMPANY, PREFIX_DEADLINE, PREFIX_STATUS,
                PREFIX_JOB_TYPE, PREFIX_INDUSTRY, PREFIX_ADDRESS);

        EditCommand.EditJobDescriptor editJobDescriptor = new EditCommand.EditJobDescriptor();

        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editJobDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            editJobDescriptor.setCompany(ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editJobDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editJobDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_JOB_TYPE).isPresent()) {
            editJobDescriptor.setJobType(ParserUtil.parseJobType(argMultimap.getValue(PREFIX_JOB_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_INDUSTRY).isPresent()) {
            editJobDescriptor.setIndustry(ParserUtil.parseIndustry(argMultimap.getValue(PREFIX_INDUSTRY).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editJobDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (!editJobDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editJobDescriptor);
    }

}
