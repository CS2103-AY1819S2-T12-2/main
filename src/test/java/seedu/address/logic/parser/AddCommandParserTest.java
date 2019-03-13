package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.BACKFACE_DESC_GOOD;
import static seedu.address.logic.commands.CommandTestUtil.FRONTFACE_DESC_GOOD;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CHINESE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INDONESIAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHINESE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INDONESIAN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalFlashcards.GOOD;

import org.junit.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.testutil.FlashcardBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Flashcard expectedFlashcard = new FlashcardBuilder(GOOD).withTags(VALID_TAG_CHINESE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FRONTFACE_DESC_GOOD + BACKFACE_DESC_GOOD
            + TAG_DESC_CHINESE, new AddCommand(expectedFlashcard));

        // multiple tags - all accepted
        Flashcard expectedFlashcardMultipleTags = new FlashcardBuilder(GOOD).withTags(VALID_TAG_INDONESIAN,
            VALID_TAG_CHINESE).build();
        assertParseSuccess(parser, FRONTFACE_DESC_GOOD + BACKFACE_DESC_GOOD + TAG_DESC_CHINESE
            + TAG_DESC_INDONESIAN, new AddCommand(expectedFlashcardMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Flashcard expectedFlashcard = new FlashcardBuilder(GOOD).withTags().build();
        assertParseSuccess(parser, FRONTFACE_DESC_GOOD + BACKFACE_DESC_GOOD,
            new AddCommand(expectedFlashcard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
//            expectedMessage);
//
//        // missing phone prefix
//        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
//            expectedMessage);
//
//        // missing email prefix
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
//            expectedMessage);
//
//        // missing address prefix
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
//            expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
//            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
//            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
//
//        // invalid phone
//        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
//            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
//
//        // invalid email
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
//            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
//
//        // invalid address
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
//            + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
//            + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
//
//        // two invalid values, only first invalid value reported
//        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
//            Name.MESSAGE_CONSTRAINTS);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
