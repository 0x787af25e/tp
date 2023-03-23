package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.TxtUtil;

public class TxtHistoryStorage implements HistoryStorage {

    private Path filePath;

    public TxtHistoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getHistoryStoragePath() {
        return this.filePath;
    }

    @Override
    public Optional<String> readHistoryString() throws IOException {
        return readHistoryString(filePath);
    }

    @Override
    public Optional<String> readHistoryString(Path filePath) throws IOException {
        requireNonNull(filePath);

        Optional<String> historyString = TxtUtil.readTxtFile(filePath);
        if (!historyString.isPresent()) {
            return Optional.empty();
        }

        return historyString;
    }

    @Override
    public void saveHistoryString(String historyString) throws IOException{
        saveHistoryString(historyString, filePath);
    }

    @Override
    public void saveHistoryString(String historyString, Path filePath) throws IOException {
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        TxtUtil.saveTxtFile(historyString, filePath);
    }
}
