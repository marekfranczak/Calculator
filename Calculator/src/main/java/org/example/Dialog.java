package org.example;


import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * Class that provide exception handle.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class Dialog {

    private static final String WARNING_TITLE = "Błąd!";
    private static final String WRONG_DATA_TYPE = "Wprowadzono niewłaściwy typ danych!";
    private static final String LACK_OF_SECOND_NUMBER = "Brak drugiej liczby aby wykonać to działanie!";
    private static final String INCORRECT_OPERATION = "Nie rozpoznano operacji, którą chcesz wykonać!";
    private static final String LACK_OF_DATA = "Nie wprowadzono żadnych danych!";
    private static final String FACTORIAL_WARNING = "Silnia może się składać tylko z liczb naturalnych!";
    private static final String ABOUT_PROGRAM = "O programie..";
    private static final String ABOUT_AUTHOR = "Rozbudowany kalkulator v.2.0.0. autor: Marek Frańczak";

/**
 * This method displays a warning dialog with a given title and context text.
 * @param window The owner window for the dialog
 * @param title The title of the dialog
 * @param contextText The context text to display in the dialog
 */
    private void warningTemplate(Window window, String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(window);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    /**
     * Method that display information about incorrect input data.
     * @param window Object of window type that is responsible for display dialog window.
     * @see Window
     */
    public void wrongDataType(Window window){
        warningTemplate(window, WARNING_TITLE, WRONG_DATA_TYPE);
    }

    /**
     * Method that display information about incorrect input data.
     * @param window Object of window type that is responsible for display dialog window.
     * @see Window
     */
    public void notEnoughData(Window window){
        warningTemplate(window, WARNING_TITLE, LACK_OF_SECOND_NUMBER);
    }

    /**
     * Method that display information about incorrect operation symbol .
     * @param window Object of window type that is responsible for display dialog window.
     * @see Window
     */
    public void incorrectOperation(Window window){
        warningTemplate(window, WARNING_TITLE, INCORRECT_OPERATION);
    }

    /**
     * Method that display information about missing input data.
     * @param window Object of window type that is responsible for display dialog window.
     * @see Window
     */
    public void noData(Window window){
        warningTemplate(window, WARNING_TITLE, LACK_OF_DATA);
    }

    /**
     * Method that display information about incorrect input data.
     * @param window Object of window type that is responsible for display dialog window.
     * @see Window
     */
    public void factorialWarning(Window window){
        warningTemplate(window, WARNING_TITLE, FACTORIAL_WARNING);
    }

    /**
     * Method that display information about application.
     * @param window Object of window type that is responsible for display dialog window.
     * @see Window
     */
    public void aboutProgram(Window window){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(window);
        alert.setTitle(ABOUT_PROGRAM);
        alert.setHeaderText(null);
        alert.setContentText(ABOUT_AUTHOR);
        alert.showAndWait();
    }
}
