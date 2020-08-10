package com.julianjupiter.addressbook;

import com.julianjupiter.addressbook.controller.ContactMainController;
import com.julianjupiter.addressbook.service.ContactService;
import com.julianjupiter.addressbook.core.View;
import com.julianjupiter.addressbook.viewmodel.ContactViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Map;
import java.util.concurrent.Callable;

public class AddressBook extends Application {
    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(false);
        var contactService = ContactService.create();
        var contactViewModel = new ContactViewModel(contactService);
        Map<Class<ContactMainController>, Callable<?>> controllerFactory = Map.of(
                ContactMainController.class,
                () -> new ContactMainController(stage, contactViewModel)
        );
        var view = View.of(ContactMainController.class, BorderPane.class, controllerFactory);
        var scene = new Scene(view.component());
        stage.setScene(scene);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        stage.show();
    }
}
