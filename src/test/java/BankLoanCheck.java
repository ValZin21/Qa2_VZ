import org.apache.commons.math3.util.Precision;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.util.InputMismatchException;

public class BankLoanCheck {

    private static Scanner valueReader = new Scanner(System.in);

    @Test
    public static void main(String[] args) throws InputMismatchException {
        Double loan;
        Double loanWithBankRates;
        Double clerkCalculationCheck;

        System.out.print("Insert Clients Amount: ");
        loan = inputValidation();

        System.out.print("Clerk check: ");
        clerkCalculationCheck = inputValidation();

        loanWithBankRates = amountWithBankRatesCalculator(loan);

        Assertions.assertEquals(clerkCalculationCheck, loanWithBankRates, "Clerk calculation is wrong!");
    }

    private static Double amountWithBankRatesCalculator(Double loan){
        Double firstTenYearsRate = 1.1;
        Double secondTenYearsARate = 1.08;
        Double thirdTenYearsRate = 1.06;

        Double result = Precision.round(loan/3*firstTenYearsRate + loan/3*secondTenYearsARate + loan/3*thirdTenYearsRate,2);
        System.out.println("Amount to pay with bank rates after 30 years: " + result);
        return result;
    }

    private static Double inputValidation () {
        Double testValue = null;
        boolean checker = false;

        while(!checker) {
            try {
                testValue = valueReader.nextDouble();
                if (testValue < 0) {
                    throw new IllegalArgumentException("Please enter only positive numerics!");
                }
                checker = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a numeric value!");
                valueReader.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                valueReader.nextLine();
            }
        }
        return testValue;
    }

}
