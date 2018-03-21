package no.hiof.martr.com.movie.model;

public class Role {
    private String firstName;
    private String lastName;
    private Person actor;

    // Constructors

    public Role(String firstName, String lastName, Person actor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.actor = actor;
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

    public Person getActor() {
        return actor;
    }

    public void setActor(Person actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}