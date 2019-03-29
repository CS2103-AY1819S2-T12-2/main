package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_QUIZ = "Quiz mode ended.";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Acquizition as requested ...";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        if (model.getQuizMode() != 0) {
            model.setQuizMode(0);
            model.setSelectedFlashcard(null);
            return new CommandResult(MESSAGE_EXIT_QUIZ);
        }
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
