package ua.com.foxminded.domain.entity;

import java.util.Objects;

public class GroupEntity {
    private int groupId;
    private String name;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public GroupEntity(int groupId, String name) {
        this.groupId = groupId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "GroupEntity{" +
                "group_id=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupEntity that = (GroupEntity) o;
        return groupId == that.groupId &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name);
    }
}
