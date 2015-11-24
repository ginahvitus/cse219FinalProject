package ssm.view;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import ssm.LanguagePropertyType;
import static ssm.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static ssm.StartupConstants.DEFAULT_SLIDE_SHOW_HEIGHT;
import static ssm.StartupConstants.ICON_NEXT;
import static ssm.StartupConstants.ICON_PREVIOUS;
import static ssm.StartupConstants.LABEL_SLIDE_SHOW_TITLE;
import static ssm.file.SlideShowFileManager.SLASH;
import static ssm.file.SlideShowSiteExporter.INDEX_FILE;
import static ssm.file.SlideShowSiteExporter.SITES_DIR;
import ssm.model.Slide;
import ssm.model.SlideShowModel;

/**
 * This class provides the UI for the slide show viewer, note that this class is
 * a window and contains all controls inside.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowViewer extends Stage {

    // THE MAIN UI

    SlideShowMakerView parentView;

    // THE DATA FOR THIS SLIDE SHOW
    SlideShowModel slides;

    // HERE ARE OUR UI CONTROLS
    ScrollPane scrollPane;
    WebView webView;
    WebEngine webEngine;

    /**
     * This constructor just initializes the parent and slides references, note
     * that it does not arrange the UI or start the slide show view window.
     *
     * @param initParentView Reference to the main UI.
     */
    public SlideShowViewer(SlideShowMakerView initParentView) {
	// KEEP THIS FOR LATER
	parentView = initParentView;

	// GET THE SLIDES
	slides = parentView.getSlideShow();
    }

    /**
     * This method initializes the UI controls and opens the window with the
     * first slide in the slideshow displayed.
     */
    public void startSlideShow() throws MalformedURLException {
	// SETUP THE UI
	webView = new WebView();
	scrollPane = new ScrollPane(webView);
	
	// GET THE URL
	String indexPath = SITES_DIR + slides.getTitle() + SLASH + INDEX_FILE;
	File indexFile = new File(indexPath);
	URL indexURL = indexFile.toURI().toURL();
	
	// SETUP THE WEB ENGINE AND LOAD THE URL
	webEngine = webView.getEngine();
	webEngine.load(indexURL.toExternalForm());
	webEngine.setJavaScriptEnabled(true);
	
	// SET THE WINDOW TITLE
	this.setTitle(slides.getTitle());

	// NOW PUT STUFF IN THE STAGE'S SCENE
	Scene scene = new Scene(webView, 1100, 650);
	setScene(scene);
	this.showAndWait();
    }
}
