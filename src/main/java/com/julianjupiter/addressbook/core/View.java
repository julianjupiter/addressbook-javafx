package com.julianjupiter.addressbook.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class View<T extends Controller, U extends Parent> {
    private static final String FXML_EXTENSION = ".fxml";
    private final Class<? extends Controller> controllerClass;
    private final ResourceBundle resourceBundle;
    private final Map<Class<T>, Callable<?>> controllerFactory;
    private FXMLLoader loader;
    private U u;

    private View(Class<? extends Controller> controllerClass, ResourceBundle resourceBundle, Map<Class<T>, Callable<?>> controllerFactory) throws IOException {
        this.controllerClass = controllerClass;
        this.resourceBundle = resourceBundle;
        this.controllerFactory = controllerFactory;
        this.load();
    }

    public static <T extends Controller, U extends Parent> View<T, U> of(Class<T> controllerClass, Class<U> componentClass) {
        try {
            return new View<>(controllerClass, null, Map.of());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <T extends Controller, U extends Parent> View<T, U> of(Class<T> controllerClass, Class<U> componentClass, ResourceBundle resourceBundle) {
        try {
            return new View<>(controllerClass, resourceBundle, Map.of());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <T extends Controller, U extends Parent> View<T, U> of(Class<T> controllerClass, Class<U> componentClass, Map<Class<T>, Callable<?>> controllerFactory) {
        try {
            return new View<>(controllerClass, null, controllerFactory);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void load() throws IOException {
        this.loader = new FXMLLoader(this.fxmlUrl(), this.resourceBundle);
        this.controllerFactory();
        this.u = this.loader.load();
    }

    private URL fxmlUrl() {
        return this.controllerClass.getResource(this.controllerClass.getSimpleName() + FXML_EXTENSION);
    }

    public U component() {
        return u;
    }

    public T controller() {
        return this.loader.getController();
    }

    private void controllerFactory() {
        this.loader.setControllerFactory(param -> {
            Callable<?> callable = controllerFactory.get(param);

            if (callable == null) {
                try {
                    return param.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
                    throw new IllegalStateException(exception);
                }
            } else {
                try {
                    return callable.call();
                } catch (Exception exception) {
                    throw new IllegalStateException(exception);
                }
            }
        });
    }
}
