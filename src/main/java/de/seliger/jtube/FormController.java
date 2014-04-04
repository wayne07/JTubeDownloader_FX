package de.seliger.jtube;

import com.google.common.base.Joiner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.io.File.separator;

/**
 * Created by js on 03.04.14.
 */
public class FormController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(FormController.class);

    public static final DateTimeFormatter MILLIS_FORMATTER = DateTimeFormat.forPattern("yyyyMMddHHmmssSSS").withLocale(Locale.GERMAN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyyMMdd").withLocale(Locale.GERMAN);

    @FXML
    private TextField PathToDll;
    @FXML
    private TextField targetDirectory;
    @FXML
    private TextField urlToSave;
    @FXML
    private TextField filename;
    @FXML
    private Button btnDownload;

    @FXML
    private void doDownload(MouseEvent event) {
        LOGGER.debug("starting Download ...");
        // do download
        File tempFile = createTempFileName();
        doDownloadToTempFile(tempFile, urlToSave.getText());
        extractAudioFrom(tempFile);
        deleteTempFile(tempFile);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        targetDirectory.setText(createTargetDirectory(getUserHome()));
    }

    private String getUserHome() {
        return System.getProperty("user.home");
    }

    private String createTargetDirectory(String userHome) {
        String dayString = DATE_FORMATTER.print(new LocalDate());
        String path = userHome + separator + "Musik" + separator + dayString;
        File target = new File(path);
        target.mkdirs();
        return target.getAbsolutePath();
    }

    private void deleteTempFile(File tempFileName) {

    }

    private File createTempFileName() {
        String systemTempPath = System.getProperty("java.io.tmpdir");
        String randomTimeString = MILLIS_FORMATTER.print(new DateTime());
        String name = systemTempPath + separator + "youtube" + separator + "youtube-dl-" + randomTimeString + ".flv";
        LOGGER.info("Temp-File-Name: " + name);
        return new File(name);
    }

    private void extractAudioFrom(File tempFileName) {
        String command = "ffmpeg -i $x -acodec libmp3lame -ac 2 -ab 128k -vn -y \"$2\"";

    }

    private void doDownloadToTempFile(File tempFile, String urlToFileDownload) {
        String command = "youtube-dl --output=" + tempFile.getAbsolutePath() + " --format=18 \"" + urlToFileDownload + "\"";
        String[] cmdarray = new String[4];
        cmdarray[0] = "youtube-dl";
        cmdarray[1] = "--output=" + tempFile.getAbsolutePath();
        cmdarray[2] = "--format=18";
        cmdarray[3] = urlToFileDownload;
        try {
            executeProcess(cmdarray);
        } catch (IOException e) {
            LOGGER.error("Fehler: " + e.getMessage(), e);
            JOptionPane.showMessageDialog(null, "Das Programm 'youtube-dl' wird benötigt. Bitte Papa rufen ;-) ");
        } catch (InterruptedException e) {
            LOGGER.error("Fehler: " + e.getMessage(), e);
        }

    }

    private void executeProcess(String[] cmdarray) throws IOException, InterruptedException {
        String commandAsString= Joiner.on(" ").join(cmdarray);
        LOGGER.info("executing command: " + commandAsString);
        Process process = Runtime.getRuntime().exec(cmdarray);

        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = input.readLine()) != null) {
            LOGGER.info(line);
        }

        BufferedReader inputError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String lineError = null;
        while ((lineError = inputError.readLine()) != null) {
            LOGGER.warn(lineError);
        }

        int exitVal = process.waitFor();
        LOGGER.info("Exited with error code " + exitVal);
        if(exitVal > 0 ) {
            JOptionPane.showMessageDialog(null, "Der Download ist fehlgeschlagen. Bitte Papa rufen ;-) ");
        }
    }


}

/*
#!/bin/bash
x=~/tmp/youtube/youtube-dl-$RANDOM-$RANDOM.flv
youtube-dl --output=$x --format=18 "$1"
ffmpeg -i $x -acodec libmp3lame -ac 2 -ab 128k -vn -y "$2"
rm $x

http://www.youtube.com/watch?v=COHQuu9Flho
BROILERS - Ist Da Jemand?

 */