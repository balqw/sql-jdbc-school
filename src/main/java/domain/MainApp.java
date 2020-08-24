package domain;

import dao.CreatorDB;
import dao.StudentsDao;
import domain.entity.Student;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        CreatorDB creatorDB = new CreatorDB();
        creatorDB.creat();

        Student student = new Student(3,"Vasya","Petrov");
        List <Student>list = Arrays.asList(student);
        StudentsDao studentsDao = new StudentsDao();
        studentsDao.insert(list.get(0).getFirst_name(),list.get(0).getLast_name());
        List<Student>students = studentsDao.takeAll();


        System.out.println(students);

    }
}
