package wordstatfromurl; //обычно пакеты называют как сайт наоборот и добавляют смысловую нагрузку что это за пакет в
//в данном случае например com.github.shnoor64.wordstat например

import java.io.IOException;

public class WordStatFromURLApplication {

    private final static String fileResoultName = "result.txt"; //по конвенции если final static то это как бы константа,
    // принято писать в виде FILE_RESULT_NAME = ...
    private final static String fileHtmlPageName = "htmlPage.html";
    private final static String fileDirectory = "D:\\downloads";

    public static void main(String[] args) throws IOException {
        OutputOfStatistics prExApp = new OutputOfStatistics(args, fileResoultName, fileHtmlPageName, fileDirectory);
        /*prExApp не читается название, я думаю не стоит сильно сокращать, - App понятно всегда.
        само название класса не очень понятно. Можно взять обратить внимание на служебные классы которые что то делают в JVM.
         OtputWriter, FileReader, Iterator. Те сразу понятно этот класс куда то пишет, этот класс читает из файла, этот класс
         для итерирования по чему то. Это наш случай надо назвать так что бы отражал что он делает, в данном случае например
         WebPageStatisticsService те сервис который делает какуюто статистику для web страницы
         */

        prExApp.executingOfStatisticsOutput();
    }
}
