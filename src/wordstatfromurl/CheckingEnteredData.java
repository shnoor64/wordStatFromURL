package wordstatfromurl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//название сменить стоит на например inputDataValidator
public class CheckingEnteredData {
    //тут снова сначала надо поля, потом конструктор
    public CheckingEnteredData(String[] arrayEnteredData, String fileDirectory) {
        this.arrayEnteredData = arrayEnteredData;
        this.fileDirectory = fileDirectory;
    }
    private static final Logger LOG = Logger.getLogger(CheckingEnteredData.class.getName());

    private String[] arrayEnteredData;
    private String urlPath;
    private String fileDirectory;

    //метод сразу несколько вещей делает, по названию мы ждем что он просто проверит что то, но он сетит еще другие поля,
    //ниже написал что наверно стоит разделить на два класса, Validator() просто проверит данные,
    //и на PathBuilder. Причем так как мы в OutputOfStatistics пихаем еще и fileResoultName, fileHtmlPageName и fileDirectory
    // то в Validator я бы проверил и их.
    public void checkingLenthData() throws IOException {
        //тут try не нужен, ты выкидываешь и так на верх исключение
            if ((arrayEnteredData.length == 0) || (arrayEnteredData.length > 2)) {
                //не уверен что подходит тип исключения, IOE это ошибки чтения, записи куда то.
                // в данном случае IllegalArgumentException подходит из встроенных
                throw new IOException("Неверно указано количество аргументов");
                //дальше бы я вынес класс PathBuilder()
            } else if (arrayEnteredData.length == 1) {
                urlPath = arrayEnteredData[0];
                LOG.log(Level.INFO, "Выбрана директория файлов по умолчанию: " + fileDirectory);
            } else if (arrayEnteredData.length == 2) {
                fileDirectory = arrayEnteredData[1];
                urlPath = arrayEnteredData[0];
                LOG.log(Level.INFO, "Выбрана директория файлов: " + fileDirectory);
            }


    }
    //тут не много логика размыта, класс делает много сразу, валидирует входящие параметры и потом еще собирает путь
    //предлагаю сделать два класса, Validator() и отдельный PathBuilder()
    public String getUrlPath() {
        return urlPath;
    }
    //предыдущий комент
    public String getFileDirectory() {
        return fileDirectory;
    }

}
