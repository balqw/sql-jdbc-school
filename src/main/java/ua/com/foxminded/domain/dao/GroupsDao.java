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
    private static final String UPDATE_QUERY = "update groups set group_id=? where group_id=?;";
    private static final String FIND_QUERY = "select * from groups WHERE group_id=?;";
    private static final String DELETE_QUERY = "delete from groups where group_id=?;";
    private static final String SELECT_QUERY = "select * from groups;";
    private static final String SELECT_BY_COUNT_GROUP = "select g.name , count(s.group_id) " +
            "from groups as g " +
            "inner join students as s " +
            "on g.group_id = s.group_id " +
            "group by g.name " +
            "having count(*)<=?;";

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
            throw new RuntimeException("Create group failed: " + e.getLocalizedMessage());
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


    public List<GroupEntity> findGroupEqualsStudentCount(int count){
        List<GroupEntity>resultGroups = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;


        try(Connection connection = this.connection.getConnection()){
            statement = connection.prepareStatement(SELECT_BY_COUNT_GROUP);
            statement.setInt(1,count);
            rs = statement.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
                Integer sum = rs.getInt(2);
                resultGroups.add(new GroupEntity(name));
            }
        }catch (SQLException e){
            throw new RuntimeException("getSelectByCountGroup failed "+ e.getLocalizedMessage());
        }

        return resultGroups;
    }


}
