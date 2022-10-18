package org.example;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static Data instance = new Data();

    private List<String> operations = new ArrayList<>();

    private double firstNumber;

    public static Data getInstance(){
        return instance;
    }

    public void addOperations(String s){
        operations.add(s);
    }

    public void clearHistory(){
        operations.clear();
    }

    public boolean deleteLast(){
        try{
            int i = operations.size();
            operations.remove(i-1);
            return true;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(String operation : operations){
            s = operation + "\n" + s;
        }
        return s;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

}
