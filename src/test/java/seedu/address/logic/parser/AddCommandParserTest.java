package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_CHEF;
import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalJobs.CLEANER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.job.Company;
import seedu.address.model.job.Job;
import seedu.address.model.job.Role;
import seedu.address.testutil.JobBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Job expectedJob = new JobBuilder(CLEANER).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ROLE_DESC_CLEANER + COMPANY_DESC_CLEANER,
                new AddCommand(expectedJob));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedJobString = ROLE_DESC_CLEANER + COMPANY_DESC_CLEANER;

        // multiple roles
        assertParseFailure(parser, ROLE_DESC_CHEF + validExpectedJobString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // multiple companies
        assertParseFailure(parser, COMPANY_DESC_CHEF + validExpectedJobString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedJobString + COMPANY_DESC_CHEF + ROLE_DESC_CHEF + validExpectedJobString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_COMPANY));

        // invalid value followed by valid value

        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + validExpectedJobString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid company
        assertParseFailure(parser, INVALID_COMPANY_DESC + validExpectedJobString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // valid value followed by invalid value

        // invalid role
        assertParseFailure(parser, validExpectedJobString + INVALID_ROLE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid company
        assertParseFailure(parser, validExpectedJobString + INVALID_COMPANY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing role prefix
        assertParseFailure(parser, VALID_ROLE_CLEANER + COMPANY_DESC_CLEANER, expectedMessage);

        // missing company prefix
        assertParseFailure(parser, ROLE_DESC_CLEANER + VALID_COMPANY_CLEANER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_ROLE_CLEANER + VALID_COMPANY_CLEANER, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid role
        assertParseFailure(parser, INVALID_ROLE_DESC + COMPANY_DESC_CLEANER, Role.MESSAGE_CONSTRAINTS);

        // invalid company
        assertParseFailure(parser, ROLE_DESC_CLEANER + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ROLE_DESC + INVALID_COMPANY_DESC, Role.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ROLE_DESC_CLEANER + COMPANY_DESC_CLEANER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
