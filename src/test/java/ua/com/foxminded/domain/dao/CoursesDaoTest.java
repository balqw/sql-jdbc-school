package ua.com.foxminded.domain.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.config.Context;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.entity.CourseEntity;
import ua.com.foxminded.service.CourseService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoursesDaoTest {

    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final Context context = Context.connectorTypeBuilder(Context.H2);
    private static CoursesDao testingCourseDao;
    private static  InitialScriptRunner scriptRunner;


    @BeforeEach
    void beforeEach() {
        testingCourseDao = context.getCoursesDao();
        scriptRunner = context.getInitialScriptRunner();
        scriptRunner.creat("src/test/resources/init_table.sql");
    }

    @Test
    void methodCreateShouldAddNewCourse() {
        CourseEntity exceptedCourse = new CourseEntity(1,NAME,DESCRIPTION);
        int fieldsBefore = testingCourseDao.readAll().size();
        CourseEntity actualCourse = testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        int fieldsAfter = testingCourseDao.readAll().size();

        assertNotNull(actualCourse);
        assertEquals(fieldsBefore+1,fieldsAfter);
        assertEquals(exceptedCourse,actualCourse);
    }

    @Test
    void methodFindByIdShouldFindCourseWithSpecifiedId() {
        CourseEntity exceptedCourse = new CourseEntity(2,NAME,DESCRIPTION);

        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        CourseEntity actualCourse = testingCourseDao.findById(2);

        assertNotNull(actualCourse);
        assertEquals(exceptedCourse,actualCourse);
    }

    @Test
    void methodReadAllShouldReturnAllCourse() {
        List<CourseEntity>exceptedCoursesList = new LinkedList<>(Arrays.asList(
                new CourseEntity(1,NAME,DESCRIPTION),
                new CourseEntity(2,NAME,DESCRIPTION)
        ));
        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));

        List<CourseEntity>actualCoursesList = testingCourseDao.readAll();

        assertNotNull(actualCoursesList);
        assertEquals(exceptedCoursesList.size(),actualCoursesList.size());
        assertEquals(exceptedCoursesList.get(0),actualCoursesList.get(0));
    }

    @Test
    void methodUpdateShouldChangeSpecifiedProperties() {
        CourseEntity exceptedCourse = new CourseEntity(1,"NEW_NAME","NEW_DESCRIPTION");
        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        CourseEntity actualCourse = testingCourseDao.update(exceptedCourse);

        assertNotNull(actualCourse);
        assertEquals(exceptedCourse,actualCourse);
    }

    @Test
    void methodDeleteShouldDeleteCourseWithSpecifiedId() {
        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        testingCourseDao.create(new CourseEntity(NAME,DESCRIPTION));
        int fieldsBefore = testingCourseDao.readAll().size();
        testingCourseDao.deleteById(1);
        int fieldAfter = testingCourseDao.readAll().size();
        CourseEntity courseNotDelete = testingCourseDao.findById(2);

       assertEquals(fieldsBefore,fieldAfter+1);
       assertEquals(2,courseNotDelete.getId());

    }

}