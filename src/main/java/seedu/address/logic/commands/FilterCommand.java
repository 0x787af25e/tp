package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.FieldsMatchRegexPredicate;

/**
 * Finds and lists all persons in the address book whose field entries each match at least one of the provided regexes.
 */
public class FilterCommand extends Command {

    public static List<String> COMMAND_WORDS = new ArrayList<String>(Arrays.asList("filter", "fil"));

    private static final Path p = Paths.get("data", "filterCommand.txt");

    public static final String MESSAGE_USAGE = COMMAND_WORDS + ": Filters all persons whose every field matches "
            + "at least one respective regex filter, and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_NAME + "NAME] [" + PREFIX_NAME + "MORE_NAMES] [" + PREFIX_PHONE + "PHONE]...\n"
            + "Example: " + COMMAND_WORDS + " "
            + PREFIX_NAME + "Al "
            + PREFIX_ADDRESS + "[0-9] "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "colleagues";

    private final FieldsMatchRegexPredicate predicate;

    /**
     * Creates a FilterCommand to filter the model for persons satisfying a supplied predicate.
     *
     * @param predicate The predicate
     */
    public FilterCommand(FieldsMatchRegexPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                true, true);
    }

    public static void saveWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }

        try {
            FileOutputStream fos = new FileOutputStream(p.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(COMMAND_WORDS);
            oos.close();
        } catch (IOException ignored) {}
    }

    public static void loadWords() {
        if (!Files.exists(p)) {
            try {
                Files.createFile(p);
            } catch (java.io.IOException ignored) {}
        }
        try {
            FileInputStream fis = new FileInputStream(p.toFile());
            ObjectInputStream ois = new ObjectInputStream(fis);
            COMMAND_WORDS = (List<String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException ignored) {}
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
