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
            //подумал, это лишний класс, можно сделать метод private void validate() который будет проверять эти все поля.
            //validate надо будет вызвать в конструкторе при инициализации класса.
            //там же в конструкторе проинициализировать все остальные поля и ulrPath (его сначала проверить в validate что это правильная ссылка)
            CheckingEnteredData cED = new CheckingEnteredData(args, fileDirectory);
            cED.checkingLenthData();

            String urlPath = cED.getUrlPath();
            ReadHtml rH /*название переменной я бы не сокращал, хотя бы reader*/ = new ReadHtml(); //название класса надо поменять он HtmlSaver() так как читает а сохраняет страницу.
            //я бы сделал вот так new HtmlSaver(urlPath, FileDirectory, fileHtmlPageName) и у HtmlSaver() сделать метод save() который будет возвращать File...тогда тебе не понадобится
            //вот это File fileResoult = new File(cED.getFileDirectory() + File.separator + fileResoultName); у тебя сразу будет File savedFile = new HtmlSaver(urlPath, cED.getFileDirectory(), fileHtmlPageName).save();
            rH.saveHtml(urlPath, cED.getFileDirectory(), fileHtmlPageName);
            //следующая строка тебе не понадобится если WriterHtml вернет сразу файл, но если все таки решишь использовать как есть то FileWriter надо использовать с автозакрытием ресурсов
            File fileResoult = new File(cED.getFileDirectory() + File.separator + fileResoultName);
            FileWriter fileResoultWrit/*ошибка result, зажал пару букв Writer =)*/ = new FileWriter(fileResoult);

            StatisticWord stW /*не сокращай statisticService норм будет */ = new StatisticWord(); //название класса, StatisticService, давай при создании класса в него положим сразу файл с которым он будет работать new StatisticService(File file /*из строки 43*/);
            //у StatisticService давай сделаем метод calculate() который будет возращать Map<String, Integer>, это мап с результатом.
            //у тебя получится
            File savedHtmlPage = new HtmlSaver(...).save();
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
