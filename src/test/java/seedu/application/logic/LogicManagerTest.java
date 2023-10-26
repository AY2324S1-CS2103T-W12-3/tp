package seedu.application.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.logic.Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX;
import static seedu.application.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalJobs.CHEF;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.CommandResult;
import seedu.application.logic.commands.ListCommand;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.Job;
import seedu.application.storage.JsonApplicationBookStorage;
import seedu.application.storage.JsonUserPrefsStorage;
import seedu.application.storage.StorageManager;
import seedu.application.testutil.JobBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonApplicationBookStorage applicationBookStorage =
            new JsonApplicationBookStorage(temporaryFolder.resolve("applicationbook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(applicationBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
            LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
            LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredJobList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredJobList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e               the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an ApplicationBookStorage that throws the IOException e when saving
        JsonApplicationBookStorage applicationBookStorage = new JsonApplicationBookStorage(prefPath) {
            @Override
            public void saveApplicationBook(ReadOnlyApplicationBook applicationBook,
                                            Path filePath) throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(applicationBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveApplicationBook method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + ROLE_DESC_CHEF + COMPANY_DESC_CHEF
            + DEADLINE_DESC_CHEF + STATUS_DESC_CHEF + JOB_TYPE_DESC_CHEF + INDUSTRY_DESC_CHEF
            + ADDRESS_DESC_CHEF;
        Job expectedJob = new JobBuilder(CHEF).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addJob(expectedJob);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
