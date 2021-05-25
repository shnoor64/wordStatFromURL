package wordstatfromurl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputOfStatistics {

    public OutputOfStatistics(String[] args, String fileResoultName, String fileHtmlPageName, String fileDirectory) {
        this.args = args;
        this.fileResoultName = fileResoultName;
        this.fileHtmlPageName = fileHtmlPageName;
        this.fileDirectory = fileDirectory;
    }

    private static final Logger LOG = Logger.getLogger(OutputOfStatistics.class.getName());

    private static String delimeter = "[^A-Za-zА-Яа-я]+";
    private static String fileResoultName;
    private static String fileHtmlPageName;
    private String fileDirectory;
    private static String[] args;

    public void executingOfStatisticsOutput() throws IOException {
        try {

            CheckingEnteredData cED = new CheckingEnteredData(args, fileDirectory);
            cED.checkingLenthData();

            String urlPath = cED.getUrlPath();
            ReadHtml rH = new ReadHtml();
            rH.saveHtml(urlPath, cED.getFileDirectory(), fileHtmlPageName);
            File fileResoult = new File(cED.getFileDirectory() + File.separator + fileResoultName);
            FileWriter fileResoultWrit = new FileWriter(fileResoult);

            StatisticWord stW = new StatisticWord();
            stW.statWordFromFile((cED.getFileDirectory() + File.separator + fileHtmlPageName), delimeter);
            for (Map.Entry<String, Integer> entry : stW.getMap().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            fileResoultWrit.write(stW.getMap().toString());
            fileResoultWrit.close();
            LOG.log(Level.INFO, "Программа завершена успешно");
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Не удалось завершить выполнение программы (" + ex + ")");

        }

    }

}
