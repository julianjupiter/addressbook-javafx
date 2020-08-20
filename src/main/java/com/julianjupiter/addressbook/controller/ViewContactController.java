package com.julianjupiter.addressbook.controller;

import com.julianjupiter.addressbook.core.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewContactController implements Controller, Initializable {
    private ContactProperty contactProperty;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label mobileNumberLabel;
    @FXML
    private Label emailAddressLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setContactProperty(ContactProperty contactProperty) {
        this.contactProperty = contactProperty;
        this.setContactValues();
    }

    private void setContactValues() {
        if (this.contactProperty != null) {
            this.firstNameLabel.setText(this.contactProperty.getFirstName());
            this.lastNameLabel.setText(this.contactProperty.getLastName());
            this.addressLabel.setText(this.contactProperty.getAddress());
            this.mobileNumberLabel.setText(this.contactProperty.getMobileNumber());
            this.emailAddressLabel.setText(this.contactProperty.getEmailAddress());
        }
    }
}
