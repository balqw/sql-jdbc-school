package ua.com.foxminded;

import ua.com.foxminded.config.Context;
import ua.com.foxminded.config.InitialScriptRunner;
import ua.com.foxminded.service.DataGenerator;
import ua.com.foxminded.service.UIService;
import static ua.com.foxminded.config.Context.H2;
import static ua.com.foxminded.config.Context.POSTGRES;


public class MainApp {
    public static void main(String[] args) {
        Context context = Context.connectorTypeBuilder(H2);


        InitialScriptRunner creatorDB = context.getInitialScriptRunner();
        creatorDB.creat("src/main/resources/init.sql");

        DataGenerator dataGenerator = context.getDataGenerator();
        dataGenerator.generateGroups();
        dataGenerator.generateCourses();
        dataGenerator.generateStudents();
        dataGenerator.generatedStudentCourse();


        UIService dbService = context.getUiService();


        dbService.runMenu();

    }
}
