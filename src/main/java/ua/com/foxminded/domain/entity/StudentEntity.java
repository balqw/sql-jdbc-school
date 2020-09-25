package ua.com.foxminded.domain.entity;

import com.sun.deploy.security.SelectableSecurityManager;

import java.util.Objects;

public class StudentEntity {
    private int student_id;
    private int group_id ;
    private String first_name;
    private String last_name;


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
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

    public StudentEntity(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public StudentEntity(int group_id, String first_name, String last_name) {
        this.group_id = group_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public StudentEntity(int student_id, int group_id, String first_name, String last_name) {
        this.student_id = student_id;
        this.group_id = group_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /*@Override
    public String toString() {
        return "id="+student_id+
                ", group_id="+group_id+
                ", first_name="+first_name+
                ", last_name="+last_name+" "+"\n";
    }*/
    @Override
    public String toString() {
        if(group_id==0) {
            return "first_name=" + first_name +
                    " last_name=" + last_name + "\n";
        }
            else
            return "id=" + student_id +
                    ", group_id=" + group_id +
                    ", first_name=" + first_name +
                    ", last_name=" + last_name + " " + "\n";


        }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return student_id == that.student_id &&
                group_id == that.group_id &&
                first_name.equals(that.first_name) &&
                last_name.equals(that.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student_id, group_id, first_name, last_name);
    }
}
