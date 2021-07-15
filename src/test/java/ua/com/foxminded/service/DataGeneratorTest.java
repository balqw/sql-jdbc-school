package ua.com.foxminded.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.config.Context;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.dao.CoursesDao;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.dao.StudentsDao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataGeneratorTest {

    private static InitialScriptRunner scriptRunner;
    private static final Context context = Context.connectorTypeBuilder(Context.H2);
    private static final CourseService courseService = context.getCourseService();
    private static final GroupService groupService = context.getGroupService();
    private static final StudentService studentService = context.getStudentService();
    private static final DataGenerator dataGenerator = context.getDataGenerator();

    @BeforeEach
    void beforeEach() {
        scriptRunner = context.getInitialScriptRunner();
        scriptRunner.creat("src/test/resources/init_table.sql");
    }

    @Test
    void methodGenerateGroupsShouldCreateTenGroups() {
        dataGenerator.generateGroups();
        int actualFields = groupService.readAll().size();
        int exceptedFields = 10;
        assertEquals(exceptedFields,actualFields);
    }

    @Test
    void methodGenerateCoursesShouldCreateTenCourses() {
        dataGenerator.generateCourses();
        int actualFields = courseService.readAll().size();
        int exceptedFields = 10;
        assertEquals(exceptedFields,actualFields);
    }

    @Test
    void methodGenerateStudentsShouldCreateTwoHundredStudents() {
        dataGenerator.generateGroups();
        dataGenerator.generateStudents();
        int actualFields = studentService.readAll().size();
        int exceptedFields = 200;

        assertEquals(exceptedFields,actualFields);
    }
}