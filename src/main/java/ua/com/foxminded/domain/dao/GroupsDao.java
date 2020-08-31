package ua.com.foxminded.domain.dao;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.entity.GroupEntity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupsDao {
    private static final String ADD_QUERY = "insert into groups(name) values (?);";
    private static final String UPDATE_QUERY = "update groups set group_id=? where group_id=?;";
    private static final String FIND_QUERY = "select * from groups WHERE group_id=?;";
    private static final String DELETE_QUERY = "delete from groups where group_id=?;";
    private static final String SELECT_QUERY = "select * from groups;";

    private final DBConnection connection;

    public GroupsDao(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    public GroupEntity create (GroupEntity groupEntity) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, groupEntity.getName());
            statement.execute();
            rs = statement.getGeneratedKeys();
            rs.next();
            groupEntity.setGroup_id(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Create group failed");
        }
        return groupEntity;
    }


    public GroupEntity findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try (Connection connection = this.connection.getConnection();) {
            statement = connection.prepareStatement(FIND_QUERY);
            rs = statement.executeQuery();
            int group_id = rs.getInt(1);
            String name = rs.getString(2);
            return new GroupEntity(group_id,name);
        } catch (SQLException e) {
            throw new RuntimeException("Find group failed");
        }
    }


    public List<GroupEntity> readAll(){
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<GroupEntity>groupEntities = new LinkedList<>();
        try (Connection connection = this.connection.getConnection()){
            statement = connection.prepareStatement(SELECT_QUERY);
            rs = statement.executeQuery();
            while(rs.next()){
                int group_id = rs.getInt(1);
                String name = rs.getString(2);
                groupEntities.add(new GroupEntity(group_id,name));
            }

        } catch (Exception e) {
            throw new RuntimeException("Read all groups failed");
        }
        return groupEntities;
    }


    public GroupEntity update(GroupEntity groupEntity) {
        PreparedStatement statement = null;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, groupEntity.getName());
            statement.setInt(2, groupEntity.getGroup_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update group failed");
        }
        return groupEntity;
    }


    public void deleteByID(Integer id) {
        PreparedStatement statement = null;
        try (Connection connection = this.connection.getConnection()) {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DeleteById group failed");
        }
    }


}
