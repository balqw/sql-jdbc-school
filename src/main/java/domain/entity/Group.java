package domain.entity;

public class Group {
    private int id;
    private String name;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group( String name) {
        this.name = name;
    }
}
