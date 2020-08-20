package com.julianjupiter.addressbook;

import com.julianjupiter.addressbook.controller.MainController;
import com.julianjupiter.addressbook.core.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddressBook extends Application {
    private Stage primaryStage;
    private final static String I18N = "i18n/messages";
    private final static Locale LOCALE = new Locale("en");

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.initMain();
    }

    private void initMain() {
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.setMaximized(false);

        var view = View.of(MainController.class, BorderPane.class, resourceBundle());
        BorderPane borderPane = view.component();
        var scene = new Scene(borderPane);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        this.primaryStage.setScene(scene);
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        MainController mainController = view.controller();
        mainController.setPrimaryStage(this.primaryStage);
        mainController.setValidator(validator());

        this.primaryStage.show();
    }

    public static ResourceBundle resourceBundle() {
        var locale = new Locale("en");
        return ResourceBundle.getBundle(I18N, LOCALE);
    }

    public static Validator validator() {
        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(new AggregateResourceBundleLocator(List.of(I18N))))
                .buildValidatorFactory()
                .getValidator();
    }

}
