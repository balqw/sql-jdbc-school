package ua.com.foxminded.domain.entity;

public class GroupEntity {
    private int group_id;
    private String name;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
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
        this.group_id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupEntity{" +
                "group_id=" + group_id +
                ", name='" + name + '\'' +
                '}';
    }
}
