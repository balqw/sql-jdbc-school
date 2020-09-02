package ua.com.foxminded.domain.dao;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.CrudOperations;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.*;
import java.util.*;

public class StudentsDao implements CrudOperations<StudentEntity, Integer> {
    private static final String UPDATE_QUERY = "update students set first_name=?,last_name=?,group_id=? where student_id=?;";
    private static final String ADD_QUERY = "insert into students(first_name,last_name,group_id) values (?,?,?);";
    private static final String FIND_QUERY = "select * from students WHERE students_id=?;";
    private static final String DELETE_QUERY = "delete from students where student_id=?;";
    private static final String SELECT_QUERY = "select * from students;";
    private static final String ADD_COURSE = "insert into student_course (student_id, course_id) values(?,?);";
    private static final String FIND_BY_COURSE = "select first_name, last_name from students "
    +"inner join student_course "
    +"on students.student_id = student_course.student_id "
    +"inner join courses "
    +"on courses.course_id = student_course.course_id "
    +"where courses.course_name = ?;";




    private final DBConnection connection;

    public StudentsDao(DBConnection connection) {
        this.connection = connection;
    }

    public StudentEntity create(StudentEntity studentEntity) {
        PreparedStatement statement = null;
        ResultSet rs;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, studentEntity.getFirst_name());
            statement.setString(2, studentEntity.getLast_name());
            statement.setInt(3, studentEntity.getGroup_id());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            studentEntity.setStudent_id(rs.getInt("student_id"));
        } catch (SQLException e) {
            throw new RuntimeException("Create student failed: " + e.getLocalizedMessage());
        }
        return studentEntity;
    }

    @Override
    public StudentEntity findBuId(Integer id) {
        PreparedStatement statement = null;
        ResultSet rs;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            rs.next();

            int groupId = rs.getInt(2);
            String firstName = rs.getString(3);
            String lastName = rs.getString(4);
            return new StudentEntity(id, groupId, firstName, lastName);
        } catch (SQLException e) {
            throw new RuntimeException("Select student failed");
        }
    }


    public List<StudentEntity> readAll() {
        List<StudentEntity> result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet rs;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(SELECT_QUERY);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int groupId = rs.getInt(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                StudentEntity studentEntity = new StudentEntity(id, groupId, firstName, lastName);
                result.add(studentEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Read all students failed");
        }
        return result;
    }


    public StudentEntity update(StudentEntity studentEntity) {
        PreparedStatement statement = null;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, studentEntity.getFirst_name());
            statement.setString(2, studentEntity.getLast_name());
            statement.setInt(3, studentEntity.getGroup_id());
            statement.setInt(4, studentEntity.getStudent_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update student failed");
        }
        return studentEntity;
    }


    public void delete(Integer id) {
        PreparedStatement statement = null;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete student failed");
        }
    }


    public void additionCourse(int idStudent, int idCourse ){
        PreparedStatement statement = null;
        try(Connection connection = this.connection.getConnection()){
            statement = connection.prepareStatement(ADD_COURSE);
            statement.setInt(1,idStudent);
            statement.setInt(2,idCourse);
            statement.execute();

        }catch (SQLException e){
            throw new RuntimeException("additionCourse failed "+e.getLocalizedMessage());
        }

    }


    public List<StudentEntity> searchStudentByCourse(String course){
        List<StudentEntity>resultStudents = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs ;

        try(Connection connection = this.connection.getConnection()){
            statement = connection.prepareStatement(FIND_BY_COURSE);
            statement.setString(1,course);
            rs = statement.executeQuery();
            while(rs.next()){
               String firstName = rs.getString(1);
               String lastName = rs.getString(2);
                resultStudents.add(new StudentEntity(firstName,lastName));
            }
        }catch (SQLException e){
            throw new RuntimeException("searchStudentByCourse failed "+e.getLocalizedMessage());
        }
        return resultStudents;
    }

}
