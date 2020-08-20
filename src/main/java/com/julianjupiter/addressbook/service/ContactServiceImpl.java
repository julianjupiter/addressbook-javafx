package com.julianjupiter.addressbook.service;

import com.julianjupiter.addressbook.persistence.repository.ContactRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public List<ContactDto> findAll() {
        return this.contactRepository.findAll().stream()
                .map(this.contactMapper::fromEntityToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<ContactDto> findById(Long id) {
        return this.contactRepository.findById(id)
                .map(this.contactMapper::fromEntityToDto);
    }

    @Override
    public List<ContactDto> findByFirstNameOrLastName(String name) {
        return this.contactRepository.findByFirstNameOrLastName(name).stream()
                .map(this.contactMapper::fromEntityToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ContactDto save(ContactDto contactDto) {
        var contact = this.contactRepository.save(this.contactMapper.fromDtoToEntity(contactDto));
        return this.contactMapper.fromEntityToDto(contact);
    }

    @Override
    public void deleteById(Long id) {
        this.contactRepository.deleteById(id);
    }
}
