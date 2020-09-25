package ua.com.foxminded.domain.entity;

import java.util.Objects;

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

    public GroupEntity(int group_id, String name) {
        this.group_id = group_id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "GroupEntity{" +
                "group_id=" + group_id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return group_id == that.group_id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group_id, name);
    }
}
