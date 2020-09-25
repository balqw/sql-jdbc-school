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
    public GroupEntity findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<GroupEntity> readAll() {
        return service.readAll();
    }

    @Override
    public GroupEntity update(GroupEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
            service.deleteByID(id);
    }

    public List<GroupEntity> findGroupEqualsStudentCount(int count){return service.findGroupEqualsStudentCount(count);}


}
