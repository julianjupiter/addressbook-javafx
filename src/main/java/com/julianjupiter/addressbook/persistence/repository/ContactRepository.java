package com.julianjupiter.addressbook.persistence.repository;

import com.julianjupiter.addressbook.persistence.entity.Contact;

public interface ContactRepository extends Repository<Contact, Long> {

    static ContactRepository create() {
        return new ContactRepositoryImpl();
    }

}
