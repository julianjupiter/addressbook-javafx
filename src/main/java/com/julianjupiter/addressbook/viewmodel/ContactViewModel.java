package com.julianjupiter.addressbook.viewmodel;

import com.julianjupiter.addressbook.service.ContactDto;
import com.julianjupiter.addressbook.service.ContactService;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ContactViewModel {
    private ContactService contactService;
    private final LongProperty id = new SimpleLongProperty();;
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty mobileNumber = new SimpleStringProperty();
    private final StringProperty emailAddress = new SimpleStringProperty();
    private final ObjectProperty<OffsetDateTime> createdAt = new SimpleObjectProperty<>();
    private final ObjectProperty<OffsetDateTime> updatedAt = new SimpleObjectProperty<>();
    private final ObservableList<ContactViewModel> contactPropertiesObservable = FXCollections.observableArrayList();

    public ContactViewModel() {

    }

    public ContactViewModel(ContactService contactService) {
        this.contactService = contactService;
    }

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public ContactViewModel setId(Long id) {
        this.id.set(id);
        return this;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ContactViewModel setLastName(String lastName) {
        this.lastName.set(lastName);
        return this;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public ContactViewModel setFirstName(String firstName) {
        this.firstName.set(firstName);
        return this;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public ContactViewModel setAddress(String address) {
        this.address.set(address);
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber.get();
    }

    public StringProperty mobileNumberProperty() {
        return mobileNumber;
    }

    public ContactViewModel setMobileNumber(String mobileNumber) {
        this.mobileNumber.set(mobileNumber);
        return this;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public ContactViewModel setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt.get();
    }

    public ObjectProperty<OffsetDateTime> createdAtProperty() {
        return createdAt;
    }

    public ContactViewModel setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt.set(createdAt);
        return this;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt.get();
    }

    public ObjectProperty<OffsetDateTime> updatedAtProperty() {
        return updatedAt;
    }

    public ContactViewModel setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt.set(updatedAt);
        return this;
    }

    public ObservableList<ContactViewModel> getContactPropertiesObservable() {
        this.contactPropertiesObservable.addAll(this.findAllContacts());
        return contactPropertiesObservable;
    }

    public List<ContactViewModel> findAllContacts() {
        List<ContactViewModel> contactViewModels = this.contactService.findAll().stream()
                .map(contactDto -> {
                    return new ContactViewModel().setId(contactDto.getId())
                            .setFirstName(contactDto.getFirstName())
                            .setLastName(contactDto.getLastName())
                            .setAddress(contactDto.getAddress())
                            .setMobileNumber(contactDto.getMobileNumber())
                            .setEmailAddress(contactDto.getEmailAddress())
                            .setCreatedAt(contactDto.getCreatedAt())
                            .setUpdatedAt(contactDto.getUpdatedAt());
                })
                .collect(Collectors.toUnmodifiableList());

        return contactViewModels;
    }

    public void createContact() {
        var contactDto = new ContactDto()
                .setFirstName("Andres")
                .setLastName("Bonifacio")
                .setAddress("Tondo, Manila")
                .setMobileNumber("09165678902")
                .setEmailAddress("andresbonifacio@gmail.com");
        this.contactService.save(contactDto);
    }

}
