package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDB {
    private volatile static AccountDB accountDB;
    private Connection connection = null;

    private AccountDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/account.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Opened account db successfully");
    }

    public static AccountDB getInstance() {
        if (accountDB == null) {
            synchronized (AccountDB.class) {
                if (accountDB == null)
                    accountDB = new AccountDB();
            }
        }
        return accountDB;
    }

    public void create() throws SQLException {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS account (\n" +
                    "    aid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    username TEXT UNIQUE,\n" +
                    "    pwd TEXT\n" +
                    ")";
            statement.executeUpdate(sql);
            statement.close();
    }
}
