import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import com.ibatis.common.jdbc.ScriptRunner;
import dao.DBConnection;

public class InitialisationDB {

    DBConnection dbConnection = new DBConnection();


    public void creat() throws SQLException {
        String sqlProperties = "src/main/resources/init.sql";
        Connection connection = null;

        try {
            connection = dbConnection.getConnection();
            ScriptRunner sr = new ScriptRunner(connection,false,false);
            Reader reader = new BufferedReader(
                    new FileReader(sqlProperties));
            sr.runScript(reader);

        } catch (FileNotFoundException e) {
            System.out.println("File with properties doesnt exists");
        } catch (IOException e) {
            System.out.println("File with properties doesnt exists");
        } finally {
            connection.close();
        }
    }
}