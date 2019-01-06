package lection10.model;

import java.util.List;

public class Student {
    private int height;
    private String name;
    private int age;
    private int weight;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String nameAgeGet() {
        return name + ": " + String.valueOf(age);
    }

    public String namesAgesGetFromExample(String name2, String age2) {
        return name2 + ": " + age2;
    }

    public String namesAgesGetFromList(List<Student> students, int element) {
        return students.get(element).name + ": " + String.valueOf(students.get(element).age);
    }
}
