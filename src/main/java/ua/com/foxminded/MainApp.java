package ua.com.foxminded;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;
import ua.com.foxminded.service.DBService;
import ua.com.foxminded.service.GroupService;
import ua.com.foxminded.service.StudentService;

import java.util.List;


public class MainApp {
    public static void main(String[] args) {
        DBConnection postgresConnection = new DBConnection("postgres");
        InitialScriptRunner creatorDB = new InitialScriptRunner(postgresConnection);
        DBService dbService = new DBService();
        creatorDB.creat();
        //dbService.selectOperation();
        StudentsDao studentsDao = new StudentsDao(postgresConnection);
        StudentService studentService = new StudentService(studentsDao);
        GroupsDao groupsDao = new GroupsDao(postgresConnection);
        GroupService groupService = new GroupService(groupsDao);

        groupService.create(new GroupEntity("rs11"));
        System.out.println(studentService.create(new StudentEntity(1,"dsd","asd")));






    }
}
