package com.basejdbc.storage;

import com.basejdbc.prefs.Prefs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public static final String INIT_DB_FILENAME = "initDB.sql";
    public void initDb(Storage storage) {
        try {
            String initDbFileName = new Prefs().getString(Prefs.INIT_DB_SQL_FILE_PATH);
            String sql = String.join("\n", Files.readAllLines(Paths.get(initDbFileName)));
            storage.executeUpdate(sql);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
