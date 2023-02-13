package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that contains all data uses in program and transfers between classes.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class Data {

    /**
     * Instance of this class.
     */
    private static Data instance = new Data();

    /**
     * History of all operation in app.
     */
    private List<String> operations = new ArrayList<>();

    /**
     * Field that contains first number in binary operations.
     */
    private double firstNumber;

    /**
     * Method that pass the object Data.
     * @return Instance of class Data.
     */
    public static Data getInstance(){
        return instance;
    }

    /**
     * Add operation to history.
     * @param s Operation that will be added to history.
     */
    public void addOperations(String s){
        operations.add(s);
    }

    /**
     * Remove all operation from history.
     */
    public void clearHistory(){
        operations.clear();
    }

    /**
     * Delete last operation from history.
     * @return Result of operation.
     */
    public boolean deleteLast(){
        try{
            int i = operations.size();
            operations.remove(i-1);
            return true;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    /**
     * Display all operations from history.
     * @return All orderly operations.
     */
    @Override
    public String toString() {
        String s = "";
        for(String operation : operations){
            s = operation + "\n" + s;
        }
        return s;
    }

    /**
     * Pass the first number.
     * @return Pass the first number.
     */
    public double getFirstNumber() {
        return firstNumber;
    }

    /**
     * Set the first number.
     * @param firstNumber Value of first number.
     */
    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

}
