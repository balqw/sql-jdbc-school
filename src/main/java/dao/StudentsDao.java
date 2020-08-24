package dao;

import domain.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentsDao {
private static FactoryDB factoryDB = new FactoryDB();


    public void insert(String firstName, String lastName) throws SQLException {

        String sql = "insert into students(first_name,last_name) values (?,?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = factoryDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.execute();

            System.out.println("Student " + firstName + " " + lastName + " has been inserted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }
    }



    public List<Student> takeAll() throws SQLException {
        List<Student>result = new LinkedList<>();
        Connection connection=null;
        PreparedStatement statement = null;
        ResultSet rs;
        String sql = "select * from students;";

        try {
            connection= factoryDB.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int groupId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                Student student = new Student(id,groupId,firstName,lastName);

                result.add(student);
            }

            System.out.println("successful");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }


        return result;

    }


}
