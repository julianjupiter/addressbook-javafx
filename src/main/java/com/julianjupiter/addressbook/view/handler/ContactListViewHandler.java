package com.julianjupiter.addressbook.view.handler;

import com.julianjupiter.addressbook.core.ViewHandler;
import com.julianjupiter.addressbook.util.Views;
import com.julianjupiter.addressbook.view.controller.ContactListController;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Map;
import java.util.concurrent.Callable;

public class ContactListViewHandler implements ViewHandler {
    private final Stage stage;
    private final ContactViewModel contactViewModel;
    private final BorderPane mainViewBorderPane;

    public ContactListViewHandler(Stage stage, ContactViewModel contactViewModel, BorderPane mainViewBorderPane) {
        this.stage = stage;
        this.contactViewModel = contactViewModel;
        this.mainViewBorderPane = mainViewBorderPane;
    }

    @Override
    public void start() {
        Map<Class<ContactListController>, Callable<?>> controllerFactory = Map.of(
                ContactListController.class,
                () -> new ContactListController(this.stage, this.contactViewModel)
        );
        var views = Views.of(ContactListController.class, StackPane.class, controllerFactory);
        var stackPane = views.component();
        this.mainViewBorderPane.setLeft(stackPane);
    }
}
