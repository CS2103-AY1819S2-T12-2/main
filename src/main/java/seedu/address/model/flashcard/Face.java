package seedu.address.model.flashcard;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Flashcard's face in the card collection.
 * Guarantees: immutable
 */
public class Face {
    public final String text;

    /**
     * Constructs a {@code Face}.
     *
     * @param text A valid text.
     */
    Face(String text) {
        requireNonNull(text);
        this.text = text;
    }

    @Override
    public String toString() {
        return "Face{" +
            "text='" + text + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Face face = (Face) o;
        return text.equals(face.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
