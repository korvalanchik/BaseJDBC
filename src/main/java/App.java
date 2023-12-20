import com.basejdbc.storage.DatabaseInitService;
import com.basejdbc.storage.Storage;

import java.sql.Connection;
import java.sql.SQLException;


public class App {
    public static void main(String[] args) throws SQLException {
        Storage storage = Storage.getInstance();
        new DatabaseInitService().initDb(storage);
//        storage.executeUpdate("CREATE TABLE IF NOT EXISTS work(ID int,NAME varchar(1000))");
    }

}
