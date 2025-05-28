package orbitalbase.parser;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataParser {
    private static final String PARAM_DELIMITER = "\\s+";

    public List<List<Integer>> parseData(List<String> lines) {
        if (lines.isEmpty()) {
            log.error("There is not data for parsing.");
        }

        List<List<Integer>> parametersList = new ArrayList<>();

        for (String line : lines) {
            List<Integer> parameters = parseLineToInteger(line);
            log.info("Line is parsed.");
            parametersList.add(parameters);
        }
        return parametersList;
    }

    private List<Integer> parseLineToInteger(String line) {
        line = line.strip();
        String[] valuesStr = line.split(PARAM_DELIMITER);
        List<Integer> values = new ArrayList<>();
        for (String valueStr : valuesStr) {
            int value = Integer.parseInt(valueStr);
            values.add(value);
        }
        return values;
    }
}
