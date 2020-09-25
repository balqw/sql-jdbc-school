package ua.com.foxminded.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Scanner;
import org.mockito.Mockito.*;
import ua.com.foxminded.config.Context;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UIServiceTest {
    private static UIService uiService;

    @BeforeEach
    public void beforeEach() {
      StudentService studentService = mock(StudentService.class);
      GroupService groupService = mock(GroupService.class);
      CourseService courseService = mock(CourseService.class);
      uiService = mock(UIService.class);
        //uiService = new UIService(studentService,groupService,courseService);
    }

    @Test
    void runMenu() {



    }
}