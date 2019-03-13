package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CardCollection;
import seedu.address.model.flashcard.Flashcard;

/**
 * A utility class containing a list of {@code Flashcard} objects to be used in tests.
 */
public class TypicalFlashcards {

    public static final Flashcard HELLO = new FlashcardBuilder().withFrontFace("Hello")
        .withBackFace("Halo").withTags("indonesian").build();
    public static final Flashcard HOLA = new FlashcardBuilder().withFrontFace("Hola")
        .withBackFace("Hello").withTags("spanish").build();
    public static final Flashcard EAT = new FlashcardBuilder().withFrontFace("Eat")
        .withBackFace("吃").withTags("chinese").build();
    public static final Flashcard NEWTON = new FlashcardBuilder().withFrontFace("Newton's 3rd Law")
        .withBackFace("idk").withTags("alevel", "physics").build();
    public static final Flashcard EMAIL = new FlashcardBuilder().withFrontFace("Robin's email")
        .withBackFace("robincyu96@gmail.com").build();

    public static final String KEYWORD_MATCHING_HELLO = "Hello"; // A keyword that matches Hello

    private TypicalFlashcards() {
    } // prevents instantiation

    /**
     * Returns an {@code CardCollection} with all the typical flashcards.
     */
    public static CardCollection getTypicalCardCollection() {
        CardCollection ab = new CardCollection();
        for (Flashcard flashcard : getTypicalFlashcards()) {
            ab.addFlashcard(flashcard);
        }
        return ab;
    }

    public static List<Flashcard> getTypicalFlashcards() {
        return new ArrayList<>(Arrays.asList(HELLO, HOLA, EAT, NEWTON, EMAIL));
    }
}
