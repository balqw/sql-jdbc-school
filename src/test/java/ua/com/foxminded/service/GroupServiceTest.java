package ua.com.foxminded.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.entity.GroupEntity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    private static final String GROUP_NAME = "GROUP_NAME";

    private GroupService groupService;
    private GroupsDao groupsDao;

    @BeforeEach
    public void beforeEach(){
        groupsDao = Mockito.mock(GroupsDao.class);
        groupService = new GroupService(groupsDao);
    }


    @Test
    void create() {
        GroupEntity preparedGroup = new GroupEntity(0,GROUP_NAME);
        when(groupsDao.create(any(GroupEntity.class))).thenReturn(new GroupEntity(1,GROUP_NAME));

        GroupEntity result = groupService.create(preparedGroup);

        assertNotNull(result);
        assertEquals(1,result.getGroup_id());
        assertEquals(GROUP_NAME, result.getName());
        assertNotEquals(0, result.getGroup_id());
        verify(groupsDao,atLeastOnce()).create(preparedGroup);

    }

    @Test
    void findBuId() {
        GroupEntity preparedGroup = new GroupEntity(1,GROUP_NAME);
        when(groupsDao.findById(anyInt())).thenReturn(preparedGroup);

        GroupEntity result = groupService.findBuId(1);

        assertNotNull(result);
        assertEquals(1,result.getGroup_id());
        assertEquals(GROUP_NAME,result.getName());
        verify(groupsDao,atLeastOnce()).findById(1);
    }

    @Test
    void readAll() {
        List<GroupEntity>preparedList = new LinkedList<>(Arrays.asList(new GroupEntity(1,GROUP_NAME),new GroupEntity(2,GROUP_NAME)));
        when(groupsDao.readAll()).thenReturn(preparedList);

        List<GroupEntity>result = groupService.readAll();

        assertNotNull(result);
        assertEquals(1,result.get(0).getGroup_id());
        assertEquals(2,result.get(1).getGroup_id());
        assertNotEquals(1,result.get(1).getGroup_id());
        assertEquals(GROUP_NAME,result.get(0).getName());
        verify(groupsDao,atLeastOnce()).readAll();

    }

    @Test
    void update() {
        GroupEntity preparedGroup = new GroupEntity(1, GROUP_NAME);
        when(groupsDao.update(any(GroupEntity.class))).thenReturn(preparedGroup);

        GroupEntity result = groupService.update(preparedGroup);

        assertNotNull(result);
        assertEquals(1,result.getGroup_id());
        assertNotEquals(0,result.getGroup_id());
        assertEquals(GROUP_NAME,result.getName());
        verify(groupsDao, atLeastOnce()).update(any(GroupEntity.class));
    }

    @Test
    void delete() {
    }

    @Test
    void findGroupEqualsStudentCount() {
    }
}