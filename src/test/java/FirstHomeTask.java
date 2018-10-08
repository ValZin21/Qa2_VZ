import org.apache.commons.math3.util.Precision;  // to use math functions
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;             //to read inputs from console using Java Scanner
import java.util.InputMismatchException;

public class FirstHomeTask {

    static Scanner valueReader = new Scanner(System.in);    // defining the Scanner

    @Test
    public static void main(String[] args) throws InputMismatchException {                //static String[] args needs to read the values from console
        Double loan;
        Double loanWithBankRates;
        Double stupidClerkCheck;

        System.out.print("Insert Clients Amount: ");
        loan = inputValidation();                    //user loan read+validation

        System.out.print("Clerk check: ");
        stupidClerkCheck = inputValidation();        //clerks calculated value read+validation

        loanWithBankRates = amountWithBankRatesCalculator(loan);   // calculating the loan with bank rates

        Assertions.assertEquals(stupidClerkCheck, loanWithBankRates, "You dummy clerk! Get back to school!");  //check if the clerk is compliant to work in our bank
    }

    private static Double amountWithBankRatesCalculator(Double loan){    //loan calculation with bank ratesmethod
        Double firstTenYearsRate = 1.1;
        Double secondTenYearsARate = 1.08;
        Double thirdTenYearsRate = 1.06;
        Double result = Precision.round(loan/3*firstTenYearsRate + loan/3*secondTenYearsARate + loan/3*thirdTenYearsRate,2);    // round of the total sum client needs to pay for 2 decimals
        System.out.println("Amount to pay with bank rates after 30 years: " + result);     // just to now how much moneys the Client needs to pay
        return result;
    }

    private static Double inputValidation () {   //  method to check for correct inputs
        Double testValue = null;
        boolean checker = false;

        while(!checker) {
            try {
                testValue = valueReader.nextDouble(); // reading the input value using the scanner;
                if (testValue < 0) {
                    throw new IllegalArgumentException("Please enter only positive numerics!");      // check for value is not negative
                }
                checker = true;    // make it valid and break the loop
            } catch (InputMismatchException e) {
                System.out.println("Please enter a numeric value!");
                valueReader.nextLine();                                                // check for value is a number
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());                                    // negative exception
                valueReader.nextLine();
            }
        }
        return testValue;
    }

}
