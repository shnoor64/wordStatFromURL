package wordstatfromurl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticWord {

    Map<String, Integer> map = new HashMap<>();

    public void statWordFromFile(String filePath, String delimeter) {
        try(FileReader reader = new FileReader(filePath)) {
            List<String> lines = ParsHtml.extractText(reader);
            for (String line : lines) {
                String[] bufferLine;
                bufferLine = line.strip().split(delimeter);
                for (String wordFromLine : bufferLine) {
                    if (wordFromLine.isEmpty()) {
                        continue;
                    }
                    String wordInUpperCase = wordFromLine.toUpperCase();
                    String stripedWord = wordInUpperCase.strip();
                    if (map.get(stripedWord) == null) {
                        map.put(stripedWord, 1);
                    } else {
                        map.put(stripedWord, map.get(wordInUpperCase) + 1);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден (" + ex + ")");
        } catch (IOException ex) {
            System.out.println("Данные файла не доступны (" + ex + ")");
        }
    }
}
