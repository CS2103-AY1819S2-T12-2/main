package seedu.address.logic.parser;

import java.io.File;

import seedu.address.logic.commands.UploadCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UploadCommand object
 */
public class UploadCommandParser implements Parser<UploadCommand> {

    public static final String PATH_MESSAGE_CONSTRAINT = "Argument should be a single path associated with a file";
    public static final String FILE_MESSAGE_CONSTRAINT = "File should be a .txt file";

    /**
     * Parses the given {@code String} of arguments in the context of the UploadCommand
     * and returns an UploadCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UploadCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        File file = new File(trimmedArgs);

        if (!file.exists()) {
            throw new ParseException(PATH_MESSAGE_CONSTRAINT);
        }

        return new UploadCommand(file);
    }

}
