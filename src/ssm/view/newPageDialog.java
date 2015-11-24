/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import static ssm.StartupConstants.CSS_CLASS_LANG_COMBO_BOX;
import static ssm.StartupConstants.CSS_CLASS_LANG_DIALOG_PANE;
import static ssm.StartupConstants.CSS_CLASS_LANG_OK_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_LANG_PROMPT;
import static ssm.StartupConstants.ENGLISH_LANG;
import static ssm.StartupConstants.FINNISH_LANG;
import static ssm.StartupConstants.LABEL_LANGUAGE_SELECTION_PROMPT;
import static ssm.StartupConstants.OK_BUTTON_TEXT;
import static ssm.StartupConstants.STYLE_SHEET_UI;

/**
 *
 * @author Huxton
 */
public class newPageDialog extends Stage {

    // GUI CONTROLS FOR OUR DIALOG
    VBox messagePane;
    Scene messageScene;
    Label messageLabel;
    Button yesButton;
    Button noButton;
    Button cancelButton;
    String selection;

    // CONSTANT CHOICES
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String CANCEL = "Cancel";

    /**
     * Initializes this dialog so that it can be used repeatedly for all kinds
     * of messages.
     *
     * @param primaryStage The owner of this modal dialog.
     */
    GridPane gridPane;
    Label languagePromptLabel;
    ComboBox languageComboBox;
    Button okButton;
    String selectedLanguage = ENGLISH_LANG;
    String selectedType = "text";

    public newPageDialog() {

        // SET THE WINDOW TITLE
        this.setTitle("NEW PAGE PROMPT");

        // SETUP THE PROMPT
        languagePromptLabel = new Label("SELECT A TYPE");

        // INIT THE LANGUAGE CHOICES
        ObservableList<String> languageChoices = FXCollections.observableArrayList();
//        languageChoices.add(ENGLISH_LANG);
//        languageChoices.add(FINNISH_LANG);
        languageChoices.add("video");
        languageChoices.add("text");
        languageChoices.add("slideshow");
        languageChoices.add("image");
        languageChoices.add("list");
        
        languageComboBox = new ComboBox(languageChoices);
//        languageComboBox.getSelectionModel().select(ENGLISH_LANG);
        okButton = new Button(OK_BUTTON_TEXT);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        gridPane.add(languagePromptLabel, 0, 0, 2, 1);
        gridPane.add(languageComboBox, 0, 1, 1, 1);
        gridPane.add(okButton, 1, 1, 1, 1);

        okButton.setOnAction(e -> {
            selectedType = languageComboBox.getSelectionModel().getSelectedItem().toString();
            this.hide();
        });

        // SPECIFY STYLE CLASSES
        gridPane.getStyleClass().add(CSS_CLASS_LANG_DIALOG_PANE);
        languagePromptLabel.getStyleClass().add(CSS_CLASS_LANG_PROMPT);
        languageComboBox.getStyleClass().add(CSS_CLASS_LANG_COMBO_BOX);
        okButton.getStyleClass().add(CSS_CLASS_LANG_OK_BUTTON);

        // NOW SET THE SCENE IN THIS WINDOW
        Scene scene = new Scene(gridPane);
        scene.getStylesheets().add(STYLE_SHEET_UI);
        setScene(scene);
    }

    public String getSelectedType() {
        
        return selectedType;
    }
}
