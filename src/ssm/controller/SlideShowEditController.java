package ssm.controller;

import properties_manager.PropertiesManager;
import static ssm.LanguagePropertyType.DEFAULT_IMAGE_CAPTION;
import static ssm.StartupConstants.DEFAULT_SLIDE_IMAGE;
import static ssm.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import ssm.model.SlideShowModel;
import ssm.view.SlideShowMakerView;


/**
 * This controller provides responses for the slideshow edit toolbar, which
 * allows the user to add, remove, and reorder slides.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowEditController {

 
    
//  Survey.pageCreationWizard creationWizard = pcw;
//  someSecretary.new positionObj()
  
  
  
       
    
//   pageCreationWizard pcw = new pageCreationWizard(ui.getWindow());
   

    // APP UI
    private SlideShowMakerView ui;

    /**
     * This constructor keeps the UI for later.
     */
    public SlideShowEditController(SlideShowMakerView initUI) {
        ui = initUI;
    }

    /**
     * Provides a response for when the user wishes to add a new slide to the
     * slide show.
     */
    public void processAddSlideRequest() throws Exception {
        SlideShowModel slideShow = ui.getSlideShow();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        slideShow.addSlide(DEFAULT_SLIDE_IMAGE, PATH_SLIDE_SHOW_IMAGES, props.getProperty(DEFAULT_IMAGE_CAPTION));


        

        ui.updateFileToolbarControls(false);
        
    }

    /**
     * Provides a response for when the user has selected a slide and wishes to
     * remove it from the slide show.
     */
    public void processRemoveSlideRequest() {
        SlideShowModel slideShow = ui.getSlideShow();
        slideShow.removeSelectedSlide();
        ui.reloadSlideShowPane();
        ui.updateFileToolbarControls(false);
    }

    /**
     * Provides a response for when the user has selected a slide and wishes to
     * move it up in the slide show.
     */
    public void processMoveSlideUpRequest() {
        SlideShowModel slideShow = ui.getSlideShow();
        slideShow.moveSelectedSlideUp();
        ui.updateFileToolbarControls(false);
    }

    /**
     * Provides a response for when the user has selected a slide and wises to
     * move it down in the slide show.
     */
    public void processMoveSlideDownRequest() {
        SlideShowModel slideShow = ui.getSlideShow();
        slideShow.moveSelectedSlideDown();
        ui.updateFileToolbarControls(false);
    }
}
