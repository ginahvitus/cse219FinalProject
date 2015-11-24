package ssm.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.LABEL_SLIDESHOW_FOOTER;
import static ssm.LanguagePropertyType.LABEL_SLIDESHOW_TITLE;
import static ssm.LanguagePropertyType.TOOLTIP_ADD_SLIDE;
import static ssm.LanguagePropertyType.TOOLTIP_EXIT;
import static ssm.LanguagePropertyType.TOOLTIP_LOAD_SLIDE_SHOW;
import static ssm.LanguagePropertyType.TOOLTIP_MOVE_DOWN;
import static ssm.LanguagePropertyType.TOOLTIP_MOVE_UP;
import static ssm.LanguagePropertyType.TOOLTIP_NEW_SLIDE_SHOW;
import static ssm.LanguagePropertyType.TOOLTIP_REMOVE_SLIDE;
import static ssm.LanguagePropertyType.TOOLTIP_SAVE_SLIDE_SHOW;
import static ssm.LanguagePropertyType.TOOLTIP_VIEW_SLIDE_SHOW;
import static ssm.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_PANE;
import static ssm.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_PANE;
import static ssm.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW;
import static ssm.StartupConstants.CSS_CLASS_SLIDES_EDITOR_PANE;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_EDIT_VIEW;
import static ssm.StartupConstants.CSS_CLASS_TITLE_PANE;
import static ssm.StartupConstants.CSS_CLASS_TITLE_PROMPT;
import static ssm.StartupConstants.CSS_CLASS_TITLE_TEXT_FIELD;
import static ssm.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_WORKSPACE;
import static ssm.StartupConstants.ICON_ADD_SLIDE;
import static ssm.StartupConstants.ICON_EXIT;
import static ssm.StartupConstants.ICON_EXPORT;
import static ssm.StartupConstants.ICON_LOAD_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_MOVE_DOWN;
import static ssm.StartupConstants.ICON_MOVE_UP;
import static ssm.StartupConstants.ICON_NEW_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_REMOVE_SLIDE;
import static ssm.StartupConstants.ICON_SAVE_AS;
import static ssm.StartupConstants.ICON_SAVE_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_VIEW_SLIDE_SHOW;
import static ssm.StartupConstants.PATH_ICONS;
import static ssm.StartupConstants.STYLE_SHEET_UI;
import ssm.controller.FileController;
import ssm.controller.SlideShowEditController;
import ssm.model.Slide;
import ssm.model.SlideShowModel;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import ssm.file.SlideShowSiteExporter;

/**
 * This class provides the User Interface for this application, providing
 * controls and the entry points for creating, loading, saving, editing, and
 * viewing slide shows.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowMakerView {

    // THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH
    Stage primaryStage;
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane ssmPane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    FlowPane fileToolbarPane;
    Button newSlideShowButton;
    Button loadSlideShowButton;
    Button saveSlideShowButton;
    Button viewSlideShowButton;
    Button exitButton;

    Button newText;
    Button newSlideShow;
    Button newImage;
    Button newList;
    Button newVideo;

    newPageDialog n;
    newImagePageDialog ipd;
    newTextPageDialog tpd;
    newSlideShowDialog ssd;
    newVideoPageDialog vpd;
    newListDialog ld;

    FlowPane top;

//
// tab.setText("new tab");
// tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
// pages.getTabs().add(tab);
//    
    //NOTE:
    Button saveAsButton;
    Button exportButton;

    Button addSlideButton;
    Button removeSlideButton;
    Button moveSlideUpButton;
    Button moveSlideDownButton;

    // WORKSPACE
    BorderPane workspace;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    FlowPane slideEditToolbar;
//    Button addSlideButton;
//    Button removeSlideButton;
//    Button moveSlideUpButton;
//    Button moveSlideDownButton;
    // FOR THE SLIDE SHOW TITLE
    FlowPane titlePane;
    Label titleLabel;
    TextField titleTextField;

    // NOTE:
    // FOR THE FOOTER
    FlowPane footerPane;
    Label footerLabel;
    TextField footerTextField;

    // AND THIS WILL GO IN THE CENTER
    ScrollPane slidesEditorScrollPane;
    VBox slidesEditorPane;
//    TabPane tabPane;

    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
    SlideShowModel slideShow;

    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
    SlideShowFileManager fileManager;

    // THIS IS FOR EXPORTING THE SLIDESHOW SITE
    SlideShowSiteExporter siteExporter;

    // THIS CLASS WILL HANDLE ALL ERRORS FOR THIS PROGRAM
    private ErrorHandler errorHandler;

    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
    private FileController fileController;

    // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
    private SlideShowEditController editController;

    /**
     * Default constructor, it initializes the GUI for use, but does not yet
     * load all the language-dependent controls, that needs to be done via the
     * startUI method after the user has selected a language.
     */
    public SlideShowMakerView(SlideShowFileManager initFileManager,
            SlideShowSiteExporter initSiteExporter) {
        // FIRST HOLD ONTO THE FILE MANAGER
        fileManager = initFileManager;

        // AND THE SITE EXPORTER
        siteExporter = initSiteExporter;

        // MAKE THE DATA MANAGING MODEL
        slideShow = new SlideShowModel(this);

        // WE'LL USE THIS ERROR HANDLER WHEN SOMETHING GOES WRONG
        errorHandler = new ErrorHandler(this);
    }

    // ACCESSOR METHODS
    public SlideShowModel getSlideShow() {
        return slideShow;
    }

    public Stage getWindow() {
        return primaryStage;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    /**
     * Initializes the UI controls and gets it rolling.
     *
     * @param initPrimaryStage The window for this application.
     *
     * @param windowTitle The title for this window.
     */
    public void startUI(Stage initPrimaryStage, String windowTitle) {
        // THE TOOLBAR ALONG THE NORTH
        initFileToolbar();

//        initSiteToolBar();
        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
        // TO THE WINDOW YET
        initWorkspace();

        // NOW SETUP THE EVENT HANDLERS
        initEventHandlers();

        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        // KEEP THE WINDOW FOR LATER
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    // UI SETUP HELPER METHODS
    private void initWorkspace() {
        // FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
        workspace = new BorderPane();

        // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
//        slideEditToolbar = new FlowPane();
//        addSlideButton = this.initChildButton(slideEditToolbar, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
//        removeSlideButton = this.initChildButton(slideEditToolbar, ICON_REMOVE_SLIDE, TOOLTIP_REMOVE_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
//        moveSlideUpButton = this.initChildButton(slideEditToolbar, ICON_MOVE_UP, TOOLTIP_MOVE_UP, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
//        moveSlideDownButton = this.initChildButton(slideEditToolbar, ICON_MOVE_DOWN, TOOLTIP_MOVE_DOWN, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, true);
//         AND THIS WILL GO IN THE CENTER
        slidesEditorPane = new VBox();
        slidesEditorScrollPane = new ScrollPane(slidesEditorPane);
        slidesEditorScrollPane.setFitToWidth(true);
        slidesEditorScrollPane.setFitToHeight(true);

//        tabPane = new TabPane();
        initTitleControls();
        initFooterControls();

        // NOW PUT THESE TWO IN THE WORKSPACE
//        workspace.setLeft(slideEditToolbar);
        workspace.setTop(fileToolbarPane);

        workspace.setLeft(slidesEditorScrollPane);
//        workspace.setRight();
        workspace.setBottom(footerPane);
//        workspace.setCenter(tabPane);

        // SETUP ALL THE STYLE CLASSES
        workspace.getStyleClass().add(CSS_CLASS_WORKSPACE);
//        slideEditToolbar.getStyleClass().add(CSS_CLASS_VERTICAL_TOOLBAR_PANE);
        //CHANGE ACUTAL CSS CLASS
//        tabPane.getStyleClass().add(CSS_CLASS_VERTICAL_TOOLBAR_PANE);
        slidesEditorPane.getStyleClass().add(CSS_CLASS_SLIDES_EDITOR_PANE);
        slidesEditorScrollPane.getStyleClass().add(CSS_CLASS_SLIDES_EDITOR_PANE);
    }

    private void initEventHandlers() {
        // FIRST THE FILE CONTROLS
        fileController = new FileController(this, fileManager, siteExporter);
        newSlideShowButton.setOnAction(e -> {
            fileController.handleNewSlideShowRequest();
        });
        loadSlideShowButton.setOnAction(e -> {
            fileController.handleLoadSlideShowRequest();
        });
        saveSlideShowButton.setOnAction(e -> {
            fileController.handleSaveSlideShowRequest();
        });
        viewSlideShowButton.setOnAction(e -> {
            fileController.handleViewSlideShowRequest();
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest();
        });

        // THEN THE SLIDE SHOW EDIT CONTROLS
        editController = new SlideShowEditController(this);
        addSlideButton.setOnAction(e -> {
            try {
                editController.processAddSlideRequest();
                n = new newPageDialog();
                n.showAndWait();

            } catch (Exception ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        removeSlideButton.setOnAction(e -> {
            editController.processRemoveSlideRequest();
        });
        moveSlideUpButton.setOnAction(e -> {
            editController.processMoveSlideUpRequest();
        });
        moveSlideDownButton.setOnAction(e -> {
            editController.processMoveSlideDownRequest();
        });
        newText.setOnAction((ActionEvent e) -> {
            try {
                editController.processAddSlideRequest();
                tpd = new newTextPageDialog();
                tpd.showAndWait();
            } catch (Exception ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        newSlideShow.setOnAction(e -> {
            try {
                editController.processAddSlideRequest();
                ssd = new newSlideShowDialog();
                ssd.showAndWait();
            } catch (Exception ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        newImage.setOnAction(e -> {
            try {
                editController.processAddSlideRequest();
                ipd = new newImagePageDialog();
                ipd.showAndWait();
            } catch (Exception ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        newList.setOnAction(e -> {
            try {
                editController.processAddSlideRequest();
                ld = new newListDialog();
                ld.showAndWait();
            } catch (Exception ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        newVideo.setOnAction(e -> {
            try {
                editController.processAddSlideRequest();
                newVideoPageDialog nv = new newVideoPageDialog();
                nv.showAndWait();
            } catch (Exception ex) {
                Logger.getLogger(SlideShowMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    private void initSiteToolBar() {

        slideEditToolbar = new FlowPane();
        slideEditToolbar.getStyleClass().add(CSS_CLASS_HORIZONTAL_TOOLBAR_PANE);
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        addSlideButton = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        removeSlideButton = this.initChildButton(fileToolbarPane, ICON_REMOVE_SLIDE, TOOLTIP_REMOVE_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        moveSlideUpButton = this.initChildButton(fileToolbarPane, ICON_MOVE_UP, TOOLTIP_MOVE_UP, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        moveSlideDownButton = this.initChildButton(fileToolbarPane, ICON_MOVE_DOWN, TOOLTIP_MOVE_DOWN, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);

        slideEditToolbar.setVgap(1);
        slideEditToolbar.setAlignment(Pos.TOP_RIGHT);

    }

    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar() {
        fileToolbarPane = new FlowPane();
        fileToolbarPane.getStyleClass().add(CSS_CLASS_HORIZONTAL_TOOLBAR_PANE);

        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        //THESE VALUES HERE MUST BE CHANGED
        newSlideShowButton = initChildButton(fileToolbarPane, ICON_NEW_SLIDE_SHOW, TOOLTIP_NEW_SLIDE_SHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadSlideShowButton = initChildButton(fileToolbarPane, ICON_LOAD_SLIDE_SHOW, TOOLTIP_LOAD_SLIDE_SHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveSlideShowButton = initChildButton(fileToolbarPane, ICON_SAVE_SLIDE_SHOW, TOOLTIP_SAVE_SLIDE_SHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        saveAsButton = initChildButton(fileToolbarPane, ICON_SAVE_AS, TOOLTIP_SAVE_SLIDE_SHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exportButton = initChildButton(fileToolbarPane, ICON_EXPORT, TOOLTIP_SAVE_SLIDE_SHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        viewSlideShowButton = initChildButton(fileToolbarPane, ICON_VIEW_SLIDE_SHOW, TOOLTIP_VIEW_SLIDE_SHOW, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        exitButton = initChildButton(fileToolbarPane, ICON_EXIT, TOOLTIP_EXIT, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);

        addSlideButton = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        removeSlideButton = this.initChildButton(fileToolbarPane, ICON_REMOVE_SLIDE, TOOLTIP_REMOVE_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        moveSlideUpButton = this.initChildButton(fileToolbarPane, ICON_MOVE_UP, TOOLTIP_MOVE_UP, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);
        moveSlideDownButton = this.initChildButton(fileToolbarPane, ICON_MOVE_DOWN, TOOLTIP_MOVE_DOWN, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, true);

        newText = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        newSlideShow = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        newImage = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        newList = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        newVideo = this.initChildButton(fileToolbarPane, ICON_ADD_SLIDE, TOOLTIP_ADD_SLIDE, CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);

        fileToolbarPane.setVgap(1);
        fileToolbarPane.setAlignment(Pos.TOP_LEFT);

    }

    private void initWindow(String windowTitle) {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(windowTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
        ssmPane = new BorderPane();
        ssmPane.getStyleClass().add(CSS_CLASS_WORKSPACE);
        ssmPane.setTop(fileToolbarPane);
//        ssmPane.setRight(slideEditToolbar);

        ssmPane.setBottom(footerTextField);
        primaryScene = new Scene(ssmPane);

        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
        // WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    /**
     * This helps initialize buttons in a toolbar, constructing a custom button
     * with a customly provided icon and tooltip, adding it to the provided
     * toolbar pane, and then returning it.
     */
    public Button initChildButton(
            Pane toolbar,
            String iconFileName,
            LanguagePropertyType tooltip,
            String cssClass,
            boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imagePath = "file:" + PATH_ICONS + iconFileName;
        Image buttonImage = new Image(imagePath);
        Button button = new Button();
        button.getStyleClass().add(cssClass);
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
        button.setTooltip(buttonTooltip);
        toolbar.getChildren().add(button);
        return button;
    }

    /**
     * Updates the enabled/disabled status of all toolbar buttons.
     *
     * @param saved
     */
    public void updateFileToolbarControls(boolean saved) {
        // FIRST MAKE SURE THE WORKSPACE IS THERE
        ssmPane.setCenter(workspace);

        // NEXT ENABLE/DISABLE BUTTONS AS NEEDED IN THE FILE TOOLBAR
        saveSlideShowButton.setDisable(saved);
        viewSlideShowButton.setDisable(false);

        updateSlideshowEditToolbarControls();
    }

    public void updateSlideshowEditToolbarControls() {
        // AND THE SLIDESHOW EDIT TOOLBAR
        addSlideButton.setDisable(false);
        boolean slideSelected = slideShow.isSlideSelected();
        removeSlideButton.setDisable(!slideSelected);
        moveSlideUpButton.setDisable(!slideSelected);
        moveSlideDownButton.setDisable(!slideSelected);
    }

    /**
     * Uses the slide show data to reload all the components for slide editing.
     *
     * @param slideShowToLoad SLide show being reloaded.
     */
    public void reloadSlideShowPane() {
        slidesEditorPane.getChildren().clear();
//        tabPane.getChildrenUnmodifiable().clear();
        reloadTitleControls();
        for (Slide slide : slideShow.getSlides()) {
            SlideEditView slideEditor = new SlideEditView(this, slide);
            if (slideShow.isSelectedSlide(slide)) {
                slideEditor.getStyleClass().add(CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW);
            } else {
                slideEditor.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);
            }
            slidesEditorPane.getChildren().add(slideEditor);
//            tabPane.getChildrenUnmodifiable().add(slideEditor);
            slideEditor.setOnMousePressed(e -> {
                slideShow.setSelectedSlide(slide);
                this.reloadSlideShowPane();
            });
        }
        updateSlideshowEditToolbarControls();
    }

    private void initTitleControls() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelPrompt = props.getProperty(LABEL_SLIDESHOW_TITLE);
        titlePane = new FlowPane();
        titleLabel = new Label(labelPrompt);
        titleTextField = new TextField();

        titlePane.getChildren().add(titleLabel);
        titlePane.getChildren().add(titleTextField);

        String titlePrompt = props.getProperty(LanguagePropertyType.LABEL_SLIDESHOW_TITLE);
        titleTextField.setText(titlePrompt);

        titleTextField.textProperty().addListener(e -> {
            slideShow.setTitle(titleTextField.getText());
            updateFileToolbarControls(false);
        });

        titlePane.getStyleClass().add(CSS_CLASS_TITLE_PANE);
        titleLabel.getStyleClass().add(CSS_CLASS_TITLE_PROMPT);
        titleTextField.getStyleClass().add(CSS_CLASS_TITLE_TEXT_FIELD);
    }

    public void reloadTitleControls() {
        if (slidesEditorPane.getChildren().size() == 0) {
            slidesEditorPane.getChildren().add(titlePane);
        }
//        if (tabPane.getChildrenUnmodifiable().size() == 0) {
//            tabPane.getChildrenUnmodifiable().add(titlePane);
//        }

        titleTextField.setText(slideShow.getTitle());
    }

    private void initFooterControls() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String labelPrompt = props.getProperty(LABEL_SLIDESHOW_FOOTER);
        footerPane = new FlowPane();
        footerLabel = new Label(labelPrompt);
        footerTextField = new TextField();

        footerPane.getChildren().add(footerLabel);
        footerPane.getChildren().add(footerTextField);

        String footerPrompt = props.getProperty(LanguagePropertyType.LABEL_SLIDESHOW_FOOTER);
        footerTextField.setText(footerPrompt);

        footerTextField.textProperty().addListener(e -> {
            slideShow.setFooter(footerTextField.getText());
            updateFileToolbarControls(false);
        });

        footerPane.getStyleClass().add(CSS_CLASS_TITLE_PANE);
        footerLabel.getStyleClass().add(CSS_CLASS_TITLE_PROMPT);
        footerTextField.getStyleClass().add(CSS_CLASS_TITLE_TEXT_FIELD);
    }

    public void reloadFooterControls() {
        if (slidesEditorPane.getChildren().size() == 0) {
            slidesEditorPane.getChildren().add(footerPane);
        }

        titleTextField.setText(slideShow.getFooter());
    }
}
