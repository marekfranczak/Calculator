package org.example;


/**
 * Class that support binary operations.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class TwoArgumentOperation {

    /**
     * Data on which the operation will be performed.
     */
    private String operation;
    /**
     * Kind of operation that will be performed.
     */
    private Calc calc;
    public static final int NONE_FLAG = 0;
    public static final int SUCCESS_FLAG = 1;
    public static final int DATA_ERROR_FLAG = 2;
    public static final int OPERATION_ERROR_FLAG = 3;
    public static final int ARRAY_ERROR_FLAG = 4;

    /**
     * Kind of exception that appeared.
     */
    private int flag = NONE_FLAG;

    /**
     * Set the data to operation and kind of it.
     * @param operation Data on which the operation will be performed.
     * @param calc Kind of operation that will be performed.
     */
    public void setOperation(String operation, Calc calc){
        this.operation = operation;
        this.calc = calc;
        flag = NONE_FLAG;
    }

    /**
     * Method that calls the appropriate method based on the selected action type.
     * @return Result of operation.
     */
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

    /**
     * Method that perform adding operation.
     * @return Result of adding.
     */
    private String plusMethod(){
        String[] strings = operation.split("\\+");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(numOne + numTwo);
    }

    /**
     * Method that perform multiplication by 2. It is called when any operator was chosen.
     * @return Result of multiplication.
     */
    private String nullMethod(){
        double dD = (Double.parseDouble(operation) * 2);
        flag = SUCCESS_FLAG;
        return String.valueOf(dD);
    }

    /**
     * Method that perform subtraction operation.
     * @return Result of subtraction.
     */
    private String minusMethod(){
        String[] strings = operation.split("-");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(numOne - numTwo);
    }

    /**
     * Method that perform division operation.
     * @return Result of division.
     */
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

    /**
     * Method that performs a percentage calculation of a number
     * @return Result of operation.
     */
    private String percentMethod(){
        String[] strings = operation.split("%");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf((numOne / 100) * numTwo);
    }

    /**
     * Method that perform multiplication operation.
     * @return Result of multiplication.
     */
    private String multiplyMethod(){
        String[] strings = operation.split("\\*");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(numOne * numTwo);
    }

    /**
     * Method that perform nth root operation.
     * @return Result of nth root.
     */
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

    /**
     * Method that performs a modulo calculation of a number
     * @return Result of operation.
     */
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

    /**
     * Method that perform exponential operation.
     * @return Result of exponential.
     */
    private String powerMethod(){
        String[] strings = operation.split("\\^");
        double numOne = Double.parseDouble(strings[0]);
        double numTwo = Double.parseDouble(strings[1]);
        flag = SUCCESS_FLAG;
        return String.valueOf(Math.pow(numOne, numTwo));
    }

    /**
     * Method that call makeOperation method and return result.
     * @return Result of math operation.
     */
    public String returnResult(){
        String s = makeOperation();
        if(!s.equals("")){
            return s;
        } else
            return "";
    }

    /**
     * Return exception or successful operation flag.
     * @return Flog result.
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Factorial calculation.
     * @param n Number for which the factorial will be calculated.
     * @return Result of factorial operation.
     */
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
