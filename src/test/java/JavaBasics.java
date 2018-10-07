import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaBasics {
    private String text;   //tk peremennaja nuzhna tolko v predelah klass

    @Test
    public void ourFirstTest() {    //test returns nothing!
        Integer a = 15;
        Integer b = 99;

        Integer c = 95;
        Integer d = 150;

      //  String text = "The sum is: "; // if char - then single quotes 'text's

      //  Integer sum = a + b;

      //  System.out.println(text + sum);    // output to console, strings combines with "+" symbol

      //  Integer sum2 = c + d;             // dublikat

      //  System.out.println(text + sum2);  // dublicat

        text = "The sum is: ";              // metod bez dublikatov

        sumAndPrint(a, b);

        sumAndPrint(c, d);

        Assertions.assertEquals(245, c + d, "wrong sum!");   //primer proverki
    }

    private void sumAndPrint(Integer a, Integer b) {   //metod izbavlenija ot dublikata
        Integer sum = a + b;
        System.out.println(text + sum);
    }

}
