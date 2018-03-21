package no.hiof.martr.com.movie.model;
import java.time.LocalDate;
import java.util.ArrayList;


public abstract class Production {

    private String title;
    private String description;
    private LocalDate releaseDate;
    private int runtime;
    private Person director;
    private ArrayList<Role> roleList = new ArrayList<>();

    // Constructors

    public Production(String title, String description, LocalDate releaseDate, int runtime) {
        this.title = title;
        this.description = description;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
    }

    public Production(String title, String description, LocalDate releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    // Methods

    public void addSingleRole(Role role) {
        this.roleList.add(role);
    }

    public void addMultipleRoles (ArrayList<Role> roles) {
        this.roleList.addAll(roles);
    }

    public void printRoles() {
        for (Role role : this.roleList) {
            System.out.println(role);
        }
    }

    // Getters and Setters

    public ArrayList<Role> getRoleList() {
        return roleList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return this.title;
    }
}