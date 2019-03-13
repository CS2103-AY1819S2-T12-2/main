package seedu.address.model.flashcard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.FlashcardBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different flashcard -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Hello"));
        assertTrue(predicate.test(new FlashcardBuilder().withFrontFace("Hello").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Hello", "Hola"));
        assertTrue(predicate.test(new FlashcardBuilder().withFrontFace("Hello Hola").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Hello", "Hola"));
        assertTrue(predicate.test(new FlashcardBuilder().withFrontFace("Halo Hola").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("hEllO", "hOla"));
        assertTrue(predicate.test(new FlashcardBuilder().withFrontFace("Hello Holla").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
//        // Zero keywords
//        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
//        assertFalse(predicate.test(new FlashcardBuilder().withName("Alice").build()));
//
//        // Non-matching keyword
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
//        assertFalse(predicate.test(new FlashcardBuilder().withName("Alice Bob").build()));
//
//        // Keywords match phone, email and address, but does not match name
//        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
//        assertFalse(predicate.test(new FlashcardBuilder().withName("Alice").withPhone("12345")
//            .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
