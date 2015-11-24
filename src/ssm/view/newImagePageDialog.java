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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import static ssm.StartupConstants.CSS_CLASS_LANG_COMBO_BOX;
import static ssm.StartupConstants.CSS_CLASS_LANG_DIALOG_PANE;
import static ssm.StartupConstants.CSS_CLASS_LANG_OK_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_LANG_PROMPT;
import static ssm.StartupConstants.ENGLISH_LANG;
import static ssm.StartupConstants.OK_BUTTON_TEXT;
import static ssm.StartupConstants.STYLE_SHEET_UI;

/**
 *
 * @author Huxton
 */
public class newImagePageDialog extends Stage {

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

    public newImagePageDialog() {

        // SET THE WINDOW TITLE
        this.setTitle("NEW Image PROMPT");

        okButton = new Button(OK_BUTTON_TEXT);

        TextField title = new TextField();
        title.setPromptText("title");
        TextField text = new TextField();
        text.setPromptText("text");
        Button applyButton = new Button("Choose File");
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        gridPane.add(new Label("Select Font:"), 1, 1, 1, 1);
        gridPane.add(languageComboBox, 1, 2, 1, 1);
        gridPane.add(languagePromptLabel, 1, 4, 1, 1);
        gridPane.add(new Label("Title:"), 1, 5, 1, 1);
        gridPane.add(title, 1, 6, 1, 1);
        gridPane.add(new Label("Caption:"), 1, 7, 1, 1);
        gridPane.add(text, 1, 8, 5, 5);
        gridPane.add(applyButton, 10, 16, 1, 1);
        gridPane.add(okButton, 14, 16, 1, 1);

        applyButton.setOnAction(e -> {
            javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.showOpenDialog(null);
        });

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
