package wordstatfromurl;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticWord {
    //читать после того как комментарии прочтал по OutputOfStatistics
    //как писал ранее предлагаю сделать конструктор сюда запихать файл и разделитель
    //в конструкторе проверить что они не null и не empty хотя бы, если что то не так так же IllegalArgumentException выкинуть
    private Map<String, Integer> mapUniqueWords = new HashMap<>();

    //метод переименовать в calculate и сделать что бы он сразу возращал Map
    public void statWordFromFile(String filePath, String delimeter) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            List<String> lines = ParsHtml.extractText(reader); //класс ParsHtml, название, - надо сменить HtmlParser
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
        } catch (IOException ex) {
            //если ты прокидываешь наверерх исключение statWordFromFile(String filePath, String delimeter) throws IOException
            //то этот блок не нужен, так как ты тут ничего не делаешь, не логируешь а просто наверх прокидываешь получится try без catch.
            //в данном случае это по идее оправдано, так как ты используешь try с ресурсами что бы закрыть FileReader reader
            throw ex;
        }
    }
    //это не надо, сразу вернуть следует мапу после подсчета.
    public Map<String, Integer> getMap() {
        return mapUniqueWords;
    }
}
