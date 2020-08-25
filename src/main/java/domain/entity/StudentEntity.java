package domain.entity;

public class StudentEntity {
    private int id;
    private int group_id ;
    private String first_name;
    private String last_name;


    public int getId() {
        return id;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public StudentEntity() {

    }

    public StudentEntity(int group_id, String first_name, String last_name) {
        this.group_id = group_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public StudentEntity(int id, int group_id, String first_name, String last_name) {
        this.id = id;
        this.group_id = group_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
