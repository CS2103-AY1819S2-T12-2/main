package systemtests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuizCommandSystemTest extends CardCollectionSystemTest {
    @Test
    public void quiz() {
        executeCommand("quiz");
        assertEquals(-1, (int) getModelQuizMode());
        executeCommand("show");
        assertEquals(1, (int) getModelQuizMode());
        int size = getModelQuizFlashcardSize();
        executeCommand("good");
        assertEquals(size - 1, getModelQuizFlashcardSize());
        executeCommand("exit");
        assertEquals(0, (int) getModelQuizMode());
    }
}
