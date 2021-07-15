package ua.com.foxminded.domain.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.config.Context;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.entity.CourseEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.service.DataGenerator;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static ua.com.foxminded.config.Context.H2;

class StudentsDaoTest {
   private static final String FIRST_NAME = "FIRST_NAME";
   private static final String LAST_NAME = "LAST_NAME";
   private static final Context context = Context.connectorTypeBuilder(H2);
   private static final DBConnection connection = context.getDbConnection();
   private static StudentsDao testingStudentDao;


    @BeforeEach
     void beforeEach(){
        InitialScriptRunner scriptRunner = context.getInitialScriptRunner();
        testingStudentDao = context.getStudentsDao();
        scriptRunner.creat("src/test/resources/init_table.sql");
        DataGenerator dataGenerator = Context.connectorTypeBuilder(H2).getDataGenerator();
        dataGenerator.generateGroups();
    }


    @Test
    void methodCreateShouldAddNewStudent() {
      StudentEntity exceptedResult = new StudentEntity(1,1,FIRST_NAME, LAST_NAME);
      int fieldsBefore = testingStudentDao.readAll().size();

      StudentEntity actualResult  = testingStudentDao.create(new StudentEntity(1,FIRST_NAME, LAST_NAME));
      int fieldsAfter = testingStudentDao.readAll().size();

      assertNotNull(actualResult);
      assertEquals(fieldsBefore+1,fieldsAfter);
      assertEquals(exceptedResult,actualResult);
    }


    @Test
    void methodFindByIdShouldFindStudentWithSpecifiedId() {
        StudentEntity exceptedStudent = new StudentEntity(1,1,FIRST_NAME, LAST_NAME);
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));

        StudentEntity actualStudent = testingStudentDao.findById(1);

        assertNotNull(actualStudent);
        assertEquals(exceptedStudent.getStudent_id(),actualStudent.getStudent_id());
        assertEquals(exceptedStudent.getGroup_id(),actualStudent.getStudent_id());
        assertEquals(exceptedStudent,actualStudent);
    }

    @Test
    void methodReadAllShouldReturnAllStudents() {
        List<StudentEntity>exceptedStudentsList = new LinkedList<>(Arrays.asList(
                new StudentEntity(1,1,FIRST_NAME,LAST_NAME),
                new StudentEntity(2,1,FIRST_NAME,LAST_NAME)
        ));
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));

        List<StudentEntity>actualStudentsList = testingStudentDao.readAll();

        assertNotNull(actualStudentsList);
        assertEquals(exceptedStudentsList.size(),actualStudentsList.size());
        assertEquals(exceptedStudentsList.get(0),actualStudentsList.get(0));
    }


    @Test
    void methodUpdateShouldChangeSpecifiedProperties() {
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));
        StudentEntity exceptedStudent = new StudentEntity(1,2,"NEW_FIRST_NAME","NEW_LAST_NAME");

        StudentEntity actualStudent = testingStudentDao.update(new StudentEntity(1,2,"NEW_FIRST_NAME","NEW_LAST_NAME"));

        assertEquals(exceptedStudent,actualStudent);
    }


    @Test
    void methodDeleteShouldDeleteStudentWithSpecifiedId() {
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));
        int fieldsBefore = testingStudentDao.readAll().size();

        testingStudentDao.delete(1);
        int fieldsAfter = testingStudentDao.readAll().size();

        assertEquals(fieldsBefore-1,fieldsAfter);
    }


    @Test
    void searchStudentByCourseShouldReturnStudentWithSelectedCourse() {
        CoursesDao coursesDao = new CoursesDao(connection);
        coursesDao.create(new CourseEntity("math","math"));
        coursesDao.create(new CourseEntity("music","music"));
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));
        testingStudentDao.create(new StudentEntity(1,FIRST_NAME,LAST_NAME));
        testingStudentDao.additionCourseToStudent(2,1);
        StudentEntity exceptedStudent = new StudentEntity(2,1,FIRST_NAME,LAST_NAME);
        List<StudentEntity>actualStudentsList = testingStudentDao.searchStudentByCourse("math");

        assertNotNull(actualStudentsList);
        assertEquals(exceptedStudent,actualStudentsList.get(0));
    }

}