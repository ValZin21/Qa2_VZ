import org.apache.commons.math3.util.Precision;  // to use math functions
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;             //to read inputs from console using Java Scanner
import java.util.InputMismatchException;

public class FirstHomeTask {

    static Scanner valueReader = new Scanner(System.in);    // defining the Scanner

    @Test
    public static void main(String[] args) {                //static String[] args needs to read the values from console
        Double loan;
        Double loanWithBankRates;
        Double stupidClerkCheck;

       // System.out.print("Insert Clients Amount: ");
        isDouble();
        loan = negativeValueCheck(valueReader.nextDouble());                     // reading the loan value using the scanner and validating it;

        System.out.print("Clerk check: ");
        stupidClerkCheck = negativeValueCheck(valueReader.nextDouble());        // reading the clerks calculated value with bank rates and validating it;

        loanWithBankRates = amountWithBankRatesCalculator(loan);

        Assertions.assertEquals(stupidClerkCheck, loanWithBankRates, "You dummy clerk! Get back to school!");  //check if the clerk is compliant to work in our bank
    }

    private static Double amountWithBankRatesCalculator(Double loan){
        Double firstTenYearsRate = 1.1;
        Double secondTenYearsARate = 1.08;
        Double thirdTenYearsRate = 1.06;
        Double result = Precision.round(loan/3*firstTenYearsRate + loan/3*secondTenYearsARate + loan/3*thirdTenYearsRate,2);    // round of the total sum client needs to pay for 2 decimals
        System.out.println("Amount to pay with bank rates after 30 years: " + result);     // just to now how much moneys the Client needs to pay
        return result;
    }

    private static Double negativeValueCheck (Double testValue){
        do {
            System.out.print("Clients Amount can't be negative! Try again: ");
            testValue = valueReader.nextDouble();
        } while (testValue < 0);
        return testValue;
    }

    private static Double isDouble (){   // not working
        Double testValue;
        boolean checker = true;
        
        do {
            try {
                System.out.print("Insert Clients Amount: ");
                testValue = valueReader.nextDouble();
                checker = false;
            }
            catch (InputMismatchException e) {
                System.out.println(testValue + " is not a number");
               // checker = false;
                valueReader.next();
            }
//            finally {
//                    System.out.println(testValue + " is not a number");
//            }
        } while (checker);
        return testValue;
    }
}
