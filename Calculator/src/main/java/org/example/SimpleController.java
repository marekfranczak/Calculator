package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;


/**
 * A class that implements the operations performed in the scientific part of the calculator. Class implements interfaces: SimpleCalc, AdditionalOptionInScienCalc.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class SimpleController implements SimpleCalc{

    /**
     * Field that pass FXML object BorderPane where math operation are display.
     */
    @FXML
    private BorderPane simpleWindow;
    /**
     * Field that pass FXML object BorderPane which are used to entering numbers.
     */
    @FXML
    private TextField currentOperation;
    /**
     * Field that pass FXML object BorderPane which display calc history.
     */
    @FXML
    private TextArea operationsHistory;

    /**
     * Symbol of the binary operation that will be performed
     */
    private Calc calc = Calc.NULL;
    /**
     * Information that next operation will be binary.
     */
    private final TwoArgumentOperation twoArgumentOperation = new TwoArgumentOperation();
    /**
     * Object of Dialog class that provides possibility to inform user about error in program.
     */
    private final Dialog dialog = new Dialog();

    /**
     * Method that change a displayed kind of calculator.
     * @throws Exception Handling exceptions that may occur when changing screens.
     */
    @FXML
    public void changeCalculator() throws Exception {
        App.setRoot("scienwindow");
    }

    /**
     * First method that will be call after SimpleController was created.
     */
    @FXML
    public void initialize(){
        operationsHistory.setText(Data.getInstance().toString());
    }

    /**
     * Method that check that input char is number.
     * @param s Inout sequence of chars.
     * @return true if sequence of chars is number.
     */
    private boolean input(String s){
        boolean b = false;
        try{
            double d = Double.parseDouble(s);
            Data.getInstance().setFirstNumber(d);
            b = true;
        }catch (NumberFormatException e){
            dialog.wrongDataType(simpleWindow.getScene().getWindow());
            currentOperation.clear();
        }
        return b;
    }

    /**
     * Add number from virtual keyboard that user chose to the number display.
     * @param s Number that will be added to the number display.
     */
    private void inputNumber(String s){
        String text = currentOperation.getText();
        text = text+s;
        currentOperation.setText(text);
    }

    /**
     * Method that responsible for display all mathematical equation. It is uses to display binary operation.
     * @param num Number in this math equation.
     * @param s Symbol of math operation.
     */
    private void output(double num, String s){
        currentOperation.setText(String.valueOf(num));
        Data.getInstance().addOperations(Data.getInstance().getFirstNumber() + s + num);
        operationsHistory.setText(Data.getInstance().toString());
    }

    /**
     * Method that responsible for display all mathematical equation. It is uses to display single argument operation.
     * @param s Symbol of math operation.
     * @param num Number in this math equation.
     */
    private void output(String s, double num){
        currentOperation.setText(String.valueOf(num));
        Data.getInstance().addOperations(s + Data.getInstance().getFirstNumber()+ " = " + num);
        operationsHistory.setText(Data.getInstance().toString());
    }

    /**
     * A method that calculates the square and displays the result.
     */
    @FXML
    public void square(){
        if(input(currentOperation.getText())) {
            double num = Math.pow(Data.getInstance().getFirstNumber(), 2);
            output(num, "^2 = ");
        }
    }

    /**
     * A method handle plus button in app and change operation to addition.
     */
    @FXML
    public void plus(){
        if(input(currentOperation.getText())) {
            calc = Calc.PLUS;
            currentOperation.setText(Data.getInstance().getFirstNumber() + "+");
        }
    }

    /**
     * A method handle minus button in app and change operation to subtraction.
     */
    @FXML
    public void minus(){
        if(input(currentOperation.getText())) {
            calc = Calc.MINUS;
            currentOperation.setText(Data.getInstance().getFirstNumber() + "-");
        }
    }

    /**
     * A method that calculates the square root and displays the result.
     */
    @FXML
    public void root(){
        if(input(currentOperation.getText())){
            if(Data.getInstance().getFirstNumber() > 0){
                double num = Math.sqrt(Data.getInstance().getFirstNumber());
                output("sqrt:", num);
            } else{
                dialog.wrongDataType(simpleWindow.getScene().getWindow());
                currentOperation.clear();
            }
        }
    }

    /**
     * A method that calculates the percent and displays the result.
     */
    @FXML
    public void percent(){
        if(input(currentOperation.getText())){
            calc = Calc.PERCENT;
            currentOperation.setText(Data.getInstance().getFirstNumber()+"%");
        }
    }

    /**
     * A method that calculates the multiplicative inverse and displays the result.
     */
    @FXML
    public void multiplicativeInverse(){
        if(input(currentOperation.getText())){
            if(Data.getInstance().getFirstNumber() != 0){
                double num = 1/Data.getInstance().getFirstNumber();
                output("1/", num);
            } else{
                dialog.wrongDataType(simpleWindow.getScene().getWindow());
                currentOperation.clear();
            }
        }
    }

    /**
     * A method handle division button in app and change operation to division.
     */
    @FXML
    public void division(){
        if(input(currentOperation.getText())){
            calc = Calc.DIVIDED;
            currentOperation.setText(Data.getInstance().getFirstNumber()+"/");
        }
    }

    /**
     * A method handle multiplication button in app and change operation to multiplication.
     */
    @FXML
    public void multiplication(){
        if(input(currentOperation.getText())){
            calc = Calc.MULTIPLY;
            currentOperation.setText(Data.getInstance().getFirstNumber()+"*");
        }
    }

    /**
     * A method that change the symbol and displays the result.
     */
    @FXML
    public void reverseSymbol(){
        if(input(currentOperation.getText())){
            double num = -(Data.getInstance().getFirstNumber());
            output(num, " = ");
        }
    }

    /**
     * A method that carries out binary operation and display result.
     */
    @FXML
    public void equal() {
        String s = currentOperation.getText();
        if (!s.equals("")) {
            twoArgumentOperation.setOperation(currentOperation.getText(), calc);
            String operationResult = twoArgumentOperation.returnResult();
            if(twoArgumentOperation.getFlag() == TwoArgumentOperation.SUCCESS_FLAG) {
                Data.getInstance().addOperations(s + "=" + operationResult);
                operationsHistory.setText(Data.getInstance().toString());
                currentOperation.setText(operationResult);
                calc = Calc.NULL;
            } else if(twoArgumentOperation.getFlag() == TwoArgumentOperation.DATA_ERROR_FLAG){
                System.out.println("NumberFormatException");
                dialog.wrongDataType(simpleWindow.getScene().getWindow());
                currentOperation.clear();
            } else if(twoArgumentOperation.getFlag() == TwoArgumentOperation.OPERATION_ERROR_FLAG){
                System.out.println("IllegalStateException");
                dialog.incorrectOperation(simpleWindow.getScene().getWindow());
                currentOperation.clear();
            } else if(twoArgumentOperation.getFlag() == TwoArgumentOperation.ARRAY_ERROR_FLAG){
                System.out.println("IndexOutOfBoundsException");
                dialog.notEnoughData(simpleWindow.getScene().getWindow());
            }
        } else {
            System.out.println("IndexOutOfBoundsException");
            dialog.noData(simpleWindow.getScene().getWindow());
        }
    }

    /**
     * A method that delete last operation in calculator.
     */
    @FXML
    public void deleteLast(){
        if(calc.equals(Calc.NULL)){
            boolean t = Data.getInstance().deleteLast();
            if (t) {
                currentOperation.setText(String.valueOf(Data.getInstance().getFirstNumber()));
                operationsHistory.setText(Data.getInstance().toString());
            } else {
                dialog.noData(simpleWindow.getScene().getWindow());
            }
        } else {
            currentOperation.setText(String.valueOf(Data.getInstance().getFirstNumber()));
            calc = Calc.NULL;
        }

    }

    /**
     * A method that handles keyboard input.
     */
    @FXML
    public void type(){
        simpleWindow.setOnKeyPressed(event ->{
            KeyCode keyCode = event.getCode();
            if(keyCode.equals(KeyCode.ENTER)){
                equal();
            } else if(keyCode.equals(KeyCode.BACK_SPACE)){
                backspace();
            } else if(keyCode.equals(KeyCode.ADD)){
                plus();
            } else if(keyCode.equals(KeyCode.SUBTRACT)){
                minus();
            } else if(keyCode.equals(KeyCode.DIVIDE)){
                division();
            } else if(keyCode.equals(KeyCode.DECIMAL)){
                decimalSeparator();
            } else if(keyCode.equals(KeyCode.MULTIPLY)){
                multiplication();
            } else if(keyCode.equals(KeyCode.NUMPAD0)){
                zero();
            } else if(keyCode.equals(KeyCode.NUMPAD1)){
                one();
            } else if(keyCode.equals(KeyCode.NUMPAD2)){
                two();
            } else if(keyCode.equals(KeyCode.NUMPAD3)){
                three();
            } else if(keyCode.equals(KeyCode.NUMPAD4)){
                four();
            } else if(keyCode.equals(KeyCode.NUMPAD5)){
                five();
            } else if(keyCode.equals(KeyCode.NUMPAD6)){
                six();
            } else if(keyCode.equals(KeyCode.NUMPAD7)){
                seven();
            } else if(keyCode.equals(KeyCode.NUMPAD8)){
                eight();
            } else if(keyCode.equals(KeyCode.NUMPAD9)){
                nine();
            }
        });
    }

    /**
     * A method that delete last char that was added to calculator.
     */
    @FXML
    public void backspace(){
        try {
            int l = currentOperation.getText().length();
            currentOperation.deleteText(l-1, l);
        } catch (IndexOutOfBoundsException e){
            System.out.println("IndexOutOfBoundsException");
        } catch (NullPointerException e){
            System.out.println("Null point exception!");
        }
    }

    /**
     * A method that clear input filed in app.
     */
    @FXML
    public void clear(){
        currentOperation.clear();
    }

    /**
     * A method that call Dialog class object and show info about app.
     */
    @FXML
    public void aboutProgram(){
        dialog.aboutProgram(simpleWindow.getScene().getWindow());
    }

    /**
     * A method that clear all the history and current operation.
     */
    @FXML
    public void clearAll(){
        currentOperation.clear();
        Data.getInstance().clearHistory();
        operationsHistory.clear();
        Data.getInstance().setFirstNumber(0);
    }

    /**
     * A method that handle 0 button.
     */
    @FXML
    public void zero(){
        inputNumber("0");
    }

    /**
     * A method that handle 1 button.
     */
    @FXML
    public void one(){
        inputNumber("1");
    }

    /**
     * A method that handle 2 button.
     */
    @FXML
    public void two(){
        inputNumber("2");
    }

    /**
     * A method that handle 3 button.
     */
    @FXML
    public void three(){
        inputNumber("3");
    }

    /**
     * A method that handle 4 button.
     */
    @FXML
    public void four(){
        inputNumber("4");
    }

    /**
     * A method that handle 5 button.
     */
    @FXML
    public void five(){
        inputNumber("5");
    }

    /**
     * A method that handle 6 button.
     */
    @FXML
    public void six(){
        inputNumber("6");
    }

    /**
     * A method that handle 7 button.
     */
    @FXML
    public void seven(){
        inputNumber("7");
    }

    /**
     * A method that handle 8 button.
     */
    @FXML
    public void eight(){
        inputNumber("8");
    }

    /**
     * A method that handle 9 button.
     */
    @FXML
    public void nine(){
        inputNumber("9");
    }

    /**
     * A method that handle , button.
     */
    @FXML
    public void decimalSeparator(){
        inputNumber(".");
    }

    /**
     * A method that close app.
     */
    @FXML
    public void exit(){
        Platform.exit();
    }

}
