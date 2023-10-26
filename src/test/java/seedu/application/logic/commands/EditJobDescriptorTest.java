package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.DESC_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.DESC_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.testutil.EditJobDescriptorBuilder;

public class EditJobDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditJobDescriptor descriptorWithSameValues = new EditJobDescriptor(DESC_CHEF);
        assertEquals(DESC_CHEF, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_CHEF, DESC_CHEF);

        // null -> returns false
        assertNotEquals(DESC_CHEF, null);

        // different types -> returns false
        assertNotEquals(DESC_CHEF, 5.0f);

        // different values -> returns false
        assertNotEquals(DESC_CHEF, DESC_CLEANER);

        // different role -> returns false
        EditJobDescriptor editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertNotEquals(DESC_CHEF, editedChef);

        // different company -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withCompany(VALID_COMPANY_CLEANER).build();
        assertNotEquals(DESC_CHEF, editedChef);

        // different deadline -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withDeadline(VALID_DEADLINE_CLEANER).build();
        assertNotEquals(DESC_CHEF, editedChef);

        // different status -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withStatus("APPROVED").build();
        assertNotEquals(DESC_CHEF, editedChef);

        // different jobType -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withJobType("PART_TIME").build();
        assertNotEquals(DESC_CHEF, editedChef);

        // different industry -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withIndustry("Baking").build();
        assertNotEquals(DESC_CHEF, editedChef);

        // different address -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withAddress("123, Jurong West Ave 6, #08-111").build();
        assertNotEquals(DESC_CHEF, editedChef);
    }

    @Test
    public void toStringMethod() {
        EditJobDescriptor editJobDescriptor = new EditJobDescriptor();
        String expected = EditJobDescriptor.class.getCanonicalName() + "{company="
            + editJobDescriptor.getCompany().orElse(null) + ", role="
            + editJobDescriptor.getRole().orElse(null) + ", deadline="
            + editJobDescriptor.getDeadline().orElse(null) + ", status="
            + editJobDescriptor.getStatus().orElse(null) + ", jobType="
            + editJobDescriptor.getJobType().orElse(null) + ", industry="
            + editJobDescriptor.getIndustry().orElse(null) + ", address="
            + editJobDescriptor.getAddress().orElse(null) + "}";
        assertEquals(expected, editJobDescriptor.toString());
    }
}
