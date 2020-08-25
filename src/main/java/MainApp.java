import dao.postgre.GroupsDao;
import dao.postgre.StudentsDao;
import domain.entity.StudentEntity;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        InitialisationDB creatorDB = new InitialisationDB();
        creatorDB.creat();
/*

*/      StudentsDao studentsDao = new StudentsDao();
        GroupsDao groupsDao = new GroupsDao();
        groupsDao.insert("math");
        studentsDao.insert("vasya","petrov");
        studentsDao.insert("vasya2","petrov2");

        List<StudentEntity>list = studentsDao.read();

        System.out.println(list);

        studentsDao.update(1,"new","new");
        list = studentsDao.read();
        System.out.println(list);
        studentsDao.delete(1);
        list = studentsDao.read();
        System.out.println(list);

    }
}
