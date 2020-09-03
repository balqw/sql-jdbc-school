package ua.com.foxminded.domain.dao;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.entity.CourseEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.service.CourseService;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class CoursesDao {
    private final DBConnection connection;



    private static final String ADD_QUERY = "insert into courses(course_name, course_description ) values (?,?);";
    private static final String UPDATE_QUERY = "update courses set course_name=?, course_description = ? where course_id=?;";
    private static final String FIND_QUERY = "select * from courses WHERE course_id=?;";
    private static final String DELETE_QUERY = "delete from courses where course_id=?;";
    private static final String SELECT_QUERY = "select * from courses;";
    private static final String DELETE_COURSE_FROM_STUDENT = "delete from student_course\n" +
            "where student_id = ? and course_id = ?;";
    private static final String ADD_COURSE_TO_STUDENT = "insert into student_course (student_id,course_id) values(?,?);";
    public CoursesDao(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    public CourseEntity create (CourseEntity courseEntity){
        try(Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,courseEntity.getName());
            statement.setString(2,courseEntity.getDescription());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            courseEntity.setId(rs.getInt(1));
            return courseEntity;
        } catch (SQLException e) {
            throw new RuntimeException("Insert course failed");
        }
    }


    public CourseEntity findById(Integer id){
        try(Connection connection = this.connection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
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
        try(Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet rs = statement.executeQuery();
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
        try(Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
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
        try(Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DeleteById course failed");
        }
    }


    public void addCourseToStudent(int idStudent,int idCourse){
        try(Connection connection = this.connection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(ADD_COURSE_TO_STUDENT);
           statement.setInt(1,idStudent);
           statement.setInt(2,idCourse);
           statement.execute();
        }catch (SQLException e){
            throw new RuntimeException("addCourse failed "+ e.getLocalizedMessage());
        }
    }


    public void deleteCourseFromStudent(int idStudent,int idCourse){
        try(Connection connection = this.connection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(DELETE_COURSE_FROM_STUDENT);
            statement.setInt(1,idStudent);
            statement.setInt(2,idCourse);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("addCourse failed "+ e.getLocalizedMessage());
        }
    }
}
