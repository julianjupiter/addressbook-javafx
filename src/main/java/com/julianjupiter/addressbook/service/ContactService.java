package com.julianjupiter.addressbook.service;

import com.julianjupiter.addressbook.persistence.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    static ContactService create() {
        return new ContactServiceImpl(ContactRepository.create(), new ContactMapper());
    }

    List<ContactDto> findAll();

    Optional<ContactDto> findById(Long id);

    List<ContactDto> findByFirstNameOrLastName(String name);

    ContactDto save(ContactDto contactDto);

    void deleteById(Long id);
}
