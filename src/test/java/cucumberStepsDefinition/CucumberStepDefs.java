package cucumberStepsDefinition;

import cucumber.api.java.en.Given;

public class CucumberStepDefs {
    @Given("Print test annotation (.*)")
    public void printTestAnnotation(String annotation) {
        System.out.println(annotation);
    }
}
