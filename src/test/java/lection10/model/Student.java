package lection10.model;

import java.util.List;

public class Student {
    int height;
    String name;
    int age;
    int weight;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
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
