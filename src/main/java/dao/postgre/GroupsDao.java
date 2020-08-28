package dao.postgre;

import dao.DBConnection;
import domain.entity.CourseEntity;
import domain.entity.GroupEntity;

import java.security.acl.Group;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupsDao {
    DBConnection dbConnection = new DBConnection();

    public GroupEntity insert(GroupEntity groupEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "insert into groups(name) values (?);";
        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,groupEntity.getName());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            groupEntity.setGroup_id(rs.getInt("group_id"));
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
        return groupEntity;
    }


    public List<GroupEntity> read(){
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
            return  result;
    }


    public GroupEntity update (GroupEntity groupEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update from groups set name = ? where group_id = ?";

        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,groupEntity.getName());
            statement.setInt(2,groupEntity.getGroup_id());
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
        return groupEntity;
    }

    public void deleteByID(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from groups where group_id = ?;";
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


    public void delete(GroupEntity groupEntity){
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from groups where group_id = ?;";
        try {
            connection = dbConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,groupEntity.getGroup_id());
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
