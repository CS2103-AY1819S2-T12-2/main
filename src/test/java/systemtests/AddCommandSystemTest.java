package systemtests;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BACKFACE_DESC_EAT;
import static seedu.address.logic.commands.CommandTestUtil.BACKFACE_DESC_HELLO;
import static seedu.address.logic.commands.CommandTestUtil.FRONTFACE_DESC_EAT;
import static seedu.address.logic.commands.CommandTestUtil.FRONTFACE_DESC_HELLO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHINESE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INDONESIAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRONTFACE_EAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalFlashcards.EAT;
import static seedu.address.testutil.TypicalFlashcards.HELLO;
import static seedu.address.testutil.TypicalFlashcards.HOLA;
import static seedu.address.testutil.TypicalFlashcards.KEYWORD_MATCHING_HELLO;
import static seedu.address.testutil.TypicalFlashcards.NEWTON;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Address;
import seedu.address.model.flashcard.Email;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Name;
import seedu.address.model.flashcard.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.FlashcardBuilder;
import seedu.address.testutil.FlashcardUtil;

public class AddCommandSystemTest extends CardCollectionSystemTest {

    @Test
    public void add() {
        Model model = getModel();

        /* ------------------------ Perform add operations on the shown unfiltered list ----------------------------- */

        /* Case: add a flashcard without tags to a non-empty card collection, command with leading spaces and trailing
         * spaces
         * -> added
         */
        Flashcard toAdd = HELLO;
        String command =
            "   " + AddCommand.COMMAND_WORD + "  " + FRONTFACE_DESC_HELLO + "  " + BACKFACE_DESC_HELLO + " "
                + TAG_DESC_INDONESIAN + " ";
        assertCommandSuccess(command, toAdd);

        /* Case: undo adding Amy to the list -> Amy deleted */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: redo adding Amy to the list -> Amy added again */
        command = RedoCommand.COMMAND_WORD;
        model.addFlashcard(toAdd);
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: add a flashcard with all fields same as another flashcard in the card collection except name ->
        added */
        toAdd = new FlashcardBuilder(HELLO).withFrontFace(VALID_FRONTFACE_EAT).build();
        command = AddCommand.COMMAND_WORD + FRONTFACE_DESC_EAT + BACKFACE_DESC_HELLO + TAG_DESC_INDONESIAN;
        assertCommandSuccess(command, toAdd);

        /* Case: add to empty card collection -> added */
        deleteAllFlashcards();
        assertCommandSuccess(HELLO);

        /* Case: add a flashcard with tags, command with parameters in random order -> added */
        toAdd = EAT;
        command = AddCommand.COMMAND_WORD + TAG_DESC_CHINESE + BACKFACE_DESC_EAT + FRONTFACE_DESC_EAT;
        assertCommandSuccess(command, toAdd);

        /* -------------------------- Perform add operation on the shown filtered list ------------------------------ */

        /* Case: filters the flashcard list before adding -> added */
        showFlashcardsWithName(KEYWORD_MATCHING_HELLO);
        assertCommandSuccess(HOLA);

        /* ------------------------ Perform add operation while a flashcard card is selected
        --------------------------- */

        /* Case: selects first card in the flashcard list, add a flashcard -> added, card selection remains unchanged */
        selectFlashcard(Index.fromOneBased(1));
        assertCommandSuccess(NEWTON);

        /* ----------------------------------- Perform invalid add operations --------------------------------------- */

        /* Case: add a duplicate flashcard -> rejected */
        command = FlashcardUtil.getAddCommand(HELLO);
        assertCommandFailure(command, AddCommand.MESSAGE_DUPLICATE_FLASHCARD);

    }

    /**
     * Executes the {@code AddCommand} that adds {@code toAdd} to the model and asserts that the,<br>
     * 1. Command box displays an empty string.<br>
     * 2. Command box has the default style class.<br>
     * 3. Result display box displays the success message of executing {@code AddCommand} with the details of
     * {@code toAdd}.<br>
     * 4. {@code Storage} and {@code FlashcardListPanel} equal to the corresponding components in
     * the current model added with {@code toAdd}.<br>
     * 5. Browser url and selected card remain unchanged.<br>
     * 6. Status bar's sync status changes.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code CardCollectionSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     *
     * @see CardCollectionSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(Flashcard toAdd) {
        assertCommandSuccess(FlashcardUtil.getAddCommand(toAdd), toAdd);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(Flashcard)}. Executes {@code command}
     * instead.
     *
     * @see AddCommandSystemTest#assertCommandSuccess(Flashcard)
     */
    private void assertCommandSuccess(String command, Flashcard toAdd) {
        Model expectedModel = getModel();
        expectedModel.addFlashcard(toAdd);
        String expectedResultMessage = String.format(AddCommand.MESSAGE_SUCCESS, toAdd);

        assertCommandSuccess(command, expectedModel, expectedResultMessage);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Flashcard)} except asserts that
     * the,<br>
     * 1. Result display box displays {@code expectedResultMessage}.<br>
     * 2. {@code Storage} and {@code FlashcardListPanel} equal to the corresponding components in
     * {@code expectedModel}.<br>
     *
     * @see AddCommandSystemTest#assertCommandSuccess(String, Flashcard)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and asserts that the,<br>
     * 1. Command box displays {@code command}.<br>
     * 2. Command box has the error style class.<br>
     * 3. Result display box displays {@code expectedResultMessage}.<br>
     * 4. {@code Storage} and {@code FlashcardListPanel} remain unchanged.<br>
     * 5. Browser url, selected card and status bar remain unchanged.<br>
     * Verifications 1, 3 and 4 are performed by
     * {@code CardCollectionSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     *
     * @see CardCollectionSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
