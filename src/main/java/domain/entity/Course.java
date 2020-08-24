package domain.entity;

public class Course {
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course( String name, String description) {
        this.name = name;
        this.description = description;
    }
}
