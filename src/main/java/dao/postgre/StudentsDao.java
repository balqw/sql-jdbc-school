package dao.postgre;

import domain.entity.StudentEntity;
import dao.DBConnection;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class StudentsDao {
private  DBConnection dbConnection = new DBConnection();

    public StudentEntity insert(StudentEntity studentEntity){

        String sql = "insert into students(first_name,last_name,group_id) values (?,?,?);";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs;
        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, studentEntity.getFirst_name());
            statement.setString(2, studentEntity.getLast_name());
            statement.setInt(3,studentEntity.getGroup_id());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
                studentEntity.setStudent_id(rs.getInt("student_id"));
        } catch (SQLException e) {
            throw new RuntimeException("Insert student is fail");
        } finally {
            try{
                assert statement != null;
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException(" connection close failed");
            }

        }
        return studentEntity;
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
        } catch (RuntimeException|SQLException e) {
            throw new RuntimeException("Read failed");
        } finally {
            try{
                assert statement != null;
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException("connection close failed");
            }
        }
        return result;
    }


    public StudentEntity update(StudentEntity studentEntity){
    Connection connection = null;
    PreparedStatement statement = null;
    String sql = "update students set first_name=?,last_name=?,group_id=? where student_id=?;";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,studentEntity.getFirst_name());
            statement.setString(2,studentEntity.getLast_name());
            statement.setInt(3,studentEntity.getGroup_id());
            statement.setInt(4,studentEntity.getStudent_id());
            statement.executeUpdate();

        } catch (RuntimeException|SQLException e) {
            throw new RuntimeException("Update failed");
        } finally {
            try{
                assert statement != null;
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException("connection close failed");
            }
        }
        return studentEntity;
    }


    public void deleteById (int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from students where student_id=?;";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (RuntimeException|SQLException e) {
            throw new RuntimeException("Delete failed");
        } finally {
            try{
                assert statement != null;
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException("connection close failed");
            }

        }
    }

    public void delete(StudentEntity studentEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from students where student_id=?;";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,studentEntity.getStudent_id());
            statement.executeUpdate();
        } catch (RuntimeException|SQLException e) {
            throw new RuntimeException("Delete failed");
        } finally {
            try{
                assert statement != null;
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException("connection close failed");
            }

        }
    }

}
