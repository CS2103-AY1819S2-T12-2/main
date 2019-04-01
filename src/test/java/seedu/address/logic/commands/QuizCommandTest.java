package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalFlashcards.getTypicalCardCollection;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests and unit tests for QuizCommand.
 */
public class QuizCommandTest {

    private Model model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_quiz_success() throws CommandException {
        QuizCommand command = new QuizCommand();
        command.execute(model, commandHistory);
        assertEquals((int) model.getQuizMode(), -1);
        assertEquals(model.getQuizFlashcards().size(), model.getFilteredFlashcardList().size() - 1);
    }

}
