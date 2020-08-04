package com.julianjupiter.addressbook.view.handler;

import com.julianjupiter.addressbook.core.ViewHandler;
import com.julianjupiter.addressbook.core.ViewModelFactory;
import com.julianjupiter.addressbook.util.Views;
import com.julianjupiter.addressbook.view.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Map;
import java.util.concurrent.Callable;

public class MainViewHandler implements ViewHandler  {
    private final Stage stage;
    private final ViewModelFactory viewModelFactory;

    public MainViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
        this.stage = stage;
        this.viewModelFactory = viewModelFactory;
    }

    @Override
    public void start() {
        Map<Class<MainController>, Callable<?>> controllerFactory = Map.of(MainController.class, () -> new MainController(stage, viewModelFactory.getContactModelView()));
        var views = Views.of(MainController.class, BorderPane.class, controllerFactory);
        var scene = new Scene(views.component());
        this.stage.setScene(scene);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);
        this.stage.show();
    }
}
