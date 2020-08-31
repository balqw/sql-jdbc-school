package ua.com.foxminded.domain.dao;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.entity.CourseEntity;
import ua.com.foxminded.service.CourseService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CoursesDao {
    private final DBConnection connection;



    private static final String ADD_QUERY = "insert into groups(course_name, course_description ) values (?,?);";
    private static final String UPDATE_QUERY = "update courses set course_name=?, course_description = ? where course_id=?;";
    private static final String FIND_QUERY = "select * from courses WHERE course_id=?;";
    private static final String DELETE_QUERY = "delete from courses where course_id=?;";
    private static final String SELECT_QUERY = "select * from courses;";
    public CoursesDao(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    public CourseEntity create (CourseEntity courseEntity){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,courseEntity.getName());
            statement.setString(2,courseEntity.getDescription());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            courseEntity.setId(rs.getInt(1));
            return courseEntity;
        } catch (SQLException e) {
            throw new RuntimeException("Insert course failed");
        }
    }


    public CourseEntity findById(Integer id){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection connection = this.connection.getConnection()){
            statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();
            int course_id = rs.getInt(1);
            String course_name = rs.getString(2);
            String description = rs.getString(3);
            return new CourseEntity(course_id,course_name,description);
        }catch (SQLException e){
            throw new RuntimeException("FindById course failed");
        }
    }


    public List<CourseEntity> readAll(){
        List<CourseEntity>result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection connection = this.connection.getConnection()) {
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
        try(Connection connection = this.connection.getConnection()) {
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
        try(Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DeleteById course failed");
        }
    }

}
