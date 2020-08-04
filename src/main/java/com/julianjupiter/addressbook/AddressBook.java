package com.julianjupiter.addressbook;

import com.julianjupiter.addressbook.core.ModelFactory;
import com.julianjupiter.addressbook.core.ViewModelFactory;
import com.julianjupiter.addressbook.view.handler.MainViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddressBook extends Application {
    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setMaximized(false);

        var modelFactory = new ModelFactory();
        var viewModelFactory = new ViewModelFactory(modelFactory);
        var mainViewHandler = new MainViewHandler(stage, viewModelFactory);
        mainViewHandler.start();
    }
}
