/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.view;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
public class createPageDialog extends Stage {

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
    String selectedType;
    Optional<String> title;
    String text;

    public createPageDialog(String type) {

        // SET THE WINDOW TITLE
        this.setTitle("CREATE PAGE PROMPT");

        String selected;
        // SETUP THE PROMPT
        TextInputDialog dialog = new TextInputDialog("title");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Page Creation Dialog");
        dialog.setContentText("Please enter a Title:");
        selection = dialog.getContentText();

        if (type.equalsIgnoreCase("text")) {
            TextInputDialog content = new TextInputDialog("content");
            dialog.setContentText("Enter some text here:");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            TextField username = new TextField();
            username.setPromptText("Text:");
            grid.add(new Label("Text:"), 0, 0);
            grid.add(username, 40, 0);
            text = content.getContentText();
            
            
            
  

        }

//        languageComboBox.getSelectionModel().select(ENGLISH_LANG);
        okButton = new Button(OK_BUTTON_TEXT);

        gridPane = new GridPane();
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

    public String getSelected() {

        return selection;
    }
}
