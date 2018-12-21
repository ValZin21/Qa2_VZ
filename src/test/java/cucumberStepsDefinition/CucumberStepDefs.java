package cucumberStepsDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lection10.model.Student;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CucumberStepDefs {

    List<Student> students = new ArrayList<Student>();
    List<String> namesAges = new ArrayList<String>();
    Student student = new Student();

    @Given("Print test annotation (.*)")
    public void printTestAnnotation(String annotation) {
        System.out.println(annotation);
    }

    @Given("Student (.*)")
    public void set_student(String name) {
        student.setName(name);
    }

    @Given("Students (.*)")
    public void set_students(String name) {
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

    @Given("students:")
    public void set_students (Map<String, String> params) {
        student.setName(params.get("name"));
        student.setAge(Integer.valueOf(params.get("age")));
        student.setHeight(Integer.valueOf(params.get("height")));
        student.setWeight(Integer.valueOf(params.get("weight")));
        students.add(student);
    }

    @When("we requesting name and age together")
    public void get_nameAge() {
        String nameAge = student.nameAgeGet();
    }

    @When("we requesting names and ages together")
    public void get_namesAges() {
        for (int i = 0; i < namesAges.size(); i++) {
            namesAges.add(students.get(i).nameAgeGet());
            System.out.println("Iteration get: " + i + " equals: " + namesAges.get(i));
        }

    }

    @Then("response must be (.*)")
    public void check_result(String toCheck) {
        Assertions.assertTrue(students.equals(toCheck), "Some fail occured");
    }

    @Then("responses must be (.*)")
    public void check_results(String toCheck) {
        boolean check  = false;
        for (int i = 0; i < namesAges.size(); i++) {
            if (namesAges.get(i).equals(toCheck));
//            check = true;
            System.out.println("Iteration set: " + i + " equals: " + namesAges.get(i));
            break;
        }
//        Assertions.assertEquals(true, check, "Some fail occured");

        Assertions.assertTrue(namesAges.equals(toCheck), "Some fail occured");

    }

}
