package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BACK_FACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRONT_FACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalFlashcards.EMAIL;
import static seedu.address.testutil.TypicalFlashcards.HELLO;
import static seedu.address.testutil.TypicalFlashcards.NEWTON;
import static seedu.address.testutil.TypicalFlashcards.getTypicalCardCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Face;
import seedu.address.model.flashcard.FlashcardContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        FlashcardContainsKeywordsPredicate firstPredicate =
            new FlashcardContainsKeywordsPredicate(Collections.singletonList("firstFront"),
                    Collections.singletonList("firstBack"), Collections.singletonList("firstTag"));
        FlashcardContainsKeywordsPredicate secondPredicate =
                new FlashcardContainsKeywordsPredicate(Collections.singletonList("secondFront"),
                        Collections.singletonList("secondBack"), Collections.singletonList("secondTag"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        FlashcardContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        FlashcardContainsKeywordsPredicate predicate = preparePredicate(PREFIX_FRONT_FACE
                + "Hello Newton's email");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HELLO, NEWTON, EMAIL), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code FlashcardContainsKeywordsPredicate}.
     */
    private FlashcardContainsKeywordsPredicate preparePredicate(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_FRONT_FACE, PREFIX_BACK_FACE, PREFIX_TAG);

        Set<Face> frontFaceKeywordSet = ParserUtil.parseFaces(argMultimap.getAllValues(PREFIX_FRONT_FACE));
        Set<Face> backFaceKeywordSet = ParserUtil.parseFaces(argMultimap.getAllValues(PREFIX_BACK_FACE));
        Set<Tag> tagKeywordSet = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        ArrayList<String> frontFaceKeywords = new ArrayList<>();
        ArrayList<String> backFaceKeywords = new ArrayList<>();
        ArrayList<String> tagKeywords = new ArrayList<>();

        for (Face frontFace : frontFaceKeywordSet) {
            String[] frontFaceTextSplit = frontFace.text.split("\\s+");
            for(String frontFaceKeyword : frontFaceTextSplit) {
                frontFaceKeywords.add(frontFaceKeyword);
            }
        }

        for (Face backFace : backFaceKeywordSet) {
            String[] backFaceTextSplit = backFace.text.split("\\s+");
            for(String backFaceKeyword : backFaceTextSplit) {
                backFaceKeywords.add(backFaceKeyword);
            }
        }

        for (Tag tag : tagKeywordSet) {
            tagKeywords.add(tag.tagName);
        }

        return new FlashcardContainsKeywordsPredicate(frontFaceKeywords, backFaceKeywords, tagKeywords);
    }
}
