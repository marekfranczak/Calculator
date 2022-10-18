package org.example;


public class TwoArgumentOperation {

    private String operation;
    private Calc calc;
    public static final int NONE_FLAG = 0;
    public static final int SUCCESS_FLAG = 1;
    public static final int DATA_ERROR_FLAG = 2;
    public static final int OPERATION_ERROR_FLAG = 3;
    public static final int ARRAY_ERROR_FLAG = 4;
    private int flag = NONE_FLAG;

    public void setOperation(String operation, Calc calc){
        this.operation = operation;
        this.calc = calc;
        flag = NONE_FLAG;
    }

    private String makeOperation() {
        String result;
        try {
            switch (calc) {
                case NULL: {
                    result = nullMethod();
                    break;
                }
                case PLUS: {
                    result = plusMethod();
                    break;
                }
                case MINUS: {
                    result = minusMethod();
                    break;
                }
                case DIVIDED: {
                    result = dividedMethod();
                    break;
                }
                case PERCENT: {
                    result = percentMethod();
                    break;
                }
                case MULTIPLY: {
                    result = multiplyMethod();
                    break;
                }
                case ROOT:{
                    result = rootMethod();
                    break;
                }
                case MODULO:{
                    result = moduloMethod();
                    break;
                }
                case POWER:{
                    result = powerMethod();
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + calc);
            }
        } catch (NumberFormatException e){
            flag = DATA_ERROR_FLAG;
            result = "";
        } catch (IllegalStateException e){
            flag = OPERATION_ERROR_FLAG;
            result = "";
        } catch (IndexOutOfBoundsException e){
            flag = ARRAY_ERROR_FLAG;
            result = "";
        }
        return result;
    }

    private String plusMethod(){
        String[] strings = operation.split("\\+");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(numOne + numTwo);
    }

    private String nullMethod(){
        double dD = (Double.parseDouble(operation) * 2);
        flag = SUCCESS_FLAG;
        return String.valueOf(dD);
    }

    private String minusMethod(){
        String[] strings = operation.split("-");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(numOne - numTwo);
    }

    private String dividedMethod(){
        String[] strings = operation.split("\\/");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        if(numTwo == 0){
            flag = DATA_ERROR_FLAG;
            return  "";
        } else {
            flag = SUCCESS_FLAG;
            return String.valueOf(numOne / numTwo);
        }
    }

    private String percentMethod(){
        String[] strings = operation.split("%");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf((numOne / 100) * numTwo);
    }

    private String multiplyMethod(){
        String[] strings = operation.split("\\*");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(numOne * numTwo);
    }

    private String rootMethod(){
        String[] strings = operation.split("\\^");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        if(numOne <= 0){
            flag = DATA_ERROR_FLAG;
            return "";
        } else {
            flag = SUCCESS_FLAG;
            return String.valueOf(Math.pow(numOne, 1/numTwo));
        }
    }

    private String moduloMethod(){
        String[] strings = operation.split("mod");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        if(numTwo == 0){
            flag = DATA_ERROR_FLAG;
            return  "";
        } else {
            flag = SUCCESS_FLAG;
            return String.valueOf((int)numOne % (int)numTwo);
        }
    }

    private String powerMethod(){
        String[] strings = operation.split("\\^");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(Math.pow(numOne, numTwo));
    }

    public String returnResult(){
        String s = makeOperation();
        if(!s.equals("")){
            return s;
        } else
            return "";
    }

    public int getFlag() {
        return flag;
    }

    public long factorial(int n){
        long frac = 1;
        if(n == 0 || n == 1){
            return 1;
        } else {
            for(int i=2; i<=n; i++)
                frac = frac*i;
            return frac;
        }
    }
}
