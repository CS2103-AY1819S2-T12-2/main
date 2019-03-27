package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BACK_FACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRONT_FACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.FlashcardContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Creates a text file consisting of a set of flashcards which contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShareCommand extends Command {

    public static final String COMMAND_WORD = "share";
    public static final String FILE_NAME = "flashcardsToShare.txt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a text file consisting of a set of flashcards "
            + "which contain any of the specified keywords (case-insensitive) based on prefix. \n"
            + "Parameters: "
            + PREFIX_FRONT_FACE + "FRONTFACE "
            + PREFIX_BACK_FACE + "BACKFACE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FRONT_FACE + "Hello Ciao"
            + PREFIX_BACK_FACE + "Hola "
            + PREFIX_TAG + "Chinese "
            + PREFIX_TAG + "Spanish";

    private final FlashcardContainsKeywordsPredicate predicate;

    public ShareCommand(FlashcardContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(predicate);
        List<Flashcard> flashcardsToShare = model.getFilteredFlashcardList();
        generateFile(flashcardsToShare);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    /**
     * Creates a text file with the details of {@code flashcardsToShare}
     */
    private void generateFile(List<Flashcard> flashcardsToShare) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {

            StringBuilder lineToAdd;
            for (Flashcard flashcard : flashcardsToShare) {
                lineToAdd = new StringBuilder(" ");

                //Front Face
                lineToAdd.append(PREFIX_FRONT_FACE);
                lineToAdd.append(flashcard.getFrontFace().text);
                lineToAdd.append(" ");

                //Back Face
                lineToAdd.append(PREFIX_BACK_FACE);
                lineToAdd.append(flashcard.getBackFace().text);
                lineToAdd.append(" ");

                //Tag
                for (Tag tag : flashcard.getTags()) {
                    lineToAdd.append(PREFIX_TAG);
                    lineToAdd.append(tag.tagName);
                    lineToAdd.append(" ");
                }

                //Remove last char
                lineToAdd.setLength(lineToAdd.length() - 1);
                lineToAdd.append(System.getProperty("line.separator"));

                bw.write(lineToAdd.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShareCommand // instanceof handles nulls
                && predicate.equals(((ShareCommand) other).predicate)); // state check
    }
}
