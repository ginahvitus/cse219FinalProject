package ssm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static ssm.StartupConstants.CSS_CLASS_LANG_COMBO_BOX;
import static ssm.StartupConstants.CSS_CLASS_LANG_DIALOG_PANE;
import static ssm.StartupConstants.CSS_CLASS_LANG_PROMPT;
import static ssm.StartupConstants.CSS_CLASS_LANG_OK_BUTTON;
import static ssm.StartupConstants.ENGLISH_LANG;
import static ssm.StartupConstants.FINNISH_LANG;
import static ssm.StartupConstants.LABEL_LANGUAGE_SELECTION_PROMPT;
import static ssm.StartupConstants.OK_BUTTON_TEXT;
import static ssm.StartupConstants.STYLE_SHEET_UI;

/**
 *
 * @author McKillaGorilla
 */
public class LanguageSelectionDialog extends Stage {
    GridPane gridPane;
    Label languagePromptLabel;
    ComboBox languageComboBox;
    Button okButton;
    String selectedLanguage = ENGLISH_LANG;
    
    public LanguageSelectionDialog() {
	// SET THE WINDOW TITLE
	this.setTitle(LABEL_LANGUAGE_SELECTION_PROMPT);
	
	// SETUP THE PROMPT
	languagePromptLabel = new Label(LABEL_LANGUAGE_SELECTION_PROMPT);
	
	// INIT THE LANGUAGE CHOICES
	ObservableList<String> languageChoices = FXCollections.observableArrayList();
	languageChoices.add(ENGLISH_LANG);
	languageChoices.add(FINNISH_LANG);
	languageComboBox = new ComboBox(languageChoices);
	languageComboBox.getSelectionModel().select(ENGLISH_LANG);
	okButton = new Button(OK_BUTTON_TEXT);
	
	gridPane = new GridPane();
	gridPane.add(languagePromptLabel, 0, 0, 2, 1);
	gridPane.add(languageComboBox, 0, 1, 1, 1);
	gridPane.add(okButton, 1, 1, 1, 1);
	
	okButton.setOnAction(e -> {
	    selectedLanguage = languageComboBox.getSelectionModel().getSelectedItem().toString();
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
    
    public String getSelectedLanguage() {
	return selectedLanguage;
    }
}
