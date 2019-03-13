package seedu.address.model.flashcard;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Flashcard}'s {@code Tag} matches any of the keywords given.
 */
public class FindCommandPredicate implements Predicate<Flashcard> {
    private final List<String> nameKeywords;
    private final List<String> tagKeywords;

    public FindCommandPredicate(List<String> nameKeywords, List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(nameKeywords);
        TagContainsKeywordsPredicate tagPredicate = new TagContainsKeywordsPredicate(tagKeywords);

        return (namePredicate.test(flashcard) || tagPredicate.test(flashcard));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommandPredicate // instanceof handles nulls
                && nameKeywords.equals(((FindCommandPredicate) other).nameKeywords)
                && tagKeywords.equals(((FindCommandPredicate) other).tagKeywords)); // state check
    }

}
