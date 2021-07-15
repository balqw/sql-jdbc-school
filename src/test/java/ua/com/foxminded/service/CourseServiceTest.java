package ua.com.foxminded.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.domain.dao.CoursesDao;
import ua.com.foxminded.domain.entity.CourseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    private static final String COURSE_NAME = "COURSE_NAME";
    private static final String COURSE_DESCRIPTION = "COURSE_DESCRIPTION";
    private CoursesDao coursesDao;
    private CourseService courseService;

    @BeforeEach
    public void beforeEach(){
        coursesDao = Mockito.mock(CoursesDao.class);
        courseService = new CourseService(coursesDao);
    }

    @Test
    void methodCreateShouldReturnCourse() {
        CourseEntity preparedCourse = new CourseEntity(0,COURSE_NAME,COURSE_DESCRIPTION);
        when(courseService.create(any(CourseEntity.class))).thenReturn(new CourseEntity(1,COURSE_NAME,COURSE_DESCRIPTION));

        CourseEntity result = courseService.create(preparedCourse);

        assertNotNull(result);
        assertEquals(1,result.getId());
        assertNotEquals(0,result.getId());
        assertEquals(COURSE_NAME,result.getName());
        assertEquals(COURSE_DESCRIPTION,result.getDescription());
        verify(coursesDao,atLeastOnce()).create(preparedCourse);

    }

    @Test
    void methodFindBuIdShouldReturnCourseWithSpecifiedId() {
        CourseEntity preparedCourse = new CourseEntity(1,COURSE_NAME,COURSE_DESCRIPTION);
        when(courseService.findById(anyInt())).thenReturn(preparedCourse);

        CourseEntity result = courseService.findById(1);

        assertNotNull(result);
        assertEquals(1,result.getId());
        assertNotEquals(0,result.getId());
        assertEquals(COURSE_NAME,result.getName());
        assertEquals(COURSE_DESCRIPTION,result.getDescription());
        verify(coursesDao,atLeastOnce()).findById(anyInt());


    }

    @Test
    void methodReadAllShouldReturnListCourses() {
        List<CourseEntity>preparedCourseList = new ArrayList<>();
        when(courseService.readAll()).thenReturn(preparedCourseList);

        List<CourseEntity>result = courseService.readAll();

        assertNotNull(result);
        verify(coursesDao,atLeastOnce()).readAll();

    }

    @Test
    void methodUpdateShouldReturnCourseWithNewProperties() {
        CourseEntity preparedCourse = new CourseEntity(1,COURSE_NAME,COURSE_DESCRIPTION);
        when(courseService.update(any(CourseEntity.class))).thenReturn(preparedCourse);

        CourseEntity result = courseService.update(preparedCourse);

        assertNotNull(result);
        verify(coursesDao,atLeastOnce()).update(any(CourseEntity.class));

    }

    @Test
    void methodDeleteShouldReturnNothing() {
        courseService.delete(anyInt());

        verify(coursesDao,atLeastOnce()).deleteById(anyInt());
    }
}