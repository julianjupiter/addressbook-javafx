package com.julianjupiter.addressbook.persistence.repository;

import com.julianjupiter.addressbook.persistence.entity.Contact;

import java.util.List;

public interface ContactRepository extends Repository<Contact, Long> {

    static ContactRepository create() {
        return new ContactRepositoryImpl();
    }

    List<Contact> findByFirstNameOrLastName(String name);

}
