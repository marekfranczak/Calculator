package org.example;

/**
 * Interface that provides mathematical operation in Simple Calculator.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public interface SimpleCalc {

    /**
     * A method that sets the mathematical operation to the square.
     */
    public void square();

    /**
     * A method that sets the mathematical operation to the plus.
     */
    public void plus();

    /**
     * A method that sets the mathematical operation to the minus.
     */
    public void minus();

    /**
     * A method that sets the mathematical operation to the square root.
     */
    public void root();

    /**
     * A method that sets the mathematical operation to the percent.
     */
    public void percent();

    /**
     * A method that sets the mathematical operation to the 1/x.
     */
    public void multiplicativeInverse();

    /**
     * A method that sets the mathematical operation to the division.
     */
    public void division();

    /**
     * A method that sets the mathematical operation to the multiplication.
     */
    public void multiplication();

    /**
     * A method that reverse symbol of number.
     */
    public void reverseSymbol();

    /**
     * A method that sets the mathematical operation to the equal.
     */
    public void equal();

}
