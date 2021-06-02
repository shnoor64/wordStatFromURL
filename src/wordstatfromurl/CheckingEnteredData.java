package wordstatfromurl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingEnteredData {

    private String[] arrayEnteredData;
    private String urlPath;
    private String fileDirectory;
    private static final Logger LOG = Logger.getLogger(CheckingEnteredData.class.getName());

    public CheckingEnteredData(String[] arrayEnteredData, String fileDirectory) {
        this.arrayEnteredData = arrayEnteredData;
        this.fileDirectory = fileDirectory;
    }

    public void checkingLenthData() throws IOException {
   
            if ((arrayEnteredData.length == 0) || (arrayEnteredData.length > 2)) {
                throw new IllegalArgumentException("Неверно указано количество аргументов");
            } else if (arrayEnteredData.length == 1) {
                urlPath = arrayEnteredData[0];
                LOG.log(Level.INFO, "Выбрана директория файлов по умолчанию: " + fileDirectory);
            } else if (arrayEnteredData.length == 2) {
                fileDirectory = arrayEnteredData[1];
                urlPath = arrayEnteredData[0];
                LOG.log(Level.INFO, "Выбрана директория файлов: " + fileDirectory);
            }
      

    }

    public String getUrlPath() {
        return urlPath;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

}
