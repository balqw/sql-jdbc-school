package ua.com.foxminded.dao;

import ua.com.foxminded.dao.config.DBConnection;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class StudentsDao {
    private DBConnection dbConnection = new DBConnection();
    private static final String ADD_QUERY = "insert into students(first_name,last_name,group_id) values (?,?,?);";
    private static final String SELECT_QUERY = "select * from students;";
    private static final String UPDATE_QUERY = "update students set first_name=?,last_name=?,group_id=? where student_id=?;";
    private static final String DELETE_QUERY= "delete from students where student_id=?;";

    public StudentEntity insert(StudentEntity studentEntity){
        PreparedStatement statement = null;
        ResultSet rs;
        try(Connection connection = dbConnection.getConnection()){
            statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, studentEntity.getFirst_name());
            statement.setString(2, studentEntity.getLast_name());
            statement.setInt(3,studentEntity.getGroup_id());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
                studentEntity.setStudent_id(rs.getInt("student_id"));
        } catch (SQLException e) {
        throw new RuntimeException("Insert student failed");
        }
        return studentEntity;
    }


    public List<StudentEntity> read(){
        List<StudentEntity>result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet rs;
        try (Connection connection= dbConnection.getConnection()){
            statement = connection.prepareStatement(SELECT_QUERY);
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int groupId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                StudentEntity studentEntity = new StudentEntity(id,groupId,firstName,lastName);
                result.add(studentEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Select student failed");
        }
        return result;
    }


    public StudentEntity update(StudentEntity studentEntity){
    PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1,studentEntity.getFirst_name());
            statement.setString(2,studentEntity.getLast_name());
            statement.setInt(3,studentEntity.getGroup_id());
            statement.setInt(4,studentEntity.getStudent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update student failed");
        }
        return studentEntity;
    }


    public void deleteById (int id){
        PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DeleteById student failed");
        }
    }


    public void delete(StudentEntity studentEntity){
        PreparedStatement statement = null;
        try (Connection connection = dbConnection.getConnection()){
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,studentEntity.getStudent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete student failed");
        }
    }

}
