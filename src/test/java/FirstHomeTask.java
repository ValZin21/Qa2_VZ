import org.apache.commons.math3.util.Precision;  // to use math functions
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;             //to read inputs from console using Java Scanner

public class FirstHomeTask {

    private static Double firstTenYearsRate = 1.1;
    private static Double secondTenYearsARate = 1.08;
    private static Double thirdTenYearsRate = 1.06;

    @Test
    public static void main(String[] args) {             // String[] args needs to read the values from console

        Double loan;
        Double loanWithBankRates;
        Double stupidClerkCheck;

        Scanner valueReader = new Scanner(System.in);    // defining the Scanner

        System.out.print("Insert Clients Amount: ");
        loan = valueReader.nextDouble();                    // reading the loan value using the scanner;
        System.out.print("Clerk check: ");
        stupidClerkCheck = valueReader.nextDouble();        // reading the clerks calculated value with bank rates;

        loanWithBankRates = amountWithBankRatesCalculator(loan, firstTenYearsRate, secondTenYearsARate, thirdTenYearsRate);

        System.out.println("Amount to pay with bank rates after 30 years: " + loanWithBankRates);

        Assertions.assertEquals(stupidClerkCheck, loanWithBankRates, "You dummy clerk! Get back to school!");
    }

    private static Double amountWithBankRatesCalculator(Double a, Double b, Double c, Double d){
        Double result = Precision.round(a/3*b + a/3*c + a/3*d,2);    // round of the total sum client needs to pay for 2 decimals
        return result;
    }
}
