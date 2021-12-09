package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
    private static volatile BaseDAO baseDAO;
    protected Connection connection;

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/bank.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened bank db successfully");
    }

    public static BaseDAO getInstance() {
        if (baseDAO == null)
            synchronized (AccountDB.class) {
                if (baseDAO == null)
                    baseDAO = new BaseDAO();
            }
        return baseDAO;
    }

    public Connection getConnection() {
        return connection;
    }

    private void createAccount() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS account(\n" +
                "    aid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    uid INTEGER,\n" +
                "    type INTEGER NOT NULL,\n" +
                "    balance INTEGER,\n" +
                "    currency TEXT\n" +
                "    FOREIGN KEY(uid) REFERENCES user(uid) " +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    private void createStock() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS stock(\n" +
                "    sid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name TEXT,\n" +
                "    price REAL,\n" +
                "    quantity INTEGER,\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }


    private void createHolding() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS holding(\n" +
                "    aid INTEGER,\n" +
                "    sid INTEGER,\n" +
                "    quantity INTEGER,\n" +
                "    PRIMARY KEY(aid, sid)\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    private void createTransaction() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS transaction(\n" +
                "    tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    aid INTEGER,\n" +
                "    fromaid INTEGER,\n" +
                "    toaid INTEGER,\n" +
                "    type INTEGER NOT NULL,\n" +
                "    amount INTEGER,\n" +
                "    fee INTEGER,\n" +
                "    date TEXT,\n" +
                "    FOREIGN KEY(aid, frombaid, tobaid) REFERENCES account(aid, aid, aid)\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void createUser() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
                "    aid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    username TEXT UNIQUE,\n" +
                "    pwd TEXT\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }
}
