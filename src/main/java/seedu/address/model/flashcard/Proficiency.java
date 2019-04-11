package seedu.address.model.flashcard;

import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a Flashcard's proficiency level on how well the user does in the quiz mode.
 */
public class Proficiency {
    public static final String VALIDATION_REGEX = "review in \\d+ proficiency level \\d+$";
    public static final String MESSAGE_CONSTRAINTS = "Proficiency string format must be in the form of: "
            + "review in <quiz left until review> proficiency <proficiency level>`";

    private int timeUntilReview;
    private int proficiencyLevel;

    public Proficiency(int timeUntilReview, int proficiencyLevel) {
        if (timeUntilReview > proficiencyLevel) {
            throw new IllegalArgumentException("timeUntilReview higher than proficiencyLevel");
        }
        if (timeUntilReview < 0) {
            throw new IllegalArgumentException("timeUntilReview cannot be negative");
        }
        this.timeUntilReview = timeUntilReview;
        this.proficiencyLevel = proficiencyLevel;
    }

    public Proficiency() {
        this.timeUntilReview = 0;
        this.proficiencyLevel = 0;
    }

    public Proficiency(String fromString) {
        Scanner sc = new Scanner(fromString);

        sc.next(); // string: review
        sc.next(); // string: in
        timeUntilReview = sc.nextInt();
        sc.next(); // string: proficiency
        sc.next(); // string: level
        proficiencyLevel = sc.nextInt();
    }

    /**
     * Returns if a given string is a valid proficiency format.
     */
    public static boolean isValidProficiency(String test) {
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        Proficiency dummy = new Proficiency(test);
        return dummy.timeUntilReview <= dummy.proficiencyLevel && dummy.timeUntilReview >= 0;
    }

    /**
     * @return True if this flashcard is reviewed in the current session, else update the time until review.
     */
    public boolean isIncludedInCurrentQuiz() {
        if (timeUntilReview == 0) {
            return true;
        }
        timeUntilReview--;
        return false;
    }

    /**
     * update the proficiency after a quiz is finished.
     *
     * @param isSuccess does the user guess the card from the quiz correctly.
     */
    public void quizAttempt(boolean isSuccess) {
        if (isSuccess) {
            proficiencyLevel++;
        } else {
            proficiencyLevel = 0;
        }
        timeUntilReview = proficiencyLevel;
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Proficiency // instanceof handles nulls
                && timeUntilReview == ((Proficiency) other).timeUntilReview
                && proficiencyLevel == ((Proficiency) other).proficiencyLevel); // state check
    }

    @Override
    public String toString() {
        return String.format("review in %d proficiency level %d", timeUntilReview, proficiencyLevel);
    }
}
