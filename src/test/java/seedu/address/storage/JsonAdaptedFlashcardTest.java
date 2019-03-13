package seedu.address.storage;

import static org.junit.Assert.assertEquals;
import static seedu.address.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.TypicalFlashcards.HELLO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.flashcard.Address;
import seedu.address.model.flashcard.Email;
import seedu.address.model.flashcard.Name;
import seedu.address.model.flashcard.Phone;
import seedu.address.testutil.Assert;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_FRONTFACE = HELLO.getFrontFace().text;
    private static final String VALID_BACKFACE = HELLO.getBackFace().text;
    private static final List<JsonAdaptedTag> VALID_TAGS = HELLO.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(BENSON);
//        assertEquals(BENSON, flashcard.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard =
//            new JsonAdaptedFlashcard(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
//        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
//            VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard =
//            new JsonAdaptedFlashcard(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
//        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
//            VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard =
//            new JsonAdaptedFlashcard(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
//        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
//            VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard =
//            new JsonAdaptedFlashcard(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
//        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
//        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
//            VALID_TAGS);
//        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
//        Assert.assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
//        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
//        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
//        JsonAdaptedFlashcard flashcard =
//            new JsonAdaptedFlashcard(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags);
//        Assert.assertThrows(IllegalValueException.class, flashcard::toModelType);
    }

}
