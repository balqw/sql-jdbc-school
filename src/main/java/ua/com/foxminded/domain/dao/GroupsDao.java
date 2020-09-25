package ua.com.foxminded.domain.dao;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GroupsDao {
    private static final String ADD_QUERY = "insert into groups(name) values (?);";
    private static final String UPDATE_QUERY = "update groups set name=? where group_id=?;";
    private static final String FIND_QUERY = "select * from groups WHERE group_id=?;";
    private static final String DELETE_QUERY = "delete from groups where group_id=?;";
    private static final String SELECT_QUERY = "select * from groups;";
    private static final String SELECT_BY_COUNT_GROUP = "select g.group_id, g.name , count(s.group_id) " +
            "from groups as g " +
            "inner join students as s " +
            "on g.group_id = s.group_id " +
            "group by g.group_id " +
            "having count(*)<=?;";

    private final DBConnection connection;

    public GroupsDao(DBConnection dbConnection) {
        this.connection = dbConnection;
    }

    public GroupEntity create (GroupEntity groupEntity) {
        try (Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, groupEntity.getName());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            groupEntity.setGroup_id(rs.getInt("group_id"));
        } catch (SQLException e) {
            throw new RuntimeException("Create group failed: " + e.getLocalizedMessage());
        }
        return groupEntity;
    }


    public GroupEntity findById(Integer id) {
        try (Connection connection = this.connection.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            String name = rs.getString("name");
            return new GroupEntity(id,name);
        } catch (SQLException e) {
            throw new RuntimeException("Find group failed");
        }
    }


    public List<GroupEntity> readAll(){
        List<GroupEntity>groupEntities = new LinkedList<>();
        try (Connection connection = this.connection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            ResultSet rs = statement.executeQuery();
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
        try (Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1,groupEntity.getName());
            statement.setInt(2, groupEntity.getGroup_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Update group failed");
        }
        return groupEntity;
    }


    public void deleteByID(Integer id) {
        try (Connection connection = this.connection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DeleteById group failed");
        }
    }


    public List<GroupEntity> findGroupEqualsStudentCount(int count){
        List<GroupEntity>resultGroups = new ArrayList<>();
        try(Connection connection = this.connection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_COUNT_GROUP);
            statement.setInt(1,count);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int groupId = rs.getInt(1);
                String name = rs.getString(2);
                resultGroups.add(new GroupEntity(groupId,name));
            }
        }catch (SQLException e){
            throw new RuntimeException("getSelectByCountGroup failed "+ e.getLocalizedMessage());
        }
        return resultGroups;
    }


}
