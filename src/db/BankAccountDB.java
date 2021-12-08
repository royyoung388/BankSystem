package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BankAccountDB {
    private volatile static BankAccountDB bankAccountDB;
    private Connection connection;

    private BankAccountDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/bankaccount.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened bank account db successfully");
    }

    public static BankAccountDB getInstance() {
        if (bankAccountDB == null)
            synchronized (BankAccountDB.class) {
                if (bankAccountDB == null)
                    bankAccountDB = new BankAccountDB();
            }
        return bankAccountDB;
    }

    public void create() throws SQLException {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS bankaccount(\n" +
                    "    baid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    type INTEGER NOT NULL,\n" +
                    "    balance INTEGER,\n" +
                    "    currency TEXT\n" +
                    ")";
            statement.executeUpdate(sql);
            statement.close();
    }
}
