package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.NameContainsKeywordsPredicate;

/**
 * Enters quiz mode
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_QUIZ_START = "Quiz mode started";


    private final NameContainsKeywordsPredicate predicate;

    public QuizCommand() {
        predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        List<Flashcard> filteredPersonList = model.getFilteredFlashcardList();
        Flashcard randomFlashcard = filteredPersonList.get((int) Math.floor(Math.random() * filteredPersonList.size()));
        model.setSelectedFlashcard(randomFlashcard);
        return new CommandResult(MESSAGE_QUIZ_START);
    }
}