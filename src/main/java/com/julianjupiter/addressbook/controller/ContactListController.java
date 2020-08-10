package com.julianjupiter.addressbook.controller;

import com.julianjupiter.addressbook.core.Controller;
import com.julianjupiter.addressbook.core.View;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class ContactListController implements Controller, Initializable {
    private final Stage stage;
    private final ContactViewModel contactViewModel;
    @FXML
    private TableView<ContactViewModel> contactViewModelTableView;
    @FXML
    private TableColumn<ContactViewModel, String> firstNameTableColumn;
    @FXML
    private TableColumn<ContactViewModel, String> lastNameTableColumn;

    public ContactListController(Stage stage, ContactViewModel contactViewModel) {
        this.stage = stage;
        this.contactViewModel = contactViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.firstNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.lastNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        this.contactViewModelTableView.setItems(this.contactViewModel.getContactPropertiesObservable());
        this.contactViewModelTableView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> this.viewContact(newValue));
    }

    private void viewContact(ContactViewModel contactViewModel) {
        Map<Class<ContactViewController>, Callable<?>> controllerFactory = Map.of(
                ContactViewController.class,
                () -> new ContactViewController(this.stage, this.contactViewModel)
        );
        var view = View.of(ContactViewController.class, AnchorPane.class, controllerFactory);
        var anchorPane = view.component();

    }
}
