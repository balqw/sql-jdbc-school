package ua.com.foxminded.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.config.Context;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static ua.com.foxminded.config.Context.H2;

class GroupsDaoTest {
    private static final String GROUP_NAME = "GROUP_NAME";
    private static final Context context = Context.connectorTypeBuilder(H2);
    private static final DBConnection connection = context.getDbConnection();
    private static GroupsDao testingGroupDao;


    @BeforeEach
    void beforeEach() {
        InitialScriptRunner scriptRunner = context.getInitialScriptRunner();
        testingGroupDao = context.getGroupsDao();
        scriptRunner.creat("src/test/resources/init_table.sql");
    }

    @Test
    void methodCreateShouldAddNewGroup() {
        GroupEntity exceptedGroup = new GroupEntity(1,GROUP_NAME);
        int fieldsBefore = testingGroupDao.readAll().size();
        GroupEntity actualGroup = testingGroupDao.create(new GroupEntity(GROUP_NAME));
        int fieldsAfter = testingGroupDao.readAll().size();

        assertNotNull(actualGroup);
        assertEquals(fieldsBefore+1,fieldsAfter);
        assertEquals(exceptedGroup,actualGroup);

    }

    @Test
    void methodFindByIdShouldFindGroupWithSpecifiedId() {
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        GroupEntity exceptedGroup = new GroupEntity(1,GROUP_NAME);

        GroupEntity actualGroup = testingGroupDao.findById(1);

        assertNotNull(actualGroup);
        assertEquals(exceptedGroup,actualGroup);
    }

    @Test
    void methodReadAllShouldReturnAllGroups() {
        List<GroupEntity>exceptedGroupsList = new LinkedList<>(Arrays.asList(
                new GroupEntity(1,GROUP_NAME),
                new GroupEntity(2,GROUP_NAME)
        ));
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        testingGroupDao.create(new GroupEntity(GROUP_NAME));

        List<GroupEntity>actualGroupsList = testingGroupDao.readAll();

        assertNotNull(actualGroupsList);
        assertEquals(exceptedGroupsList.size(),actualGroupsList.size());
        assertEquals(exceptedGroupsList.get(0),actualGroupsList.get(0));

    }

    @Test
    void methodUpdateShouldChangeSpecifiedProperties() {
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        GroupEntity exceptedGroup = new GroupEntity(1,"NEW_NAME");

        GroupEntity actualGroup = testingGroupDao.update(exceptedGroup);

        assertNotNull(actualGroup);
        assertEquals(exceptedGroup,actualGroup);

    }

    @Test
    void methodDeleteShouldDeleteGroupWithSpecifiedId() {
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        int fieldBefore = testingGroupDao.readAll().size();
        testingGroupDao.deleteByID(1);
        int fieldAfter = testingGroupDao.readAll().size();
        GroupEntity groupNotDelete = testingGroupDao.findById(2);

        assertEquals(fieldBefore-1,fieldAfter);
        assertEquals(2,groupNotDelete.getGroupId());
    }

    @Test
    void methodFindGroupEqualsStudentCountShouldReturnGroupsWithSpecifiedNumberStudents() {
        StudentsDao studentsDao = new StudentsDao(connection);
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        testingGroupDao.create(new GroupEntity(GROUP_NAME));
        studentsDao.create(new StudentEntity(1,"FIRST_NAME","LAST_NAME"));
        studentsDao.create(new StudentEntity(2,"FIRST_NAME","LAST_NAME"));
        studentsDao.create(new StudentEntity(2,"FIRST_NAME","LAST_NAME"));

        List<GroupEntity>exceptedGroupsList = new LinkedList<>(Collections.singletonList(new GroupEntity(1, GROUP_NAME)));

        List<GroupEntity>actualGroupsList = testingGroupDao.findGroupEqualsStudentCount(1);

        assertNotNull(actualGroupsList);
        assertEquals(exceptedGroupsList.get(0),actualGroupsList.get(0));
    }
}