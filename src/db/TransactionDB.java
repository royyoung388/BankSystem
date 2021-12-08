package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDB {
    private volatile static TransactionDB transactionDB;
    private Connection connection;

    private TransactionDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/transaction.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened transaction db successfully");
    }

    public TransactionDB getInstance() {
        if (transactionDB == null)
            synchronized (TransactionDB.class) {
                if (transactionDB == null)
                    transactionDB = new TransactionDB();
            }
        return transactionDB;
    }

    public void create() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS transaction(\n" +
                "    tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    aid INTEGER,\n" +
                "    frombaid INTEGER,\n" +
                "    tobaid INTEGER,\n" +
                "    type INTEGER NOT NULL,\n" +
                "    amount INTEGER,\n" +
                "    fee INTEGER,\n" +
                "    date TEXT,\n" +
                "    FOREIGN KEY(aid, frombaid, tobaid) REFERENCES account(aid, aid, aid)\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }
}
