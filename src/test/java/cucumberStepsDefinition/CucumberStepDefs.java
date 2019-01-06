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

    private String nameAge;
    private List<Student> students = new ArrayList<Student>();
    private List<String> namesAges = new ArrayList<String>();
    private Student student = new Student();

    @Given("Print test annotation (.*)")
    public void printTestAnnotation(String annotation) {
        System.out.println(annotation);
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
        Student student2 = new Student();
        student2.setName(params.get("name"));
        student2.setAge(Integer.valueOf(params.get("age")));
        student2.setHeight(Integer.valueOf(params.get("height")));
        student2.setWeight(Integer.valueOf(params.get("weight")));
        students.add(student2);
        System.out.println(students.size());
        for (int i = 0; i < students.size(); i++) {
            System.out.println("given iteration " + i + " = " + students.get(i).getName());
        }
    }

    @Given("students from example")
    public void set_studentsFromExample() {
        System.out.println("Working with student from Example");
    }

    @When("we requesting name and age together")
    public void get_nameAge() {
        nameAge = student.nameAgeGet();
    }

    @When("we are requesting (.*) and (.*) together from example")
    public void get_namesAges(String name, String age) {
        nameAge = student.namesAgesGetFromExample(name, age);

    }

    @When("we requesting names and ages together")
    public void get_namesAges() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Iteration get students name: " + i + " equals: " + students.get(i).getName());
            namesAges.add(students.get(i).nameAgeGet());
            System.out.println("Iteration get namesAges: " + i + " equals: " + namesAges.get(i));
        }
    }

    @Then("response must be (.*)")
    public void check_result(String toCheck) {
        System.out.println("Student is: " + toCheck);
        System.out.println("Student should be : " + nameAge);
        Assertions.assertTrue(nameAge.equals(toCheck), "Some fail occured");
    }

    @Then("responses must be (.*)")
    public void check_results(String toCheck) {
        boolean check  = false;
        for (int i = 0; i < namesAges.size(); i++) {
            if (namesAges.get(i).equals(toCheck)) {
                check = true;
                System.out.println("Iteration set: " + i + " equals: " + namesAges.get(i));
                break;
            }
        }
        Assertions.assertTrue(check == true, "Some fail occured");
    }

}
