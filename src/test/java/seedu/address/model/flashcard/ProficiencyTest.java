package seedu.address.model.flashcard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import seedu.address.testutil.Assert;

public class ProficiencyTest {
    private final Calendar NOW = Calendar.getInstance();
    private final Calendar YESTERDAY = Calendar.getInstance();
    private final Calendar TOMORROW = Calendar.getInstance();
    private final Calendar TWO_DAYS_FROM_NOW = Calendar.getInstance();

    @Before
    public void setUp() {
        TOMORROW.add(Calendar.DATE, 1);

        TWO_DAYS_FROM_NOW.add(Calendar.DATE, 2);

        YESTERDAY.add(Calendar.DATE, -1);
    }

    @Test
    public void constructor_successLessThanAttempt_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Proficiency(YESTERDAY, -32));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Proficiency(NOW, -1));
    }

    @Test
    public void isIncludedInCurrentQuiz() {
        assertFalse(new Proficiency(TOMORROW, 31).isIncludedInCurrentQuiz());
        assertFalse(new Proficiency(TOMORROW, 1).isIncludedInCurrentQuiz());

        assertTrue(new Proficiency().isIncludedInCurrentQuiz());
        assertTrue(new Proficiency(YESTERDAY, 100).isIncludedInCurrentQuiz());
    }

    @Test
    public void quizAttempt() {
        Proficiency proficiency = new Proficiency(NOW, 1);
        proficiency.quizAttempt(true);
        assertEquals(new Proficiency(TWO_DAYS_FROM_NOW, 2), proficiency);

        proficiency = new Proficiency(YESTERDAY, 3);
        proficiency.quizAttempt(false);
        assertEquals(new Proficiency(), proficiency);
    }


    @Test
    public void isValidProficiency() {
        // null proficiency
        Assert.assertThrows(NullPointerException.class, () -> Statistics.isValidStatistics(null));

        // blank proficiency
        assertFalse(Proficiency.isValidProficiency("")); // empty string
        assertFalse(Proficiency.isValidProficiency(" ")); // spaces only

        // missing parts
        assertFalse(Proficiency.isValidProficiency("inactive until 2 proficiency level"));
        assertFalse(Proficiency.isValidProficiency("inactive until 2 proficiency 3"));
        assertFalse(Proficiency.isValidProficiency("inactive until 2 level 3"));
        assertFalse(Proficiency.isValidProficiency("inactive until proficiency level 3"));
        assertFalse(Proficiency.isValidProficiency("inactive 2 proficiency level 3"));
        assertFalse(Proficiency.isValidProficiency("until 2 proficiency level 3"));

        // negative proficiency level
        assertFalse(Proficiency.isValidProficiency("inactive until 5 proficiency level -1"));

        // extra character(s)
        assertFalse(Proficiency.isValidProficiency("inactive until 2 proficiency level 3 "));
        assertFalse(Proficiency.isValidProficiency("inactive until 2 proficiency level 3  "));
        assertFalse(Proficiency.isValidProficiency("inactive until 2 proficiency level 3.  I am good at this."));

        // valid Proficiency
        assertTrue(Proficiency.isValidProficiency("inactive until 202 proficiency level 3"));
        assertTrue(Proficiency.isValidProficiency("inactive until 10230 proficiency level 100"));
        assertTrue(Proficiency.isValidProficiency("inactive until 202 proficiency level 30"));
        assertTrue(Proficiency.isValidProficiency("inactive until 12338 proficiency level 3392"));
    }
}
