package dao.postgre;

import domain.entity.StudentEntity;
import dao.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentsDao {
private  DBConnection dbConnection = new DBConnection();

    public void insert(String firstName, String lastName) throws SQLException {

        String sql = "insert into students(first_name,last_name) values (?,?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnection.getConnection();
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



    public List<StudentEntity> read() throws SQLException {
        List<StudentEntity>result = new LinkedList<>();
        Connection connection=null;
        PreparedStatement statement = null;
        ResultSet rs;
        String sql = "select * from students;";

        try {
            connection= dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int groupId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                StudentEntity studentEntity = new StudentEntity(id,groupId,firstName,lastName);

                result.add(studentEntity);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


        return result;

    }


    public void update(int id,String firstName, String lastName)throws  SQLException{
    Connection connection = null;
    PreparedStatement statement = null;
    String sql = "update students set first_name=?,last_name=? where student_id=?;";



        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setInt(3,id);
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);;
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }
    }


    public void delete (int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from students where student_id=?;";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
         throw new RuntimeException("delete error");
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }


    }

}
