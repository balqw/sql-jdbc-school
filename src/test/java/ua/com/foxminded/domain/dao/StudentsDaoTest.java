package ua.com.foxminded.domain.dao;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.config.Context;
import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.config.InitialScriptRunner;

import static org.junit.jupiter.api.Assertions.*;
import static ua.com.foxminded.config.Context.H2;

class StudentsDaoTest {
   private Context context = Context.connectorTypeBuilder(H2);
   private DBConnection connection = context.getDbConnection();


    @Test
    void create() {
    }

    @Test
    void findBuId() {
    }

    @Test
    void readAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void additionCourse() {
    }

    @Test
    void searchStudentByCourse() {
    }
}