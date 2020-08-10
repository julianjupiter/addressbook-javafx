package com.julianjupiter.addressbook.controller;

import com.julianjupiter.addressbook.core.Controller;
import com.julianjupiter.addressbook.core.View;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class ContactMainController implements Controller, Initializable {
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

    public ContactMainController(Stage stage, ContactViewModel contactViewModel) {
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
        this.mainHeaderBorderPane.setOnMousePressed(mouseEvent -> {
            if (!this.stage.isMaximized()) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
            }
        });

        this.mainHeaderBorderPane.setOnMouseDragged(mouseEvent -> {
            if (!this.stage.isMaximized()) {
                this.stage.setX(mouseEvent.getScreenX() - xOffset);
                this.stage.setY(mouseEvent.getScreenY() - yOffset);
            }
        });

        this.closeWindowFontIcon.setOnMouseClicked(mouseEvent -> {
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

        this.minimizeWindowFontIcon.setOnMouseClicked(mouseEvent -> {
            this.stage.setIconified(true);
        });
    }

    private void startContactListController() {
        Map<Class<ContactListController>, Callable<?>> controllerFactory = Map.of(
                ContactListController.class,
                () -> new ContactListController(this.stage, this.contactViewModel)
        );
        var view = View.of(ContactListController.class, StackPane.class, controllerFactory);
        var stackPane = view.component();
        this.mainBorderPane.setLeft(stackPane);
    }
}
