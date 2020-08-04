package com.julianjupiter.addressbook.core;

import com.julianjupiter.addressbook.service.ContactService;

public class ModelFactory {
    private final ContactService contactService;

    public ModelFactory() {
        this.contactService = ContactService.create();
    }

    public ContactService getDataModel() {
        return contactService;
    }
}
