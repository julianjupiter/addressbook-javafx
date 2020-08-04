package com.julianjupiter.addressbook.view.controller;

import com.julianjupiter.addressbook.core.Controller;
import com.julianjupiter.addressbook.view.handler.ContactListViewHandler;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Controller, Initializable {
    private final Stage stage;
    private final ContactViewModel contactViewModel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private BorderPane mainHeaderBorderPane;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private FontIcon closeWindowFontIcon;
    @FXML
    private FontIcon minimizeWindowFontIcon;

    public MainController(Stage stage, ContactViewModel contactViewModel) {
        this.stage = stage;
        this.contactViewModel = contactViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.initWindowEvents();

        this.contactViewModel.createContact();
        this.contactViewModel.createContact();
        this.contactViewModel.findAllContacts();

        this.startContactListController();
    }

    private void initWindowEvents() {
        mainHeaderBorderPane.setOnMousePressed(mouseEvent -> {
            if (!this.stage.isMaximized()) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        mainHeaderBorderPane.setOnMouseDragged(mouseEvent -> {
            if (!this.stage.isMaximized()) {
                this.stage.setX(mouseEvent.getScreenX() - xOffset);
                this.stage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });

        closeWindowFontIcon.setOnMouseClicked(mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Close Application");
            alert.setHeaderText("You are about to close the application.");
            alert.setContentText("Do you want to continue?");

            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(this.stage);

            alert.showAndWait()
                    .filter(buttonType -> buttonType == ButtonType.OK)
                    .ifPresent(buttonType -> Platform.exit());
        });

        minimizeWindowFontIcon.setOnMouseClicked(mouseEvent -> {
            this.stage.setIconified(true);
        });
    }

    private void startContactListController() {
        var contactListHandler = new ContactListViewHandler(this.stage, this.contactViewModel, this.mainBorderPane);
        contactListHandler.start();
    }
}
