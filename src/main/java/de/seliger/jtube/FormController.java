package de.seliger.jtube;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by js on 03.04.14.
 */
public class FormController implements Initializable {

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
    }
}
