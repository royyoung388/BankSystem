package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class StockDB {
    private volatile static StockDB stockDB;
    private Connection connection;

    private StockDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/stock.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened stock db successfully");
    }

    public StockDB getInstance() {
        if (stockDB == null)
            synchronized (StockDB.class) {
                if (stockDB == null)
                    stockDB = new StockDB();
            }
        return stockDB;
    }
}
