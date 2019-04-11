package seedu.address.model.flashcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ProficiencyTest {

    @Test
    public void constructor_successLessThanAttempt_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Proficiency(10, 9));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Proficiency(1, 0));
    }

    @Test
    public void isIncludedInCurrentQuiz() {
        assertFalse(new Proficiency(17, 31).isIncludedInCurrentQuiz());
        assertFalse(new Proficiency(1, 1).isIncludedInCurrentQuiz());

        assertTrue(new Proficiency().isIncludedInCurrentQuiz());
        assertTrue(new Proficiency(0, 100).isIncludedInCurrentQuiz());
    }

    @Test
    public void quizAttempt() {
        Proficiency proficiency = new Proficiency(0, 3);
        proficiency.quizAttempt(true);
        assertEquals(new Proficiency(4, 4), proficiency);

        proficiency = new Proficiency(0, 3);
        proficiency.quizAttempt(false);
        assertEquals(new Proficiency(0, 0), proficiency);
    }


    @Test
    public void isValidProficiency() {
        // null proficiency
        Assert.assertThrows(NullPointerException.class, () -> Statistics.isValidStatistics(null));

        // blank proficiency
        assertFalse(Proficiency.isValidProficiency("")); // empty string
        assertFalse(Proficiency.isValidProficiency(" ")); // spaces only

        // missing parts
        assertFalse(Proficiency.isValidProficiency("review in 2 proficiency level"));
        assertFalse(Proficiency.isValidProficiency("review in 2 proficiency 3"));
        assertFalse(Proficiency.isValidProficiency("review in 2 level 3"));
        assertFalse(Proficiency.isValidProficiency("review in proficiency level 3"));
        assertFalse(Proficiency.isValidProficiency("review 2 proficiency level 3"));
        assertFalse(Proficiency.isValidProficiency("in 2 proficiency level 3"));

        // success greater than attempt
        assertFalse(Proficiency.isValidProficiency("review in 5 proficiency level 3"));

        // extra character(s)
        assertFalse(Proficiency.isValidProficiency("review in 2 proficiency level 3 "));
        assertFalse(Proficiency.isValidProficiency("review in 2 proficiency level 3  "));
        assertFalse(Proficiency.isValidProficiency("review in 2 proficiency level 3.  I am good at this."));

        // valid Proficiency
        assertTrue(Proficiency.isValidProficiency("review in 2 proficiency level 3"));
        assertTrue(Proficiency.isValidProficiency("review in 100 proficiency level 100"));
        assertTrue(Proficiency.isValidProficiency("review in 20 proficiency level 30"));
        assertTrue(Proficiency.isValidProficiency("review in 1238 proficiency level 3392"));
    }
}
