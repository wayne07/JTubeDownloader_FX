package de.seliger.jtube;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by js on 03.04.14.
 */
public class FormController implements Initializable {

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
         System.out.println("hallooo  WELT .........");
        // do download
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userHome = System.getProperty("user.home");
        String targetDirectoryString = createTargetDirectory(userHome);
        targetDirectory.setText(targetDirectoryString);

    }

    private String createTargetDirectory(String userHome) {
        String dayString = DATE_FORMATTER.print(new LocalDate());
        String path = userHome + File.separator + "Musik" + File.separator + dayString;
        File target = new File(path);
        target.mkdirs();
        return target.getAbsolutePath();
    }
}
