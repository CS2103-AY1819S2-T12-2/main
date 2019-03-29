package seedu.address.ui;

import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.flashcard.Flashcard;


/**
 * The Browser Panel of the App.
 */
public class CardViewPanel extends UiPart<Region> {

    private static final String FXML = "CardViewPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private int quizMode;

    @FXML
    private StackPane cardPlaceholder;

    public CardViewPanel(ObservableValue<Flashcard> selectedCard, ObservableValue<Integer> quizMode) {
        super(FXML);

        // Reload page when selected card changes.
        selectedCard.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                loadDefaultPage();
                return;
            }
            loadCardPage(newValue);
        });

        quizMode.addListener(((observableValue, oldValue, newValue) -> {
            this.quizMode = newValue;
        }));

        loadDefaultPage();
    }

    private void loadCardPage(Flashcard flashcard) {
        loadPage(new FlashcardCardView(flashcard, quizMode));
    }

    /**
     * Loads page given uiPart
     *
     * @param uiPart the uiPart to show
     */
    public void loadPage(UiPart<Region> uiPart) {
        cardPlaceholder.getChildren().clear();
        if (uiPart != null) {
            cardPlaceholder.getChildren().add(uiPart.getRoot());
        }
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        loadPage(null);
    }

}
