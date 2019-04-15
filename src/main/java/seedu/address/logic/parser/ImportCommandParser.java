package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PATH;

import java.io.File;

import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ImportCommand object
 */
public class ImportCommandParser implements Parser<ImportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ImportCommand
     * and returns an ImportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ImportCommand parse(String args) throws ParseException {
        String path = args.trim();

        File file = new File(path);
        if (!file.exists() || file.isDirectory() || !path.endsWith(".txt")) {
            throw new ParseException(String.format(MESSAGE_INVALID_PATH, ImportCommand.MESSAGE_USAGE));
        }

        return new ImportCommand(file);
    }

}
