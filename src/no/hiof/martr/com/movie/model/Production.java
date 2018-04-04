package no.hiof.martr.com.movie.model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a filmed production. Contains information about the title, a description, the release date, the length of
 * the production (runtime), director and a list of {@link Role Roles}.
 *
 * @author Martin
 */

public abstract class Production {

    private String title;
    private String description;
    private LocalDate releaseDate;
    private int runtime;
    private Person director;
    private ArrayList<Role> roleList = new ArrayList<>();

    // Constructors

    /**
     * Default constructor with all parameters
     * @param title the title of the production
     * @param description a short description of the production
     * @param releaseDate the release date of the production
     * @param runtime - the runtime (length) of the production in minutes
     */
    public Production(String title, String description, LocalDate releaseDate, int runtime) {
        this.title = title;
        this.description = description;
        this.runtime = runtime;
        this.releaseDate = releaseDate;
    }

    /**
     * Constructor without specifying runtime
     * @param title the title of the production
     * @param description a short description of the production
     * @param releaseDate the release date of the production
     */
    public Production(String title, String description, LocalDate releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    // Methods

    /**
     * Adds a single role to the roleList
     * @param role the Role object to be added
     */
    public void addSingleRole(Role role) {

        this.roleList.add(role);
    }

    /**
     * Takes an ArrayList of roles and adds it to the roleList
     * @param roles the ArrayList of Role objects to be added
     */
    public void addMultipleRoles (ArrayList<Role> roles) {
        this.roleList.addAll(roles);
    }

    /**
     * Prints out the roleList to System.out
     */
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