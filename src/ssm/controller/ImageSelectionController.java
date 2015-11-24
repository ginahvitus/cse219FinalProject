package ssm.controller;

import java.io.File;
import javafx.stage.FileChooser;
import static ssm.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import ssm.model.Slide;
import ssm.view.SlideEditView;
import ssm.view.SlideShowMakerView;

/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author McKilla Gorilla & _____________
 */
public class ImageSelectionController {
    SlideShowMakerView ui;
    
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public ImageSelectionController(SlideShowMakerView initUi) {   
	ui = initUi;
    }
    
    /**
     * This function provides the response to the user's request to
     * select an image.
     * 
     * @param slideToEdit - Slide for which the user is selecting an image.
     * 
     * @param view The user interface control group where the image
     * will appear after selection.
     */
    public void processSelectImage(Slide slideToEdit, SlideEditView view) {
	FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOW_IMAGES));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
	imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    String fileName = file.getName();
	    slideToEdit.setImage(path, fileName);
	    view.updateSlideImage();
	    ui.updateFileToolbarControls(false);
	}
    }
    
      public void processSelectVideo(Slide slideToEdit, SlideEditView view) {
	FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOW_IMAGES));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter oggFilter = new FileChooser.ExtensionFilter("OGG files (*.ogg)", "*.OGG");
	FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.MP4");
	FileChooser.ExtensionFilter aviFilter = new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.AVI");
	imageFileChooser.getExtensionFilters().addAll(oggFilter, mp4Filter, aviFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    String fileName = file.getName();
	    slideToEdit.setImage(path, fileName);
	    view.updateSlideImage();
	    ui.updateFileToolbarControls(false);
	}
      }
}
