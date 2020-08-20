package com.julianjupiter.addressbook.controller;

import com.julianjupiter.addressbook.service.ContactDto;

public class ContactMapper {
    public ContactProperty fromDtoToProperty(ContactDto contactDto) {
        return new ContactProperty()
                .setId(contactDto.getId())
                .setLastName(contactDto.getLastName())
                .setFirstName(contactDto.getFirstName())
                .setAddress(contactDto.getAddress())
                .setMobileNumber(contactDto.getMobileNumber())
                .setEmailAddress(contactDto.getEmailAddress())
                .setCreatedAt(contactDto.getCreatedAt())
                .setUpdatedAt(contactDto.getUpdatedAt());
    }

    public ContactDto fromPropertyToDto(ContactProperty contactProperty) {
        return new ContactDto()
                .setId(contactProperty.getId())
                .setLastName(contactProperty.getLastName())
                .setFirstName(contactProperty.getFirstName())
                .setAddress(contactProperty.getAddress())
                .setMobileNumber(contactProperty.getMobileNumber())
                .setEmailAddress(contactProperty.getEmailAddress())
                .setCreatedAt(contactProperty.getCreatedAt())
                .setUpdatedAt(contactProperty.getUpdatedAt());
    }
}
