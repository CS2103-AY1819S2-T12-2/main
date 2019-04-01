package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.flashcard.Flashcard;

/**
 * Panel containing the list of flashcards.
 */
public class QuizPanel extends UiPart<Region> {
    private static final String FXML = "QuizPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(QuizPanel.class);

    @FXML
    private Label cardsRemaining;

    public QuizPanel(ObservableList<Flashcard> quizCards) {
        super(FXML);
        cardsRemaining.setText(String.valueOf(quizCards.size() + 1));
        quizCards.addListener((ListChangeListener<Flashcard>) change ->
            cardsRemaining.setText(String.valueOf(change.getList().size() + 1)));
    }
}
