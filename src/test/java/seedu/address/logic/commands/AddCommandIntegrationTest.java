package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalCardCollection;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Flashcard validFlashcard = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getCardCollection(), new UserPrefs());
        expectedModel.addPerson(validFlashcard);
        expectedModel.commitCardCollection();

        assertCommandSuccess(new AddCommand(validFlashcard), model, commandHistory,
            String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Flashcard flashcardInList = model.getCardCollection().getPersonList().get(0);
        assertCommandFailure(new AddCommand(flashcardInList), model, commandHistory,
            AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
