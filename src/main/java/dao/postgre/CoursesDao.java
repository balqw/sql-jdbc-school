package dao.postgre;

import dao.DBConnection;
import domain.entity.CourseEntity;
import domain.entity.GroupEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CoursesDao {
    private DBConnection dbConnection = new DBConnection();

    public CourseEntity insert(CourseEntity courseEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "insert into courses (course_name, course_description) values (?,?);";
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,courseEntity.getName());
            statement.setString(2,courseEntity.getDescription());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            courseEntity.setId(rs.getInt("course_id"));

        } catch (RuntimeException|SQLException e) {
            throw new RuntimeException("Insert failed");
        } finally {
            try{
                assert statement != null;
                statement.close();
                connection.close();
            }catch (SQLException e){
                throw new RuntimeException("connection close failed");
            }
        }
        return courseEntity;
    }

    public List<CourseEntity> read (){
        List<CourseEntity>result = new LinkedList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from courses;";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("course_id");
                String name = rs.getString("course_name");
                String desc = rs.getString("course_description");
                result.add(new CourseEntity(id,name,desc));
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

    public CourseEntity update (CourseEntity courseEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update from courses set course_name = ?, course_description = ? where group_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,courseEntity.getName());
            statement.setString(2,courseEntity.getDescription());
            statement.setInt(3,courseEntity.getId());
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
        return courseEntity;
    }

    public void deleteById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from courses where course_id = ?";

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

    public void delete(CourseEntity courseEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from courses where course_id = ?";
        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,courseEntity.getId());
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
