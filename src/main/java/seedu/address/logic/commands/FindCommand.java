package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.flashcard.FindCommandPredicate;
import seedu.address.model.flashcard.NameContainsKeywordsPredicate;
import seedu.address.model.flashcard.TagContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards whose text contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final FindCommandPredicate findPredicate;

    public FindCommand(FindCommandPredicate findPredicate) {
        this.findPredicate = findPredicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(findPredicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && findPredicate.equals(((FindCommand) other).findPredicate)); // state check
    }
}
