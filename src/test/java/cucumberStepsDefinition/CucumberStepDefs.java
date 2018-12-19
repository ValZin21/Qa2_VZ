package cucumberStepsDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lection10.model.Student;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

public class CucumberStepDefs {

    Student student = new Student();
    @Given("Print test annotation (.*)")
    public void printTestAnnotation(String annotation) {
        System.out.println(annotation);
    }

    @Given("Student (.*)")
    public void set_student(String name) {
        student.setName(name);
    }

    @Given("age ([0-9]*)")
    public void set_age(Integer age) {
        student.setAge(age);
    }

    @Given("Height ([0-9]*)")
    public void set_height(Integer height) {
        student.setAge(height);
    }

    @Given("Weight ([0-9]*)")
    public void set_weight(Integer weight) {
        student.setAge(weight);
    }

    @Given("student:")
    public void set_student (Map<String, String> params) {
        student.setName(params.get("name"));
        student.setAge(Integer.valueOf(params.get("age")));
        student.setHeight(Integer.valueOf(params.get("height")));
        student.setWeight(Integer.valueOf(params.get("weight")));
    }

    @When("we requesting name and age together")
    public void get_nameAge() {
        String nameAge = student.nameAgeGet();
    }

    @Then("response must be (.*)")
    public void check_result(String toCheck) {
        Assertions.assertEquals(toCheck, student.nameAgeGet(), "Some fail accured");
    }

}
