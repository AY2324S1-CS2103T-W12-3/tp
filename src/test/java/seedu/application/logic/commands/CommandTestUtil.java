package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;
import seedu.application.model.job.FieldContainsKeywordsPredicate;
import seedu.application.model.job.Job;
import seedu.application.testutil.EditJobDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ROLE_CHEF = "Chef";
    public static final String VALID_ROLE_CLEANER = "Cleaner";
    public static final String VALID_COMPANY_CHEF = "FineFoods";
    public static final String VALID_COMPANY_CLEANER = "Bleach Inc";
    public static final String VALID_STATUS_CHEF = "rejected";
    public static final String VALID_STATUS_CLEANER = "approved";
    public static final String VALID_DEADLINE_CHEF = "Dec 31 2030 1200";
    public static final String VALID_DEADLINE_CLEANER = "Dec 31 2040 1200";
    public static final String VALID_JOB_TYPE_CHEF = "FULL_TIME";
    public static final String VALID_JOB_TYPE_CLEANER = "PART_TIME";
    public static final String VALID_INDUSTRY_CHEF = "Culinary";
    public static final String VALID_INDUSTRY_CLEANER = "Cleaning";
    public static final String VALID_ADDRESS_CHEF = "123, Jurong West Ave 6, #08-111";
    public static final String VALID_ADDRESS_CLEANER = "311, Clementi Ave 2, #02-25";
    public static final String ROLE_DESC_CHEF = " " + PREFIX_ROLE + VALID_ROLE_CHEF;
    public static final String ROLE_DESC_CLEANER = " " + PREFIX_ROLE + VALID_ROLE_CLEANER;
    public static final String COMPANY_DESC_CHEF = " " + PREFIX_COMPANY + VALID_COMPANY_CHEF;
    public static final String COMPANY_DESC_CLEANER = " " + PREFIX_COMPANY + VALID_COMPANY_CLEANER;
    public static final String STATUS_DESC_CHEF = " " + PREFIX_STATUS + VALID_STATUS_CHEF;
    public static final String STATUS_DESC_CLEANER = " " + PREFIX_STATUS + VALID_STATUS_CLEANER;
    public static final String DEADLINE_DESC_CHEF = " " + PREFIX_DEADLINE + VALID_DEADLINE_CHEF;
    public static final String DEADLINE_DESC_CLEANER = " " + PREFIX_DEADLINE + VALID_DEADLINE_CLEANER;
    public static final String JOB_TYPE_DESC_CHEF = " " + PREFIX_JOB_TYPE + VALID_JOB_TYPE_CHEF;
    public static final String JOB_TYPE_DESC_CLEANER = " " + PREFIX_JOB_TYPE + VALID_JOB_TYPE_CLEANER;
    public static final String INDUSTRY_DESC_CHEF = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_CHEF;
    public static final String INDUSTRY_DESC_CLEANER = " " + PREFIX_INDUSTRY + VALID_INDUSTRY_CLEANER;
    public static final String ADDRESS_DESC_CHEF = " " + PREFIX_ADDRESS + VALID_ADDRESS_CHEF;
    public static final String ADDRESS_DESC_CLEANER = " " + PREFIX_ADDRESS + VALID_ADDRESS_CLEANER;
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + "&"; // '&' not allowed in roles
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "    "; // company cannot be blank
    public static final String INVALID_DEADLINE_DESC =
            " " + PREFIX_DEADLINE + "Nov 12 2023"; // deadline must be in the future
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS + "SUBMITTED";
    public static final String INVALID_JOB_TYPE_DESC =
            " " + PREFIX_JOB_TYPE + "OTHER"; // job type must be selected from the list of options
    public static final String INVALID_INDUSTRY_DESC =
            " " + PREFIX_INDUSTRY + "    "; //industry cannot be blank
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // address cannot be blank
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditJobDescriptor DESC_CHEF;
    public static final EditCommand.EditJobDescriptor DESC_CLEANER;

    static {
        DESC_CHEF = new EditJobDescriptorBuilder().withRole(VALID_ROLE_CHEF)
            .withCompany(VALID_COMPANY_CHEF).build();
        DESC_CLEANER = new EditJobDescriptorBuilder().withRole(VALID_ROLE_CLEANER)
            .withCompany(VALID_COMPANY_CLEANER).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the application book, filtered job list and selected job in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ApplicationBook expectedApplicationBook = new ApplicationBook(actualModel.getApplicationBook());
        List<Job> expectedFilteredList = new ArrayList<>(actualModel.getFilteredJobList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedApplicationBook, actualModel.getApplicationBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredJobList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the job at the given {@code targetIndex} in the
     * {@code model}'s application book.
     */
    public static void showJobAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredJobList().size());

        Job job = model.getFilteredJobList().get(targetIndex.getZeroBased());
        final String[] splitRole = job.getRole().description.split("\\s+");
        model.updateFilteredJobList(
            new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList(splitRole[0])));

        assertEquals(1, model.getFilteredJobList().size());
    }

}
