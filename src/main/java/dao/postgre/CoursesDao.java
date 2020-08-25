package dao.postgre;

import dao.DBConnection;
import domain.entity.CourseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class CoursesDao {
    private DBConnection dbConnection = new DBConnection();
    public void insert(String name, String description) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "insert into courses (course_name, course_description) values (?,?);";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.executeQuery();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }
    }

    public List<CourseEntity> read () throws SQLException {
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
                String name = rs.getString("course_name");
                String desc = rs.getString("course_description");
                result.add(new CourseEntity(name,desc));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();

        }
        return result;
    }


    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from courses where course_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }

    }


}
