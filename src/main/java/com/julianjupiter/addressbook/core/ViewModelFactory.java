package com.julianjupiter.addressbook.core;

import com.julianjupiter.addressbook.viewmodel.ContactViewModel;

public class ViewModelFactory {
    private final ModelFactory modelFactory;

    public ViewModelFactory(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public ContactViewModel getContactModelView() {
        return new ContactViewModel(modelFactory.getDataModel());
    }
}
