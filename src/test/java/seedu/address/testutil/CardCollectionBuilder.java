package seedu.address.testutil;

import seedu.address.model.CardCollection;
import seedu.address.model.flashcard.Flashcard;

/**
 * A utility class to help with building CardCollection objects.
 * Example usage: <br>
 * {@code CardCollection ab = new CardCollectionBuilder().withPerson("John", "Doe").build();}
 */
public class CardCollectionBuilder {

    private CardCollection cardCollection;

    public CardCollectionBuilder() {
        cardCollection = new CardCollection();
    }

    public CardCollectionBuilder(CardCollection cardCollection) {
        this.cardCollection = cardCollection;
    }

    /**
     * Adds a new {@code Flashcard} to the {@code CardCollection} that we are building.
     */
    public CardCollectionBuilder withPerson(Flashcard flashcard) {
        cardCollection.addPerson(flashcard);
        return this;
    }

    public CardCollection build() {
        return cardCollection;
    }
}
