package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BankDB {
    private volatile static BankDB bankDB;
    private Connection connection;

    private BankDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/bank.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened bank db successfully");
    }

    public BankDB getInstance() {
        if (bankDB == null)
            synchronized (BankDB.class) {
                if (bankDB == null)
                    bankDB = new BankDB();
            }
        return bankDB;
    }

    public void create() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS bank(\n" +
                "    currency TEXT PRIMARY KEY,\n" +
                "    balance INTEGER\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }
}
