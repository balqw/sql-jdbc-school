package ua.com.foxminded.service;

import ua.com.foxminded.domain.CrudOperations;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.entity.GroupEntity;

import java.util.List;

public class GroupService implements CrudOperations<GroupEntity,Integer> {
    private final GroupsDao service;


    public GroupService(GroupsDao service) {
        this.service = service;
    }

    @Override
    public GroupEntity create(GroupEntity entity) {
       return service.create(entity);
    }

    @Override
    public GroupEntity findBuId(Integer entity) {
        return null;
    }

    @Override
    public List<GroupEntity> readAll() {
        return null;
    }

    @Override
    public GroupEntity update(GroupEntity entity) {
        return null;
    }

    @Override
    public void delete(Integer entity) {

    }
}
