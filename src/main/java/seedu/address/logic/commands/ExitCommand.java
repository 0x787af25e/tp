package seedu.address.logic.commands;

import seedu.address.model.Model;

import java.util.List;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final List<String> COMMAND_WORD = List.of(new String[]{"exit"});

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
