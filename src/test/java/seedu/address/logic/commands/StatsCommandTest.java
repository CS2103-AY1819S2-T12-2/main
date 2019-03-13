package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalFlashcards.getTypicalCardCollection;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StatsCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute() throws Exception {
        StatsCommand command = new StatsCommand();

        CommandResult result = command.execute(model, commandHistory);

        assertEquals(result, new CommandResult("Success rate: 0.00 %."));
    }
}
