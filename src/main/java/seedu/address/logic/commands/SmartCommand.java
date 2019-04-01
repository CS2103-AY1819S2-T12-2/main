package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Good user feedback
 */
public class SmartCommand extends Command {

    public static final String COMMAND_WORD = "";

    private static final String MESSAGE_FAILURE = "";

    public SmartCommand() {
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.getQuizMode() == -1) {
            return new ShowCommand().execute(model, history);
        } else if (model.getQuizMode() == 1) {
            return new GoodCommand().execute(model, history);
        } else {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
