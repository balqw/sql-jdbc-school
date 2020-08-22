package domain;

import dao.ScriptSql;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        ScriptSql scriptRunner = new ScriptSql();
        scriptRunner.run();


    }
}
