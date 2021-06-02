package wordstatfromurl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputOfStatistics {

    private static final String delimeter = "[^A-Za-zА-Яа-я]+";
    private static final Logger LOG = Logger.getLogger(OutputOfStatistics.class.getName());

    private final String[] args;
    private final String fileResoultName;
    private final String fileHtmlPageName;
    private final String fileDirectory;

    public OutputOfStatistics(String[] args, String fileResoultName, String fileHtmlPageName, String fileDirectory) {
        this.args = args;
        this.fileResoultName = fileResoultName;
        this.fileHtmlPageName = fileHtmlPageName;
        this.fileDirectory = fileDirectory;
    }

    public void executingOfStatisticsOutput() throws IOException {
        try {

            CheckingEnteredData checkEntreryData = new CheckingEnteredData(args, fileDirectory);
            checkEntreryData.checkingLenthData();

            String urlPath = checkEntreryData.getUrlPath();
            HtmlSaver htmlSaver = new HtmlSaver();
            htmlSaver.saveHtml(urlPath, checkEntreryData.getFileDirectory(), fileHtmlPageName);
            File fileResoult = new File(checkEntreryData.getFileDirectory() + File.separator + fileResoultName);
            FileWriter fileResoultWrit = new FileWriter(fileResoult);

            StatisticWord statWord = new StatisticWord();
            statWord.statWordFromFile((checkEntreryData.getFileDirectory() + File.separator + fileHtmlPageName), delimeter);
            for (Map.Entry<String, Integer> entry : statWord.getMapUniqueWords().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            fileResoultWrit.write(statWord.getMapUniqueWords().toString());
            fileResoultWrit.close();
            LOG.log(Level.INFO, "Программа завершена успешно");
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Не удалось завершить выполнение программы (" + ex + ")");

        }

    }

}
