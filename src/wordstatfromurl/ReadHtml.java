package wordstatfromurl;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadHtml {

    private static final Logger LOG = Logger.getLogger(ReadHtml.class.getName());

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
        } catch (UnknownHostException ex) {
            LOG.log(Level.WARNING, "URL не доступен, проверьте подключение к сети интернет (" + ex + ")");
            throw ex;
        } catch (ConnectException ex) {
            LOG.log(Level.WARNING, "Превышен таймаут ожидания, проверьте правильность ввода URL (" + ex + ")");
            throw ex;
        } catch (IOException ex) {
            LOG.log(Level.WARNING, "Данные не доступны (" + ex + ")");
            throw ex;
        }
    }
}
