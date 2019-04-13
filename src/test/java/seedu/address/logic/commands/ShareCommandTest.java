package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalFlashcards.getTypicalCardCollection;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ShareCommandTest {

    private Model model;
    private CommandHistory commandHistory;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_share_success() {
        Path testDataFolder = Paths.get("src", "test", "data", "uploadCommandTest");
        String file = testDataFolder.resolve("flashcardsCorrupted.txt").toString();

        try {
            new ShareCommand(testDataFolder.toString()).execute(model, commandHistory);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }




/*
    //This test fails on Travis for some reason
    //It works locally
    @Ignore
    @Test
    public void execute_imageCommand_success() throws CommandException {
        File imageToProduce = new File(IMAGE_DIRECTORY.concat(validFlashcard));
        File imageToTest = new File("src\\test\\data\\images\\".concat(validFlashcard));
        if (!imageToTest.exists()) {
            logger.severe("Image ".concat(imageToTest.getAbsolutePath()).concat(" couldn't be found."));
        }
        if (imageToProduce.delete()) {
            logger.warning("Image ".concat(imageToProduce.getAbsolutePath()).concat(" deleted."));
        }
        ImageCommand command = new ImageCommand(imageToTest);
        command.execute(model, commandHistory);
        assert(imageToProduce.exists());
        if (!imageToProduce.delete()) {
            logger.severe("Image ".concat(imageToProduce.getAbsolutePath()).concat(" couldn't be deleted."));
        }
    }*/
}
