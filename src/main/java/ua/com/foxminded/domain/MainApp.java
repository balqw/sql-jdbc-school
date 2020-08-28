package ua.com.foxminded.domain;

import ua.com.foxminded.dao.GroupsDao;
import ua.com.foxminded.dao.StudentsDao;
import ua.com.foxminded.domain.entity.GroupEntity;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.sql.SQLException;


public class MainApp {
    public static void main(String[] args) throws SQLException {

        InitialisationDB creatorDB = new InitialisationDB();
        creatorDB.creat();
/*

*/      StudentsDao studentsDao = new StudentsDao();
        GroupsDao groupsDao = new GroupsDao();
        GroupEntity ge1 = new GroupEntity("rs-11");
        System.out.println(groupsDao.insert(ge1));
        GroupEntity ge2 = new GroupEntity("rt-12");
        System.out.println(groupsDao.insert(ge2));
        StudentEntity se1 = new StudentEntity(1,"Vasya","Petrov");
        System.out.println(studentsDao.insert(se1));
        StudentEntity se2 = new StudentEntity(1,"Sasha","Stepanov");
        System.out.println(studentsDao.insert(se2));
        StudentEntity se3 = new StudentEntity(2,"Lena","Penkova");
        System.out.println(studentsDao.insert(se3));
        studentsDao.delete(se1);
        System.out.println("______");
        System.out.println(studentsDao.read());
        se2.setLast_name("new");
        studentsDao.update(se2);
        System.out.println(studentsDao.read());
        studentsDao.deleteById(3);
        System.out.println(studentsDao.read());
    }
}
