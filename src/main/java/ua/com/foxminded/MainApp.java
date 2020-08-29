package ua.com.foxminded;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.service.StudentService;

import java.util.List;


public class MainApp {
    public static void main(String[] args) {
        DBConnection postgresConnection = new DBConnection("postgres");
        InitialScriptRunner creatorDB = new InitialScriptRunner(postgresConnection);
        StudentsDao studentsDao = new StudentsDao(postgresConnection);
        StudentService studentService = new StudentService(studentsDao);

        creatorDB.creat();
        List<StudentEntity> studentEntities = studentService.readAll();
    }
}
