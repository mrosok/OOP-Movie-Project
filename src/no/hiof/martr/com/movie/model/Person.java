package no.hiof.martr.com.movie.model;

public class Person {
    private String firstName;
    private String lastName;
    private String nationality;

    // Constructors

    public Person(String firstName, String lastName, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
    }

    // Methods

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    //Task 8
    @Override
    public String toString() {
        return this.getFullName();
    }
}
