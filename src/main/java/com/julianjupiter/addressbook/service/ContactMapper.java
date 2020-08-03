package com.julianjupiter.addressbook.service;

import com.julianjupiter.addressbook.persistence.entity.Contact;

public class ContactMapper {
    public ContactDto fromEntityToDto(Contact contact) {
        if (contact != null) {
            return new ContactDto()
                    .setId(contact.getId())
                    .setFirstName(contact.getFirstName())
                    .setLastName(contact.getLastName())
                    .setAddress(contact.getAddress())
                    .setMobileNumber(contact.getMobileNumber())
                    .setEmailAddress(contact.getEmailAddress())
                    .setCreatedAt(contact.getCreatedAt())
                    .setUpdatedAt(contact.getUpdatedAt());
        }

        return null;
    }

    public Contact fromDtoToEntity(ContactDto contactDto) {
        if (contactDto != null) {
            return new Contact()
                    .setId(contactDto.getId())
                    .setFirstName(contactDto.getFirstName())
                    .setLastName(contactDto.getLastName())
                    .setAddress(contactDto.getAddress())
                    .setMobileNumber(contactDto.getMobileNumber())
                    .setEmailAddress(contactDto.getEmailAddress())
                    .setCreatedAt(contactDto.getCreatedAt())
                    .setUpdatedAt(contactDto.getUpdatedAt());
        }

        return null;
    }
}
