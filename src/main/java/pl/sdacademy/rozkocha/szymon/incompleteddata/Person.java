package pl.sdacademy.rozkocha.szymon.incompleteddata;

import java.util.Optional;

public class Person {
    private String name;
    private String surname;
    private Double height;
    private Double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Optional<Double> getHeight() {
        return Optional.ofNullable(height);
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Optional<Double> getWeight() {
        return Optional.ofNullable(weight);
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "[" + name +
                ", " + surname +
                ((height != null) ? ", " + height : "") +
                ((weight != null) ? ", " + weight : "") +
                ']';
    }
}
