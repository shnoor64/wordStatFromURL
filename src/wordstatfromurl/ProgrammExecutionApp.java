package wordstatfromurl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgrammExecutionApp {

    private static final Logger LOG = Logger.getLogger(ProgrammExecutionApp.class.getName());

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {

            String delimeter = "[^A-Za-zА-Яа-я]+";
            String fileResoultName = "resoult.txt";
            String fileHtmlPageName = "htmlPage.html";
            String fileDirectory = "D:\\downloads";

            if ((args.length == 0)||(args.length>2)) {
                throw new ArgumentLenthException("Неверно указано количество аргументов");
            } else if (args.length == 1) {
                LOG.log(Level.INFO, "Выбрана директория файлов по умолчанию: " + fileDirectory);
            } else if (args.length == 2) {
                fileDirectory = args[1];
                LOG.log(Level.INFO, "Выбрана директория файлов: " + fileDirectory);
            }
            String urlPath = args[0];
            
            LOG.log(Level.INFO, "Программа запущена, начат анализ: " + urlPath);
            LOG.log(Level.INFO, "Производится чтение страницы и запись в файл:  " + fileDirectory + File.separator + fileHtmlPageName);
            ReadHtml rH = new ReadHtml();
            rH.saveHtml(urlPath, fileDirectory, fileHtmlPageName);
            LOG.log(Level.INFO, "Чтение страницы и запись в файл завершено:  " + fileDirectory + File.separator + fileHtmlPageName);
            File fileResoult = new File(fileDirectory + File.separator + fileResoultName);
            FileWriter fileResoultWrit = new FileWriter(fileResoult);

            StatisticWord stW = new StatisticWord();
            LOG.log(Level.INFO, "Производится формирование статиcтики по колличеству уникальных слов");
            stW.statWordFromFile((fileDirectory + File.separator + fileHtmlPageName), delimeter);
            for (Map.Entry<String, Integer> entry : stW.map.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            LOG.log(Level.INFO, "Производится запись статистики в файл: " + fileDirectory + File.separator + fileResoultName);
            fileResoultWrit.write(stW.map.toString());
            fileResoultWrit.close();
            LOG.log(Level.INFO, "Запись статистики в файл завершена: " + fileDirectory + File.separator + fileResoultName);
            LOG.log(Level.INFO, "Программа завершена успешно");
        } catch (ArgumentLenthException ex) {
            LOG.log(Level.WARNING, "Указано 0 или более 2х входных параметров(" + ex + ")");
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Не удалось завершить выполнение программы (" + ex + ")");
        
        }

    }

}

class ArgumentLenthException extends IOException {

    public ArgumentLenthException(String massage) {
        super(massage);
    }
}
