package wordstatfromurl;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HtmlSaver {

    public void saveHtml(String urlPath, String fileDirectory, String fileName) throws IOException {
        URL url = new URL(urlPath);
        try (BufferedReader bufferFromUrl = new BufferedReader(new InputStreamReader(url.openStream()))) {
            new File(fileDirectory).mkdirs();
            try (PrintWriter writer = new PrintWriter((fileDirectory + "\\" + fileName), StandardCharsets.UTF_8)) {
                String inputLine;
                while ((inputLine = bufferFromUrl.readLine()) != null) {
                    writer.println(inputLine);
                }
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
}
