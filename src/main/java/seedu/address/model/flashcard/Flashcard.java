package seedu.address.model.flashcard;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Flashcard in the card collection. Guarantees: details are present and not null, field values are
 * validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Statistics statistics;

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, tags, new Statistics());
    }

    public Flashcard(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Statistics statistics) {
        requireAllNonNull(name, phone, email, address, tags, statistics);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.statistics = statistics;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Returns true if both flashcards of the same name have at least one other identity field that is the same. This
     * defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getName().equals(getName())
                && (otherFlashcard.getPhone().equals(getPhone()) || otherFlashcard.getEmail().equals(getEmail()));
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    /**
     * Returns true if both flashcards have the same identity and data fields. This defines a stronger notion of
     * equality between two flashcards.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Flashcard)) {
            return false;
        }

        Flashcard otherFlashcard = (Flashcard) other;
        return otherFlashcard.getName().equals(getName())
                && otherFlashcard.getPhone().equals(getPhone())
                && otherFlashcard.getEmail().equals(getEmail())
                && otherFlashcard.getAddress().equals(getAddress())
                && otherFlashcard.getTags().equals(getTags());
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
