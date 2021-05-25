package wordstatfromurl;

import java.io.IOException;

public class WordStatFromURLApplication {

    private final static String fileResoultName = "result.txt";
    private final static String fileHtmlPageName = "htmlPage.html";
    private final static String fileDirectory = "D:\\downloads";

    public static void main(String[] args) throws IOException {
        OutputOfStatistics prExApp = new OutputOfStatistics(args, fileResoultName, fileHtmlPageName, fileDirectory);
        prExApp.executingOfStatisticsOutput();
    }
}
