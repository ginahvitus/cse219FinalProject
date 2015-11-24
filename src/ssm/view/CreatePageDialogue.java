///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ssm.view;
//
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.scene.Parent;
//import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TextInputDialog;
//import javafx.scene.control.Toggle;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.layout.VBoxBuilder;
//
///**
// *
// * @author Huxton
// */
//public class CreatePageDialogue extends Survey.WizardPage {
//
//    private RadioButton yes;
//    private RadioButton no;
//    private ToggleGroup options = new ToggleGroup();
//    String selection;
//
//    public CreatePageDialogue() {
//        super("CreatePage");
//
//        TextInputDialog dialog = new TextInputDialog("title");
//        dialog.setTitle("Text Input Dialog");
//        dialog.setHeaderText("Page Creation Dialog");
//        dialog.setContentText("Please enter a Title:");
//        selection = dialog.getContentText();
//
//        nextButton.setDisable(true);
//        finishButton.setDisable(true);
//        yes.setToggleGroup(options);
//        no.setToggleGroup(options);
//        options.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//            @Override
//            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
//                nextButton.setDisable(false);
//                finishButton.setDisable(false);
//            }
//        });
//    }
//
//    public String getSelection() {
//        return selection;
//    }
//
//    Parent getContent() {
//        yes = new RadioButton("Yes");
//        no = new RadioButton("No");
//        Survey.pageData.instance.hasComplaints.bind(yes.selectedProperty());
//        return VBoxBuilder.create().spacing(5).children(
//                new Label("Please Enter a Title: "), yes, no
//        ).build();
//    }
//
//    @Override
//    void nextPage() {
//        // If they have complaints, go to the normal next page
//        if (options.getSelectedToggle().equals(yes)) {
//            super.nextPage();
//        } else {
//            // No complaints? Short-circuit the rest of the pages
//            navTo("Thanks");
//        }
//    }
//}
//}
