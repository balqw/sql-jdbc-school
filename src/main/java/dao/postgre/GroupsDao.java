package dao.postgre;

import dao.DBConnection;
import domain.entity.GroupEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GroupsDao {
    DBConnection dbConnection = new DBConnection();

    public void insert(String name) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "insert into groups(name) values (?);";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }

    }


    public List<GroupEntity> read() throws SQLException {
        List<GroupEntity>result = new LinkedList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from groups;";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("group_id");
                String name = rs.getString("name");
                result.add(new GroupEntity(id,name));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }
            return  result;
    }


    public void update(int id, String name) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update from groups set name = ? where group_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setInt(2,id);
            statement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert statement != null;
            statement.close();
            connection.close();
        }
    }


    public void delete(int id) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from groups where group_id = ?;";

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
