package ua.com.foxminded.service;


import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.dao.CoursesDao;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

public class DBService {

    DBConnection postgresConnection = new DBConnection("postgres");
    InitialScriptRunner creatorDB = new InitialScriptRunner(postgresConnection);
    StudentsDao studentsDao = new StudentsDao(postgresConnection);
    StudentService studentService = new StudentService(studentsDao);
    GroupsDao groupsDao = new GroupsDao(postgresConnection);
    GroupService groupService = new GroupService(groupsDao);
    CoursesDao coursesDao = new CoursesDao(postgresConnection);
    CourseService courseService = new CourseService(coursesDao);



    public void selectOperation(){
        System.out.println("Application menu:\n"
        +"a. Find all groups with less or equals student count\n"
        +"b. Find all students related to course with given name\n"
        +"c. Add new student\n"
        +"d. Delete student by STUDENT_ID\n"
        +"e. Add a student to the course (from a list)\n"
        +"f. Remove the student from one of his or her courses\n");


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("a")){

        }else if (input.equalsIgnoreCase("b")){

        }else if (input.equalsIgnoreCase("c")){
            System.out.println("Please write student name");
            String firstName = scanner.nextLine();
            System.out.println("Please write student last name");
            String lastName = scanner.nextLine();
            System.out.println("Please write student group_id");
            int groupId = Integer.parseInt(scanner.nextLine());
            studentService.create(new StudentEntity(groupId,firstName,lastName));

        }else if (input.equalsIgnoreCase("d")){

        }else if (input.equalsIgnoreCase("e")){

        }else if (input.equalsIgnoreCase("f")){

        }else{

        }



    }
}
