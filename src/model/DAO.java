package model;

import bean.Stock;
import bean.account.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static volatile DAO baseDAO;
    private Connection connection;

    private DAO() {
        try {
            connect();
            createUser();
            createAccount();
            createTransaction();
            createStock();
            createHolding();
            createCollateral();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:db/bank.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened bank db successfully");
    }

    public static DAO getInstance() {
        if (baseDAO == null)
            synchronized (DAO.class) {
                if (baseDAO == null) {
                    baseDAO = new DAO();
                    createManager();

                }
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
                "    name TEXT,\n" +
                "    type TEXT,\n" +
                "    balance REAL,\n" +
                "    currency TEXT,\n" +
                "    status TEXT,\n" +
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
                "    quantity INTEGER\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }


    private void createHolding() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS holding(\n" +
                "    uid INTEGER,\n" +
                "    sid INTEGER,\n" +
                "    quantity INTEGER,\n" +
                "    PRIMARY KEY(uid, sid)\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    private void createTransaction() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS txn(\n" +
                "    tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    uid INTEGER,\n" +
                "    fromaid INTEGER,\n" +
                "    toaid INTEGER,\n" +
                "    type TEXT,\n" +
                "    currency TEXT,\n" +
                "    amount REAL,\n" +
                "    fee REAL,\n" +
                "    detail TEXT,\n" +
                "    time INTEGER,\n" +
                "    FOREIGN KEY(uid) REFERENCES user(uid),\n" +
                "    FOREIGN KEY(fromaid, toaid) REFERENCES account(aid, aid)\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    private void createCollateral() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS collateral(\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    uid INTEGER,\n" +
                "    name TEXT,\n" +
                "    value REAL\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    public void createUser() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
                "    uid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    type INTEGER,\n" +
                "    username TEXT UNIQUE,\n" +
                "    pwd TEXT,\n" +
                "    status TEXT\n" +
                ")";
        statement.executeUpdate(sql);
        statement.close();
    }

    public static void createManager() {
        UserModelImpl userModel = new UserModelImpl();
        if (userModel.isUserExists("admin"))
            return;
        generateDefaultStock();
        userModel.signUp(0, "admin", "admin");
        int userID = userModel.queryManager().getUid();
        AccountModelImpl accountModel = new AccountModelImpl();
        accountModel.createAccount(userID, "USD", Account.AccountType.SAVING, 100000, Account.CurrencyType.USD);
        accountModel.createAccount(userID, "RMB", Account.AccountType.SAVING, 100000, Account.CurrencyType.RMB);
        accountModel.createAccount(userID, "EUR", Account.AccountType.SAVING, 100000, Account.CurrencyType.EUR);
    }

    public static void generateDefaultStock(){
        StockMarketModelImpl stockMarketModel=new StockMarketModelImpl();
        Stock stock1=new Stock(-1,"Apple",100,100);

        Stock stock2=new Stock(-1,"Google",200,100);
        System.out.println("1111111111");
        List<Stock> stockList=new ArrayList<>();
        stockList.add(stock1);
        stockList.add(stock2);
        stockMarketModel.insertStocks(stockList);
    }

    public static void main(String[] args) {
        DAO.getInstance();
        createManager();
    }
}
