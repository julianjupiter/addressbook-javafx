package com.julianjupiter.addressbook.view.controller;

import com.julianjupiter.addressbook.core.Controller;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
    }
}
