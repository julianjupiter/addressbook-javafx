package com.julianjupiter.addressbook.controller;

import com.julianjupiter.addressbook.core.Controller;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactViewController implements Controller, Initializable {
    private final Stage stage;
    private final ContactViewModel contactViewModel;

    public ContactViewController(Stage stage, ContactViewModel contactViewModel) {
        this.stage = stage;
        this.contactViewModel = contactViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
