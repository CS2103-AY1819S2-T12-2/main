package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.flashcard.FlashcardContainsKeywordsPredicate.IS_FRONT_FACE;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.FaceContainsKeywordsPredicate;
import seedu.address.model.flashcard.Flashcard;

/**
 * Enters quiz mode
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_QUIZ_START = "Quiz mode started";


    private final FaceContainsKeywordsPredicate predicate;

    public QuizCommand() {
        predicate = new FaceContainsKeywordsPredicate(Collections.emptyList(), IS_FRONT_FACE);
    }

    private Flashcard getRandomFlashCard(Model model) {
        Random random = new Random();

        List<Flashcard> filteredPersonList = model.getFilteredFlashcardList();
        Flashcard res;
        do {
            res = filteredPersonList.get(random.nextInt(filteredPersonList.size()));
        } while (res.equals(model.getSelectedFlashcard()));
        return res;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Flashcard randomFlashcard = getRandomFlashCard(model);
        model.setQuizMode(-1);
        model.setSelectedFlashcard(randomFlashcard);
        return new CommandResult(MESSAGE_QUIZ_START);
    }
}
