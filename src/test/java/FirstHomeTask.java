import org.apache.commons.math3.util.Precision;  // to use math functions
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;             //to read inputs from console using Java Scanner

public class FirstHomeTask {

    @Test
    public static void main(String[] args) {                //static String[] args needs to read the values from console
        Double loan;
        Double loanWithBankRates;
        Double stupidClerkCheck;

        Scanner valueReader = new Scanner(System.in);       // defining the Scanner

        System.out.print("Insert Clients Amount: ");
        loan = valueReader.nextDouble();                    // reading the loan value using the scanner;
        System.out.print("Clerk check: ");
        stupidClerkCheck = valueReader.nextDouble();        // reading the clerks calculated value with bank rates;

        loanWithBankRates = amountWithBankRatesCalculator(loan);

        Assertions.assertEquals(stupidClerkCheck, loanWithBankRates, "You dummy clerk! Get back to school!");  //check if the clerk is compliant to work in our bank
    }

    private static Double amountWithBankRatesCalculator(Double a){
        Double firstTenYearsRate = 1.1;
        Double secondTenYearsARate = 1.08;
        Double thirdTenYearsRate = 1.06;
        Double result = Precision.round(a/3*firstTenYearsRate + a/3*secondTenYearsARate + a/3*thirdTenYearsRate,2);    // round of the total sum client needs to pay for 2 decimals
        System.out.println("Amount to pay with bank rates after 30 years: " + result);     // just to now how much moneys the Client needs to pay
        return result;
    }
}
