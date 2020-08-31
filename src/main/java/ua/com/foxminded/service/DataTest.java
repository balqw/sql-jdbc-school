package ua.com.foxminded.service;

import com.github.javafaker.Faker;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.dao.CoursesDao;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.CourseEntity;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;

public class DataTest {

    DBConnection postgresConnection = new DBConnection("postgres");
    InitialScriptRunner creatorDB = new InitialScriptRunner(postgresConnection);
    StudentsDao studentsDao = new StudentsDao(postgresConnection);
    StudentService studentService = new StudentService(studentsDao);
    GroupsDao groupsDao = new GroupsDao(postgresConnection);
    GroupService groupService = new GroupService(groupsDao);
    CoursesDao coursesDao = new CoursesDao(postgresConnection);
    CourseService courseService = new CourseService(coursesDao);
    Faker faker = new Faker();
    public void generatedGroup(){
        String name = "gp-";
        for(int i = 0;i<10;i++) {
            groupService.create(new GroupEntity(name+(i+1)));
        }
    }

    public void generatedCourses(){
        courseService.create(new CourseEntity("math","math course"));
        courseService.create(new CourseEntity("history","history course"));
        courseService.create(new CourseEntity("music","music course"));
        courseService.create(new CourseEntity("philosophy","philosophy course"));
        courseService.create(new CourseEntity("theology","theology course"));
        courseService.create(new CourseEntity("archaeology","archaeology course"));
        courseService.create(new CourseEntity("economics","economics course"));
        courseService.create(new CourseEntity("psychology","psychology course"));
        courseService.create(new CourseEntity("biology","biology course"));
        courseService.create(new CourseEntity("science","science course"));
    }

    public void generatedStudent(){
        for(int i=0;i<200;i++){
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            int groupId = 1+(int)(Math.random()*3);
            studentService.create(new StudentEntity(groupId,firstName,lastName));
        }
    }
}
