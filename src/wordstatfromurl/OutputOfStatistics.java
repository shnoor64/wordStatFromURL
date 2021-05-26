package wordstatfromurl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutputOfStatistics {

    /*По конвенции обычно идет, сначало поля статические класса, потом поля класса, потом конструктор*/
    public OutputOfStatistics(String[] args, String fileResoultName, String fileHtmlPageName, String fileDirectory) {
        this.args = args;
        //
        //так не пойдет, у тебя поля объявлены как private STATIC,
        // а ты обращается к ним через this, я лошара и не могу объяснить почему это не ломается на этапе компиляции =)
        // в общем если this то поля не должны быть static, idea подсвечивает это
        this.fileResoultName = fileResoultName; //ошибка в Result
        this.fileHtmlPageName = fileHtmlPageName;
        this.fileDirectory = fileDirectory;
    }

    private static final Logger LOG = Logger.getLogger(OutputOfStatistics.class.getName());

    private static String delimeter = "[^A-Za-zА-Яа-я]+"; //это все правильно можно так же сделать final и написать DELIMITER
    private static String fileResoultName; //не должен быть static
    private static String fileHtmlPageName; //тоже не должен быть статик
    private String fileDirectory; //если поля инициализируются через конструктор то обычно их делают private final
    private static String[] args; //и этот не должен быть статик.

    public void executingOfStatisticsOutput() throws IOException {
        try {

            CheckingEnteredData cED = new CheckingEnteredData(args, fileDirectory);
            cED.checkingLenthData(); //это я бы перенес в конструктор, при создании экземпляра OutputOfStatistics
            //перед инитом полей проверяем их на корректность. Раз нам надо в приложении дальше использовать new CheckingEnteredData
            //то под него отдельное поле завести в классе (OutputOfStatistics). Еще опять же название класса предлагаю назвать InputDataValidator

            String urlPath = cED.getUrlPath();
            ReadHtml rH /*название переменной я бы не сокращал, хотя бы reader*/ = new ReadHtml(); //название класса надо поменять он HtmlSaver() так как читает а сохраняет страницу.
            //я бы сделал вот так new HtmlSaver(urlPath, cED.getFileDirectory(), fileHtmlPageName) и у HtmlSaver() сделать метод save() который будет возвращать File...тогда тебе не понадобится
            //вот это File fileResoult = new File(cED.getFileDirectory() + File.separator + fileResoultName); у тебя сразу будет File savedFile = new HtmlSaver(urlPath, cED.getFileDirectory(), fileHtmlPageName).save();
            rH.saveHtml(urlPath, cED.getFileDirectory(), fileHtmlPageName);
            File fileResoult = new File(cED.getFileDirectory() + File.separator + fileResoultName); //лучше сделать как написал на 44 .под cED.getFileDirectory() + File.separator + fileResoultName
            // под это у тебя будет отдельный класс PathBuilder() (см комменты в CheckingEnteredData) где будет метод buildResultPath(), например, который вернет тебе путь сконкатенированный
            FileWriter fileResoultWrit/*ошибка result, зажал пару букв Writer =)*/ = new FileWriter(fileResoult); //нужно использовать autoclosable те try (FileWriter fileResultWrit = new FileWriter(fileResoult))

            StatisticWord stW /*не сокращай statisticService норм будет */ = new StatisticWord(); //название класса, StatisticService, давай при создании класса в него положим сразу файл с которым он будет работать new StatisticService(File file /*из строки 43*/);
            //у StatisticService давай сделаем метод calculate() который будет возращать Map<String, Integer>, это мап с результатом.
            //у тебя получится
            PathBuilder pathBuilder = new PathBuilder(fileResoultName, fileHtmlPageName, fileDirectory//мб еще что что бы построить путь);
            File savedHtmlPage = new HtmlSaver(pathBuilder.buildResultPath()).save();
            StaisticService staisticService = new StaisticService(savedHtmlPage, delimeter);
            Map<String, Integer> calculatedResult = staisticService.calculate();

            stW.statWordFromFile((cED.getFileDirectory() + File.separator + fileHtmlPageName), delimeter);

            //можно сделать отдельный класс под это ResultPrinter(Map<String, Integer> calculatedResult)
            //у этого класса сделать метод print() в который поместить этот цикл
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
