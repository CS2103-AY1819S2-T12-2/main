package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.flashcard.FindCommandPredicate;
import seedu.address.model.flashcard.Name;
import seedu.address.model.flashcard.NameContainsKeywordsPredicate;
import seedu.address.model.flashcard.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) && !arePrefixesPresent(argMultimap, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Set<Name> nameList = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        ArrayList<String> nameKeywords = new ArrayList<>();
        ArrayList<String> tagKeywords = new ArrayList<>();

        for (Name name : nameList) {
            String[] nameSplit = name.toString().split("\\s+");
            for(String nameString : nameSplit) {
                nameKeywords.add(nameString);
            }
        }

        for (Tag tag : tagList) {
            String[] tagSplit = tag.toString().split("\\s+");
            for(String tagString : tagSplit) {
                tagKeywords.add(tagString);
            }
        }

        return new FindCommand(new FindCommandPredicate(nameKeywords, tagKeywords));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
