package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.StateHistory;

/**
 * Finds and lists all persons in the address book whose field entries each match at least one of the provided regexes.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the previous command or a number of most "
            + "recent commands. Ignores Undo, Redo, and Export commands; affects all other valid commands.\n"
            + "Parameters: [NUMBER_OF_COMMANDS]...\n"
            + "Example: " + COMMAND_WORD + " 5";

    public static final String MESSAGE_SUCCESS = "Undone %1$d / %2$d commands";

    private final int numCommands;
    private StateHistory history = null;

    /**
     * Creates an UndoCommand to undo a given number of previous Commands.
     *
     * @param predicate The predicate
     */
    public UndoCommand(int numCommands) {
        this.numCommands = numCommands;
    }

    @Override
    public void setHistory(StateHistory history) {
        this.history = history;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        requireNonNull(history);
        int undoneCommands = history.undo(numCommands);
        Model undoneModel = history.presentModel();
        model.setAddressBook(undoneModel.getAddressBook());
        model.updateFilteredPersonList(undoneModel.getPredicate());
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, undoneCommands, numCommands), false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand // instanceof handles nulls
                && numCommands == ((UndoCommand) other).numCommands); // state check
    }
}
