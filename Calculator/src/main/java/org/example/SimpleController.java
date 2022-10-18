package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;


public class SimpleController implements SimpleCalc{

    @FXML
    private BorderPane simpleWindow;
    @FXML
    private TextField currentOperation;
    @FXML
    private TextArea operationsHistory;

    private Calc calc = Calc.NULL;
    private final TwoArgumentOperation twoArgumentOperation = new TwoArgumentOperation();
    private final Dialog dialog = new Dialog();

    @FXML
    public void changeCalculator() throws Exception {
        App.setRoot("scienwindow");
    }

    @FXML
    public void initialize(){
        operationsHistory.setText(Data.getInstance().toString());
    }

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

    private void inputNumber(String s){
        String text = currentOperation.getText();
        text = text+s;
        currentOperation.setText(text);
    }

    private void output(double num, String s){
        currentOperation.setText(String.valueOf(num));
        Data.getInstance().addOperations(Data.getInstance().getFirstNumber() + s + num);
        operationsHistory.setText(Data.getInstance().toString());
    }

    private void output(String s, double num){
        currentOperation.setText(String.valueOf(num));
        Data.getInstance().addOperations(s + Data.getInstance().getFirstNumber()+ " = " + num);
        operationsHistory.setText(Data.getInstance().toString());
    }

    @FXML
    public void square(){
        if(input(currentOperation.getText())) {
            double num = Math.pow(Data.getInstance().getFirstNumber(), 2);
            output(num, "^2 = ");
        }
    }

    @FXML
    public void plus(){
        if(input(currentOperation.getText())) {
            calc = Calc.PLUS;
            currentOperation.setText(Data.getInstance().getFirstNumber() + "+");
        }
    }

    @FXML
    public void minus(){
        if(input(currentOperation.getText())) {
            calc = Calc.MINUS;
            currentOperation.setText(Data.getInstance().getFirstNumber() + "-");
        }
    }

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

    @FXML
    public void percent(){
        if(input(currentOperation.getText())){
            calc = Calc.PERCENT;
            currentOperation.setText(Data.getInstance().getFirstNumber()+"%");
        }
    }

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

    @FXML
    public void division(){
        if(input(currentOperation.getText())){
            calc = Calc.DIVIDED;
            currentOperation.setText(Data.getInstance().getFirstNumber()+"/");
        }
    }

    @FXML
    public void multiplication(){
        if(input(currentOperation.getText())){
            calc = Calc.MULTIPLY;
            currentOperation.setText(Data.getInstance().getFirstNumber()+"*");
        }
    }

    @FXML
    public void reverseSymbol(){
        if(input(currentOperation.getText())){
            double num = -(Data.getInstance().getFirstNumber());
            output(num, " = ");
        }
    }

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

    @FXML
    public void clear(){
        currentOperation.clear();
    }

    @FXML
    public void aboutProgram(){
        dialog.aboutProgram(simpleWindow.getScene().getWindow());
    }

    @FXML
    public void clearAll(){
        currentOperation.clear();
        Data.getInstance().clearHistory();
        operationsHistory.clear();
        Data.getInstance().setFirstNumber(0);
    }

    @FXML
    public void zero(){
        inputNumber("0");
    }

    @FXML
    public void one(){
        inputNumber("1");
    }

    @FXML
    public void two(){
        inputNumber("2");
    }

    @FXML
    public void three(){
        inputNumber("3");
    }

    @FXML
    public void four(){
        inputNumber("4");
    }

    @FXML
    public void five(){
        inputNumber("5");
    }

    @FXML
    public void six(){
        inputNumber("6");
    }

    @FXML
    public void seven(){
        inputNumber("7");
    }

    @FXML
    public void eight(){
        inputNumber("8");
    }

    @FXML
    public void nine(){
        inputNumber("9");
    }

    @FXML
    public void decimalSeparator(){
        inputNumber(".");
    }

    @FXML
    public void exit(){
        Platform.exit();
    }

}
