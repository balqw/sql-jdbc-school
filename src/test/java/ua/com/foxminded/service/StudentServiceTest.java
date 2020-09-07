package ua.com.foxminded.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    public void create() {
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
    public void findBuId() {
        StudentEntity preparedStudent = new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME);
        when(studentsDao.findBuId(1)).thenReturn(preparedStudent);

        StudentEntity result = studentService.findBuId(1);

        assertNotNull(result);
        assertEquals(1, result.getStudent_id());
        assertNotEquals(0, result.getStudent_id());
        assertEquals(0,result.getGroup_id());
        assertNotEquals(1,result.getGroup_id());
        assertEquals(STUDENT_FIRST_NAME,result.getFirst_name());
        assertEquals(STUDENT_LAST_NAME,result.getLast_name());
        verify(studentsDao,atLeastOnce()).findBuId(1);

    }

    @Test
    public void readAll() {
        List<StudentEntity>preparedStudentList = new LinkedList<>(Arrays.asList(new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME)));
        when(studentService.readAll()).thenReturn(new LinkedList<>(Arrays.asList(new StudentEntity(1, 0, STUDENT_FIRST_NAME, STUDENT_LAST_NAME))));

        List<StudentEntity>result = studentService.readAll();

        assertNotNull(result);
        assert(!result.isEmpty());
        verify(studentsDao,atLeastOnce()).readAll();
    }

    @Test
    public void update() {
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
    public void delete() {


       //verify(studentsDao,atLeastOnce()).delete(1);
    }

    @Test
    public void addCourse() {
      //???
    }

    @Test
    public void searchByCourse() {
        List<StudentEntity>preparedList = new ArrayList<>();
        when(studentsDao.searchStudentByCourse(anyString())).thenReturn(preparedList);

        List<StudentEntity>result = studentService.searchByCourse(anyString());
        assertNotNull(result);

        verify(studentsDao,atLeastOnce()).searchStudentByCourse(anyString());

    }
}