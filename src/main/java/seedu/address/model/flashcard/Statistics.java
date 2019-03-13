package seedu.address.model.flashcard;

import java.util.Objects;

import seedu.address.model.flashcard.exceptions.ZeroAttemptException;

/**
 * Represents a Flashcard's statistics on how well the user does in the quiz mode.
 */
public class Statistics {

    private int attemptNumber;
    private int successAttempt;

    public Statistics(int successAttempt, int attemptNumber) {
        if (successAttempt > attemptNumber) {
            throw new IllegalArgumentException("successAttempt higher than attemptNumber");
        }
        this.attemptNumber = attemptNumber;
        this.successAttempt = successAttempt;
    }

    public Statistics() {
        this.attemptNumber = 0;
        this.successAttempt = 0;
    }

    /**
     * @return The success rate of a quiz on a particular card. Return 0 if there hasn't any attempt in quiz.
     */
    public double getSuccessRate() throws ZeroAttemptException {
        if (attemptNumber == 0) {
            return 0;
        }
        return (double) successAttempt / attemptNumber;
    }

    /**
     * update the statistics after a quiz is finished.
     * @param isSuccess does the user guess the card from the quiz correctly.
     */
    public void quizAttempt(boolean isSuccess) {
        if (isSuccess) {
            successAttempt++;
        }
        attemptNumber++;
    }

    /**
     * merge two statistics by combining the attempt and success number.
     * @param oth
     * @return new Statistics.
     */
    public Statistics merge(Statistics oth) {
        Statistics merged = new Statistics();
        merged.attemptNumber = this.attemptNumber + oth.attemptNumber;
        merged.successAttempt = this.successAttempt + oth.successAttempt;
        return merged;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.successAttempt)
                .append(" success out of ")
                .append(this.attemptNumber)
                .append(" attempts.");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Statistics // instanceof handles nulls
                && attemptNumber == ((Statistics) other).attemptNumber
                && successAttempt == ((Statistics) other).successAttempt); // state check
    }
}
