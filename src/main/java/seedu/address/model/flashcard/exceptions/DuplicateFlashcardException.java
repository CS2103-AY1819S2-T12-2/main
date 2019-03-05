package seedu.address.model.flashcard.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateFlashcardException extends RuntimeException {
    public DuplicateFlashcardException() {
        super("Operation would result in duplicate persons");
    }
}
