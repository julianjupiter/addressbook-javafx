package com.julianjupiter.addressbook.controller;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

public class ContactProperty {
    private LongProperty id;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty address;
    private StringProperty mobileNumber;
    private StringProperty emailAddress;
    private ObjectProperty<OffsetDateTime> createdAt;
    private ObjectProperty<OffsetDateTime> updatedAt;

    public ContactProperty() {
        this.id = new SimpleLongProperty();
        this.lastName = new SimpleStringProperty();
        this.firstName = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.mobileNumber = new SimpleStringProperty();
        this.emailAddress = new SimpleStringProperty();
        this.createdAt = new SimpleObjectProperty<>();
        this.updatedAt = new SimpleObjectProperty<>();
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public ContactProperty setId(Long id) {
        this.id.set(id);
        return this;
    }

    @NotBlank(message = "{contactProperty.lastName.NotBlank}")
    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ContactProperty setLastName(String lastName) {
        this.lastName.set(lastName);
        return this;
    }

    @NotBlank(message = "{contactProperty.firstName.NotBlank}")
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public ContactProperty setFirstName(String firstName) {
        this.firstName.set(firstName);
        return this;
    }

    @NotBlank(message = "{contactProperty.address.NotBlank}")
    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public ContactProperty setAddress(String address) {
        this.address.set(address);
        return this;
    }

    @NotBlank(message = "{contactProperty.mobileNumber.NotBlank}")
    public String getMobileNumber() {
        return mobileNumber.get();
    }

    public StringProperty mobileNumberProperty() {
        return mobileNumber;
    }

    public ContactProperty setMobileNumber(String mobileNumber) {
        this.mobileNumber.set(mobileNumber);
        return this;
    }

    @Email(regexp = ".+@.+\\..+", message = "{contactProperty.emailAddress.Email}")
    public String getEmailAddress() {
        return emailAddress.get();
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public ContactProperty setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt.get();
    }

    public ObjectProperty<OffsetDateTime> createdAtProperty() {
        return createdAt;
    }

    public ContactProperty setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt.set(createdAt);
        return this;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt.get();
    }

    public ObjectProperty<OffsetDateTime> updatedAtProperty() {
        return updatedAt;
    }

    public ContactProperty setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt.set(updatedAt);
        return this;
    }
}
