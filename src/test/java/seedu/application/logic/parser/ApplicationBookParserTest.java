package seedu.application.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.ClearCommand;
import seedu.application.logic.commands.DeadlineCommand;
import seedu.application.logic.commands.DeleteCommand;
import seedu.application.logic.commands.EditCommand;
import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.logic.commands.ExitCommand;
import seedu.application.logic.commands.FindCommand;
import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.commands.ListCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.Job;
import seedu.application.model.job.RoleContainsKeywordsPredicate;
import seedu.application.testutil.EditJobDescriptorBuilder;
import seedu.application.testutil.JobBuilder;
import seedu.application.testutil.JobUtil;

public class ApplicationBookParserTest {

    private final ApplicationBookParser parser = new ApplicationBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Job job = new JobBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(JobUtil.getAddCommand(job));
        assertEquals(new AddCommand(job), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_JOB.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_JOB), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Job job = new JobBuilder().build();
        EditJobDescriptor descriptor = new EditJobDescriptorBuilder(job).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_JOB.getOneBased() + " " + JobUtil.getEditJobDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_JOB, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new RoleContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        final String deadline = "Dec 31 20230 1200";
        DeadlineCommand command = (DeadlineCommand) parser.parseCommand(DeadlineCommand.COMMAND_WORD + " "
                + INDEX_FIRST_JOB.getOneBased() + " " + PREFIX_DEADLINE + deadline);
        assertEquals(new DeadlineCommand(INDEX_FIRST_JOB, deadline), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
