package wordstatfromurl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckingEnteredData {

    public CheckingEnteredData(String[] arrayEnteredData, String fileDirectory) {
        this.arrayEnteredData = arrayEnteredData;
        this.fileDirectory = fileDirectory;
    }
    private static final Logger LOG = Logger.getLogger(CheckingEnteredData.class.getName());

    private String[] arrayEnteredData;
    private String urlPath;
    private String fileDirectory;

    public void checkingLenthData() throws IOException {
        try {
            if ((arrayEnteredData.length == 0) || (arrayEnteredData.length > 2)) {
                throw new IOException("Неверно указано количество аргументов");
            } else if (arrayEnteredData.length == 1) {
                urlPath = arrayEnteredData[0];
                LOG.log(Level.INFO, "Выбрана директория файлов по умолчанию: " + fileDirectory);
            } else if (arrayEnteredData.length == 2) {
                fileDirectory = arrayEnteredData[1];
                urlPath = arrayEnteredData[0];
                LOG.log(Level.INFO, "Выбрана директория файлов: " + fileDirectory);
            }
        } catch (IOException ex) {
            throw ex;
        }

    }

    public String getUrlPath() {
        return urlPath;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

}
