package com.julianjupiter.addressbook.controller;

import com.julianjupiter.addressbook.core.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.ResourceBundle;

public class EditContactController implements Controller, Initializable {
    private ContactProperty contactProperty;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField mobileNumberTextField;
    @FXML
    private TextField emailAddressTextField;
    @FXML
    private Label firstNameMessageLabel;
    @FXML
    private Label lastNameMessageLabel;
    @FXML
    private Label addressMessageLabel;
    @FXML
    private Label mobileNumberMessageLabel;
    @FXML
    private Label emailAddressMessageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setContactProperty(ContactProperty contactProperty) {
        this.contactProperty = contactProperty;
        this.setContactValues();
    }

    private void setContactValues() {
        this.firstNameTextField.setText(this.contactProperty.getFirstName());
        this.lastNameTextField.setText(this.contactProperty.getLastName());
        this.addressTextField.setText(this.contactProperty.getAddress());
        this.mobileNumberTextField.setText(this.contactProperty.getMobileNumber());
        this.emailAddressTextField.setText(this.contactProperty.getEmailAddress());
    }

    public ContactProperty contactProperty() {
        return new ContactProperty()
                .setId(this.contactProperty.getId())
                .setFirstName(firstNameTextField.getText())
                .setLastName(lastNameTextField.getText())
                .setAddress(addressTextField.getText())
                .setMobileNumber(mobileNumberTextField.getText())
                .setEmailAddress(emailAddressTextField.getText())
                .setCreatedAt(this.contactProperty.getCreatedAt())
                .setUpdatedAt(OffsetDateTime.now());
    }

    public void validation(Map<String, String> violations) {
        if (violations.containsKey("firstName")) {
            this.firstNameMessageLabel.setVisible(true);
            this.firstNameMessageLabel.setText(violations.get("firstName"));
            this.firstNameMessageLabel.setTextFill(Color.RED);
        } else {
            this.firstNameMessageLabel.setText(null);
        }

        if (violations.containsKey("lastName")) {
            this.lastNameTextField.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            this.lastNameMessageLabel.setVisible(true);
            this.lastNameMessageLabel.setText(violations.get("lastName"));
            this.lastNameMessageLabel.setTextFill(Color.RED);
        } else {
            this.lastNameMessageLabel.setText(null);
        }

        if (violations.containsKey("address")) {
            this.addressTextField.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            this.addressMessageLabel.setVisible(true);
            this.addressMessageLabel.setText(violations.get("address"));
            this.addressMessageLabel.setTextFill(Color.RED);
        } else {
            this.addressMessageLabel.setText(null);
        }

        if (violations.containsKey("mobileNumber")) {
            this.mobileNumberTextField.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            this.mobileNumberMessageLabel.setVisible(true);
            this.mobileNumberMessageLabel.setText(violations.get("mobileNumber"));
            this.mobileNumberMessageLabel.setTextFill(Color.RED);
        } else {
            this.mobileNumberMessageLabel.setText(null);
        }

        if (violations.containsKey("emailAddress")) {
            this.emailAddressTextField.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            this.emailAddressMessageLabel.setVisible(true);
            this.emailAddressMessageLabel.setText(violations.get("emailAddress"));
            this.emailAddressMessageLabel.setTextFill(Color.RED);
        } else {
            this.emailAddressMessageLabel.setText(null);
        }
    }
}
