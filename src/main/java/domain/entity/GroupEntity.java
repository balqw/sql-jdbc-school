package domain.entity;

public class GroupEntity {
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

    public GroupEntity(String name) {
        this.name = name;
    }

    public GroupEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
