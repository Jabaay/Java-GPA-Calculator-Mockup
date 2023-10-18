/*
 * @author gongj3@miamioh.edu
 * 
 * This class is used for setting up some methods for computing some values 
 * easily in GPACalculator.java.
 * 
 */

public class DataHolder {

    /**
     * This method returns a warning message that the course name is not valid.
     * 
     * @return a predefined string
     */
    public String invalidName() {
        return " " + "Invalid Course Name !";
    }

    /**
     * This method returns a warning message that the course name shouldn't be
     * empty.
     * 
     * @return a predefined string
     */
    public String invalidCSName() {
        return " " + "Course Name cannot be empty !";
    }

    /**
     * This method returns a warning message that the previous credits cannot be
     * empty
     * 
     * @return a predefined string
     */
    public String emptyCredits() {
        return " " + "Previous Credits cannot be Empty !";
    }

    /**
     * 
     * @return a predefined string that the previous GPA cannot be empty.
     */
    public String emptyGPA() {
        return " " + "Previous GPA cannot be Empty !";
    }

    /**
     * This method returns a formatted string.
     * 
     * @param str1 String
     * @param str2 String
     * @param obj1 Object
     * @param obj2 Object
     * @return a formatted string
     */
    public String format1(String str1, String str2, Object obj1, Object obj2) {
        return str1 + " " + str2 + " | Credits: " + obj1 + " | Grade: " + obj2
                + "\n";
    }

    /**
     * This method
     * 
     * @return
     */
    public String convertCredits(int num1, int num2) {
        return num1 + num2 + "";
    }

    /**
     * This method calculates the product of two numbers.
     * 
     * @param num1 Double variable
     * @param num2 Double variable
     * @return the product which is a Double variable
     */
    public double calculateVP(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * This method calculates the quotient of two numbers.
     * 
     * @param num1 Double variable
     * @param num2 Double variable
     * @return the quotient which is a Double variable
     */
    public double calculateTolGPA(double num1, double num2) {
        return num1 / num2;
    }

    /**
     * This method calculates the average of two numbers.
     * 
     * @param num1 Double variable
     * @param num2 Double variable
     * @return the midpoint which is a Double variable
     */
    public double calculateMid(double num1, double num2) {
        return (num1 + num2) / 2;
    }

}
