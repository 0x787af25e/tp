package seedu.address.logic.commands;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.StateHistory;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    public static void saveToFile() {
    }

    public static void addWord(String shortForm) {
    }

    /**
     * Sets the StateHistory for this command to refer to.
     *
     * @param history StateHistory to use
     */
    public void setHistory(StateHistory history) {
        // Do nothing
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;


}
