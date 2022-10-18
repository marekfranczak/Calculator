package org.example;


import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Dialog {

    private static final String WARNING_TITLE = "Błąd!";
    private static final String WRONG_DATA_TYPE = "Wprowadzono niewłaściwy typ danych!";
    private static final String LACK_OF_SECOND_NUMBER = "Brak drugiej liczby aby wykonać to działanie!";
    private static final String INCORRECT_OPERATION = "Nie rozpoznano operacji, którą chcesz wykonać!";
    private static final String LACK_OF_DATA = "Nie wprowadzono żadnych danych!";
    private static final String FACTORIAL_WARNING = "Silnia może się składać tylko z liczb naturalnych!";
    private static final String ABOUT_PROGRAM = "O programie..";
    private static final String ABOUT_AUTHOR = "Rozbudowany kalkulator v.2.0.0. autor: Marek Frańczak";

    private void warningTemplate(Window window, String title, String contextText){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(window);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public void wrongDataType(Window window){
        warningTemplate(window, WARNING_TITLE, WRONG_DATA_TYPE);
    }

    public void notEnoughData(Window window){
        warningTemplate(window, WARNING_TITLE, LACK_OF_SECOND_NUMBER);
    }

    public void incorrectOperation(Window window){
        warningTemplate(window, WARNING_TITLE, INCORRECT_OPERATION);
    }

    public void noData(Window window){
        warningTemplate(window, WARNING_TITLE, LACK_OF_DATA);
    }

    public void factorialWarning(Window window){
        warningTemplate(window, WARNING_TITLE, FACTORIAL_WARNING);
    }

    public void aboutProgram(Window window){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(window);
        alert.setTitle(ABOUT_PROGRAM);
        alert.setHeaderText(null);
        alert.setContentText(ABOUT_AUTHOR);
        alert.showAndWait();
    }
}
