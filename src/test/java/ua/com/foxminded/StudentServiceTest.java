package ua.com.foxminded;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.service.StudentService;

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
    }

    @Test
    public void readAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void addCourse() {
    }

    @Test
    public void searchByCourse() {
    }
}