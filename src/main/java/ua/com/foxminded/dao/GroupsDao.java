package ua.com.foxminded.dao;

import ua.com.foxminded.dao.config.DBConnection;
import ua.com.foxminded.domain.entity.GroupEntity;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
public class GroupsDao {
    DBConnection dbConnection = new DBConnection();
    private static final String ADD_QUERY = "insert into groups(name) values (?);";
    private static final String SELECT_QUERY = "select * from groups;";
    private static final String UPDATE_QUERY = "update from groups set name = ? where group_id = ?";
    private static final String DELETE_QUERY= "delete from groups where group_id = ?;";

    public GroupEntity insert(GroupEntity groupEntity){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(ADD_QUERY,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,groupEntity.getName());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            groupEntity.setGroup_id(rs.getInt("group_id"));
        }catch (SQLException e) {
            throw new RuntimeException("Insert group failed");
        }
        return groupEntity;
    }


    public List<GroupEntity> read(){
        List<GroupEntity>result = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try (Connection connection = dbConnection.getConnection();){
            statement = connection.prepareStatement(SELECT_QUERY);
            rs = statement.executeQuery();
            while(rs.next()){
                int id = rs.getInt("group_id");
                String name = rs.getString("name");
                result.add(new GroupEntity(id,name));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Select group failed");
        }
        return  result;
    }


    public GroupEntity update (GroupEntity groupEntity){
        PreparedStatement statement = null;
        try (Connection  connection = dbConnection.getConnection()){
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1,groupEntity.getName());
            statement.setInt(2,groupEntity.getGroup_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Update group failed");
        }
        return groupEntity;
    }

    public void deleteByID(int id){
        PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DeleteById group failed");
        }
    }


    public void delete(GroupEntity groupEntity){
        PreparedStatement statement = null;
        try(Connection connection = dbConnection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1,groupEntity.getGroup_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Delete group failed");
        }
    }


}
