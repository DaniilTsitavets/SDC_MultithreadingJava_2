package orbitalbase.reader;


import lombok.extern.slf4j.Slf4j;
import orbitalbase.exception.CustomException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Slf4j
public class DataReader {

    public List<String> readData(String filePath) throws CustomException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getFile());

        List<String> lines;
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = bufferedReader.lines().collect(Collectors.toList());
            log.info("File was read.");
        } catch (IOException e) {
            log.error("Cannot read file.", e);
            throw new CustomException("Cannot read file.", e);
        }
        return lines;
    }
}