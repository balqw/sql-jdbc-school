package ua.com.foxminded.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.StudentEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    private static final String STUDENT_FIRST_NAME = "STUDENT_FIRST_NAME";
    private static final String STUDENT_LAST_NAME = "STUDENT_LAST_NAME";
    private StudentService studentService;
    private StudentsDao studentsDao;

    @BeforeEach
    public void beforeEach() {
        studentsDao = Mockito.mock(StudentsDao.class);
        studentService = new StudentService(studentsDao);
    }

    @Test
    public void methodCreateShouldReturnStudent() {
        StudentEntity preparedStudent = new StudentEntity(0, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME);
        when(studentsDao.create(any(StudentEntity.class))).thenReturn(new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME));

        StudentEntity result = studentService.create(preparedStudent);

        assertNotNull(result);
        assertEquals(1, result.getStudent_id());
        assertNotEquals(0, result.getStudent_id());
        assertEquals(0, result.getGroup_id());
        assertEquals(STUDENT_FIRST_NAME, result.getFirst_name());
        assertEquals(STUDENT_FIRST_NAME, result.getFirst_name());
        verify(studentsDao, atLeastOnce()).create(preparedStudent);
    }

    @Test
    public void methodFindBuIdShouldReturnStudentWithSpecifiedId() {
        StudentEntity preparedStudent = new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME);
        when(studentsDao.findById(1)).thenReturn(preparedStudent);

        StudentEntity result = studentService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getStudent_id());
        assertNotEquals(0, result.getStudent_id());
        assertEquals(0,result.getGroup_id());
        assertNotEquals(1,result.getGroup_id());
        assertEquals(STUDENT_FIRST_NAME,result.getFirst_name());
        assertEquals(STUDENT_LAST_NAME,result.getLast_name());
        verify(studentsDao,atLeastOnce()).findById(1);

    }

    @Test
    public void methodReadAllShouldReturnListWithStudent() {
        List<StudentEntity>preparedStudentList = new LinkedList<>(Arrays.asList(new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME)));
        when(studentService.readAll()).thenReturn(new LinkedList<>(Arrays.asList(new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME))));

        List<StudentEntity>result = studentService.readAll();

        assertNotNull(result);
        assert(!result.isEmpty());
        verify(studentsDao,atLeastOnce()).readAll();
    }

    @Test
    public void methodUpdateShouldReturnStudentWithNewProperties() {
        StudentEntity preparedStudent = new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME);
        when(studentService.update(any(StudentEntity.class))).thenReturn(preparedStudent);

        StudentEntity result = studentService.update(preparedStudent);

        assertNotNull(result);
        assertEquals(STUDENT_FIRST_NAME,result.getFirst_name());
        assertEquals(STUDENT_LAST_NAME,result.getLast_name());
        assertNotEquals(0,result.getStudent_id());
        verify(studentsDao,atLeastOnce()).update(preparedStudent);
    }

    @Test
    public void methodDeleteShouldReturnNothing() {
        studentService.delete(anyInt());
        verify(studentsDao,atLeastOnce()).delete(anyInt());
    }

    @Test
    public void methodAddCourseShouldReturnNothing() {
     studentService.addCourse(anyInt(),anyInt());
     verify(studentsDao,atLeastOnce()).additionCourseToStudent(anyInt(),anyInt());
    }


    @Test
    public void methodSearchByCourseShouldReturnListStudentsWithSpecifiedCourse() {
        List<StudentEntity>exceptedStudentList = new ArrayList<>(Collections.singletonList(new StudentEntity(1, 1, STUDENT_FIRST_NAME, STUDENT_LAST_NAME)));
        when(studentService.searchByCourse("math")).thenReturn(exceptedStudentList);

        List<StudentEntity>resultStudentsList = studentService.searchByCourse("math");

        assertNotNull(resultStudentsList);
        assertEquals(exceptedStudentList.get(0),resultStudentsList.get(0));
        verify(studentsDao,atLeastOnce()).searchStudentByCourse(anyString());
    }


    @Test
    public void methodDeleteCourseFromStudentShouldReturnNothing() {
        studentService.deleteCourseFromStudent(anyInt(),anyInt());

        verify(studentsDao,atLeastOnce()).deleteCourseFromStudent(anyInt(),anyInt());
    }
}