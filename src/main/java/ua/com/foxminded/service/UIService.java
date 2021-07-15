package ua.com.foxminded.service;


import ua.com.foxminded.domain.entity.StudentEntity;

import java.util.List;
import java.util.Scanner;

public class UIService {

    private final StudentService studentService;
    private final GroupService groupService;
    private final CourseService courseService;

    public UIService(StudentService studentService, GroupService groupService, CourseService courseService) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.courseService = courseService;
    }

    public void runMenu(){
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String operation = "";
        while (!operation.equals("q")){
            operation = scanner.nextLine();
            selectOperation(operation);
            printMenu();
        }
    }

    public void selectOperation(String operation) {
        Scanner scanner = new Scanner(System.in);
        if (operation.equalsIgnoreCase("a")) {
            System.out.println("Please enter number of students:");
            int numberOfStudents = Integer.parseInt(scanner.nextLine());
            System.out.println(groupService.findGroupEqualsStudentCount(numberOfStudents));
        } else if (operation.equalsIgnoreCase("b")) {
            System.out.println("Please write course");
            String course = scanner.nextLine();
            List<StudentEntity>result =  studentService.searchByCourse(course);
            for(StudentEntity studentEntity: result){
                System.out.println(studentEntity);
            }
        } else if (operation.equalsIgnoreCase("c")) {
            System.out.println("Please enter name:");
            String firstName = scanner.nextLine();
            System.out.println("Please write student last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please write student group_id(1-10):");
            int groupId = Integer.parseInt(scanner.nextLine());
            studentService.create(new StudentEntity(groupId, firstName, lastName));
        } else if (operation.equalsIgnoreCase("d")) {
            System.out.println("Please write student ID:");
            int id = Integer.parseInt(scanner.nextLine());
            studentService.delete(id);
        } else if (operation.equalsIgnoreCase("e")) {
            System.out.println("Please write student_id");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please write course_id");
            int courseId = Integer.parseInt(scanner.nextLine());
            studentService.addCourse(studentId,courseId);
        } else if (operation.equalsIgnoreCase("f")) {
            System.out.println("Please write student_id");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.println("Please write course_id");
            int courseId = Integer.parseInt(scanner.nextLine());
            studentService.deleteCourseFromStudent(studentId,courseId);
        } else {
            System.out.println("Operation is incorrect");
        }
    }

    private void printMenu(){
        System.out.println("Application menu:\n"
                + "a. Find all groups with less or equals student count\n"
                + "b. Find all students related to course with given name\n"
                + "c. Add new student\n"
                + "d. Delete student by STUDENT_ID\n"
                + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of his or her courses\n");

    }
}
