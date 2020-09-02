package ua.com.foxminded.service;


import ua.com.foxminded.domain.entity.StudentEntity;

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

    public void selectOperation() {
        System.out.println("Application menu:\n"
                + "a. Find all groups with less or equals student count\n"
                + "b. Find all students related to course with given name\n"
                + "c. Add new student\n"
                + "d. Delete student by STUDENT_ID\n"
                + "e. Add a student to the course (from a list)\n"
                + "f. Remove the student from one of his or her courses\n");


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("a")) {
            System.out.println("Please enter number of students:");
            int numberOfStudents = Integer.parseInt(scanner.nextLine());
            System.out.println(groupService.findGroupEqualsStudentCount(numberOfStudents));

        } else if (input.equalsIgnoreCase("b")) {
            System.out.println("Please write course");
            String course = scanner.nextLine();
            System.out.println(studentService.searchByCourse(course));
        } else if (input.equalsIgnoreCase("c")) {
            System.out.println("Please write student name:");
            String firstName = scanner.nextLine();
            System.out.println("Please write student last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please write student group_id(1-10):");
            int groupId = Integer.parseInt(scanner.nextLine());
            studentService.create(new StudentEntity(groupId, firstName, lastName));

        } else if (input.equalsIgnoreCase("d")) {

        } else if (input.equalsIgnoreCase("e")) {

        } else if (input.equalsIgnoreCase("f")) {

        } else {

        }


    }
}
