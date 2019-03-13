package seedu.address.model.flashcard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class StatisticsTest {

    @Test
    public void constructor_successLessThanAttempt_throwsIllegalArgumentException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new Statistics(10, 9));
        Assert.assertThrows(IllegalArgumentException.class, () -> new Statistics(1, 0));
    }

    @Test
    public void getSuccessRate() {
        double epsilon = 1e-9;
        assertEquals((double) 17 / 31, new Statistics(17, 31).getSuccessRate(), epsilon);
        assertEquals((double) 11 / 50, new Statistics(11, 50).getSuccessRate(), epsilon);

        assertEquals(0, new Statistics(0, 0).getSuccessRate(), epsilon);
        assertEquals(0, new Statistics().getSuccessRate(), epsilon);
    }

    @Test
    public void quizAttempt() {
        Statistics stats = new Statistics(1, 3);

        stats.quizAttempt(true);
        assertEquals(new Statistics(2, 4), stats);

        stats.quizAttempt(false);
        assertEquals(new Statistics(2, 5), stats);
    }

    @Test
    public void merge() {
        Statistics stats1 = new Statistics(11, 30);
        Statistics stats2 = new Statistics(101, 301);
        Statistics stats3 = new Statistics();

        assertEquals(new Statistics(101 + 11, 301 + 30), stats1.merge(stats2));
        assertEquals(new Statistics(101, 301), stats2.merge(stats3));
        assertEquals(new Statistics(11, 30), stats1.merge(stats3));
    }
}
