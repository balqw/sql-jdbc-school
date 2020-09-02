package ua.com.foxminded.service;

import com.github.javafaker.Faker;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.CourseEntity;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;

public class DataGenerator {
    private final StudentService studentService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final Faker faker;

    public DataGenerator(StudentService studentService, GroupService groupService, CourseService courseService, Faker faker) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.courseService = courseService;
        this.faker = faker;
    }


    public void generateGroups() {
        String name = "gp-";
        for (int i = 0; i < 10; i++) {
            groupService.create(new GroupEntity(name + (i + 1)));
        }
    }

    public void generatedStudentCourse(){

        for(int i= 1;i<=200;i++){
            int randomCount =1+ (int) (Math.random() * 3);
            for(int j = 0; j < randomCount;j++) {
                int randomCourse = 1 + (int) (Math.random() * 10);
                studentService.addCourse(i, randomCourse);
            }
        }
    }


    public void generateCourses() {
        courseService.create(new CourseEntity("math", "math course"));
        courseService.create(new CourseEntity("history", "history course"));
        courseService.create(new CourseEntity("music", "music course"));
        courseService.create(new CourseEntity("philosophy", "philosophy course"));
        courseService.create(new CourseEntity("theology", "theology course"));
        courseService.create(new CourseEntity("archaeology", "archaeology course"));
        courseService.create(new CourseEntity("economics", "economics course"));
        courseService.create(new CourseEntity("psychology", "psychology course"));
        courseService.create(new CourseEntity("biology", "biology course"));
        courseService.create(new CourseEntity("science", "science course"));
    }


    public void generateStudents() {
        for (int i = 0; i < 200; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            int groupId = 1 + (int) (Math.random() * 10);
            studentService.create(new StudentEntity(groupId,firstName, lastName));
        }
    }
}
