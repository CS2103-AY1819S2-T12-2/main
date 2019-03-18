package seedu.address.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.flashcard.Flashcard;

public class FlashcardCardView extends UiPart<Region> {

    private static final String FXML = "FlashcardCardView.fxml";

    public final Flashcard flashcard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label frontFace;
    @FXML
    private Label backFace;

    public FlashcardCardView(Flashcard flashcard) {
        super(FXML);
        this.flashcard = flashcard;
        frontFace.setText(flashcard.getFrontFace().text);
        backFace.setText(flashcard.getBackFace().text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlashcardCardView)) {
            return false;
        }
        FlashcardCardView that = (FlashcardCardView) o;
        return flashcard.equals(that.flashcard) &&
            cardPane.equals(that.cardPane) &&
            frontFace.equals(that.frontFace) &&
            backFace.equals(that.backFace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flashcard, cardPane, frontFace, backFace);
    }
}
