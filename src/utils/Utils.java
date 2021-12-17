package utils;

import bean.Stock;
import bean.StockHolding;
import bean.Transaction;
import bean.account.Account;
import bean.user.Customer;
import bean.user.User;

import javax.swing.table.TableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String[][] accountToArray(List<Account> accountList) {
        String[][] array = new String[accountList.size()][];
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            String[] strings = {String.valueOf(account.getAid()), account.getAccountName(), String.valueOf(account.getType()),
                    String.valueOf(account.getBalance()), String.valueOf(account.getCurrency())};
            array[i] = strings;
        }
        return array;
    }

    public static String[][] stockToArray(List<Stock> stockList) {
        String[][] array = new String[stockList.size()][];
        for (int i = 0; i < stockList.size(); i++) {
            Stock stock = stockList.get(i);
            String[] strings = {String.valueOf(stock.getSid()), stock.getName(),
                    String.valueOf(stock.getPrice()), String.valueOf(stock.getQuantity())};
            array[i] = strings;
        }
        return array;
    }

    public static List<Stock> arrayToStock(TableModel model) {
        List<Stock> stockList = new ArrayList<>(model.getRowCount());
        for (int i = 0; i < model.getRowCount(); i++) {
            stockList.add(new Stock((int) model.getValueAt(i, 0), (String) model.getValueAt(i, 1),
                    (double) model.getValueAt(i, 2), (int) model.getValueAt(i, 3)));
        }
        return stockList;
    }

    public static String[][] transactionToArray(List<Transaction> transactionList) {
        String[][] array = new String[transactionList.size()][];
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction transaction = transactionList.get(i);
            String[] strings = {transaction.getToAid() < 0 ? "In-person" : String.valueOf(transaction.getToAid()),
                    String.valueOf(transaction.getType()), String.valueOf(transaction.getCurrencyType()),
                    String.valueOf(transaction.getAmount()), String.valueOf(transaction.getFee()),
                    transaction.getDetail(), transaction.getTime().format(DateTimeFormatter.ISO_DATE)};
            array[i] = strings;
        }
        return array;
    }


    public static String[][] userToArray(List<Customer> userList) {
        String[][] array = new String[userList.size()][];
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String[] strings = {String.valueOf(user.getUid()), user.getUsername(), user.getPwd()};
            array[i] = strings;
        }
        return array;
    }

    public static String[][] holdingToArray(List<StockHolding> stockList) {
        String[][] array = new String[stockList.size()][];
        for (int i = 0; i < stockList.size(); i++) {
            StockHolding holding = stockList.get(i);
            String[] strings = {String.valueOf(holding.getStock().getSid()), holding.getStock().getName(),
                    String.valueOf(holding.getStock().getPrice()), String.valueOf(holding.getQuantity())};
            array[i] = strings;
        }
        return array;
    }
}
