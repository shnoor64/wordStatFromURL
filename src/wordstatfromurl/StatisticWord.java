package wordstatfromurl;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticWord {

    private Map<String, Integer> mapUniqueWords = new HashMap<>();

    public Map <String, Integer> statWordFromFile(String filePath, String delimeter) throws IOException {

        try (FileReader reader = new FileReader(filePath)) {
            List<String> lines = HtmlParser.extractText(reader);
            for (String line : lines) {
                String[] bufferLine;
                bufferLine = line.strip().split(delimeter);
                for (String wordFromLine : bufferLine) {
                    if (wordFromLine.isEmpty()) {
                        continue;
                    }
                    String wordInUpperCase = wordFromLine.toUpperCase();
                    String stripedWord = wordInUpperCase.strip();
                    if (mapUniqueWords.get(stripedWord) == null) {
                        mapUniqueWords.put(stripedWord, 1);
                    } else {
                        mapUniqueWords.put(stripedWord, mapUniqueWords.get(wordInUpperCase) + 1);
                    }
                }
            }
            return mapUniqueWords;
        } catch (IOException ex) {
            throw ex;
        }
    }

    public Map<String, Integer> getMapUniqueWords() {
        return mapUniqueWords;
    }
}
