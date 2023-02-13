package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class in JavaFX application.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class App extends Application {

    /**
     * Object that responsible for display application.
     */
    private static Scene scene;

    /**
     * Method responsible for display application.
     * @param stage Object that responsible for manage scene.
     * @throws IOException Throw IOException if app don't find graphic component of window.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("simplewindow"));
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();
    }

    /**
     * Sets the value of the property root.
     * @param fxml Pass the fxml file with app window.
     * @throws IOException Throw IOException if app don't find graphic component of window.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Load the .fxml file.
     * @param fxml Pass the fxml file with app window.
     * @return ready .fxml file
     * @throws IOException Throw IOException if app don't find graphic component of window.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main method in java app.
     * @param args Args of main method in java app.
     */
    public static void main(String[] args) {
        launch();
    }

}