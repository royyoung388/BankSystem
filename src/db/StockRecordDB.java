package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StockRecordDB {
    private volatile static StockRecordDB stockRecordDB;
    private Connection connection;

    private StockRecordDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/stockrecord.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened market db successfully");
    }

    public StockRecordDB getInstance() {
        if (stockRecordDB == null)
            synchronized (StockRecordDB.class) {
                if (stockRecordDB == null)
                    stockRecordDB = new StockRecordDB();
            }
        return stockRecordDB;
    }

    private void create() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS stockrecord(\n" +
                "    aid INTEGER,\n" +
                "    sid INTEGER,\n" +
                "    quantity INTEGER,\n" +
                "    PRIMARY KEY(aid, sid)\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    public Connection getConnection() {
        return connection;
    }
}
