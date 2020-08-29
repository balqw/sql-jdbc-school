package ua.com.foxminded.domain.dao;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.entity.CourseEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CoursesDao {
    private final DBConnection dbConnection;
    private static final String ADD_QUERY = "insert into courses (course_name, course_description) values (?,?);";
    private static final String SELECT_QUERY = "select * from courses;";
    private static final String UPDATE_QUERY = "update from courses set course_name = ?, course_description = ? where group_id = ?";
    private static final String DELETE_QUERY= "delete from courses where course_id = ?";

    public CoursesDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public CourseEntity insert(CourseEntity courseEntity){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,courseEntity.getName());
            statement.setString(2,courseEntity.getDescription());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            courseEntity.setId(rs.getInt("course_id"));

        } catch (SQLException e) {
            throw new RuntimeException("Insert course failed");
        }
        return courseEntity;
    }

    public List<CourseEntity> read (){
        List<CourseEntity>result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(SELECT_QUERY);
            rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("course_id");
                String name = rs.getString("course_name");
                String desc = rs.getString("course_description");
                result.add(new CourseEntity(id,name,desc));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Select course failed");
        }
        return result;
    }

    public CourseEntity update (CourseEntity courseEntity){
        PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1,courseEntity.getName());
            statement.setString(2,courseEntity.getDescription());
            statement.setInt(3,courseEntity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update course failed");
        }
        return courseEntity;
    }

    public void deleteById(int id){
        PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DeleteById course failed");
        }
    }

    public void delete(CourseEntity courseEntity){
        PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,courseEntity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Delete course failed");
        }
    }

}
