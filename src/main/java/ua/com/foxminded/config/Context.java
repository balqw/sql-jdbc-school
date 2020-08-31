package ua.com.foxminded.config;

import com.github.javafaker.Faker;
import ua.com.foxminded.domain.dao.CoursesDao;
import ua.com.foxminded.domain.dao.GroupsDao;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.service.*;

public final class Context {
    public static final String POSTGRES = "postgres";
    public static final String H2 = "h2";

    private final DBConnection dbConnection;
    private final InitialScriptRunner initialScriptRunner;
    private final DataGenerator dataGenerator;
    private final UIService uiService;
    private final StudentsDao studentsDao;
    private final GroupsDao groupsDao;
    private final CoursesDao coursesDao;
    private final StudentService studentService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final Faker faker;

    private Context(DBConnection postgresConnection,
                    InitialScriptRunner creatorDB,
                    DataGenerator dataGenerator,
                    UIService uiService,
                    StudentsDao studentsDao,
                    GroupsDao groupsDao,
                    CoursesDao coursesDao,
                    StudentService studentService,
                    GroupService groupService,
                    CourseService courseService,
                    Faker faker) {
        this.dbConnection = postgresConnection;
        this.initialScriptRunner = creatorDB;
        this.dataGenerator = dataGenerator;
        this.uiService = uiService;
        this.studentsDao = studentsDao;
        this.groupsDao = groupsDao;
        this.coursesDao = coursesDao;
        this.studentService = studentService;
        this.groupService = groupService;
        this.courseService = courseService;
        this.faker = faker;
    }

    public static Context connectorTypeBuilder(String dbPrefix) {
        DBConnection connection = new DBConnection(dbPrefix);
        InitialScriptRunner initialScriptRunner = new InitialScriptRunner(connection);
        StudentsDao studentDao = new StudentsDao(connection);
        GroupsDao groupDao = new GroupsDao(connection);
        CoursesDao courseDao = new CoursesDao(connection);
        StudentService studentService = new StudentService(studentDao);
        GroupService groupService = new GroupService(groupDao);
        CourseService courseService = new CourseService(courseDao);
        UIService uiService = new UIService(studentService, groupService, courseService);
        Faker faker = new Faker();
        DataGenerator dataGenerator = new DataGenerator(studentService, groupService, courseService, faker);
        return new Context(connection, initialScriptRunner, dataGenerator, uiService, studentDao,
                groupDao, courseDao, studentService, groupService, courseService, faker);
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public InitialScriptRunner getInitialScriptRunner() {
        return initialScriptRunner;
    }

    public DataGenerator getDataGenerator() {
        return dataGenerator;
    }

    public UIService getUiService() {
        return uiService;
    }

    public StudentsDao getStudentsDao() {
        return studentsDao;
    }

    public GroupsDao getGroupsDao() {
        return groupsDao;
    }

    public CoursesDao getCoursesDao() {
        return coursesDao;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public Faker getFaker() {
        return faker;
    }

}
