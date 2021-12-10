package model;

import bean.account.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountModelImpl implements AccountModel {
    @Override
    public boolean createAccount(int uid, Account.AccountType type, Account.CurrencyType currency, String accountName) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("INSERT INTO account VALUES (NULL, %d, '%s', 0, '%s','%s')",
                    uid, type, currency, accountName);
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Account> queryAllAccount(int uid) {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM account WHERE uid=" + uid);
            while (rs.next()) {
                Account.AccountType type = Account.AccountType.valueOf(rs.getString(3));
                switch (type) {
                    case SAVING:
                        accounts.add(new SavingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6)));
                        break;
                    case CHECKING:
                        accounts.add(new CheckingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6)));
                        break;
                    case SECURITY:
                        accounts.add(new SecurityAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6)));
                        break;
                    case LOAN:
                        accounts.add(new LoanAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6)));
                        break;
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account queryAccount(int aid) {
        Account account = null;
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM account WHERE aid=" + aid);
            if (rs.next()) {
                Account.AccountType type = Account.AccountType.valueOf(rs.getString(3));
                switch (type) {
                    case SAVING:
                        account = new SavingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6));
                        break;
                    case CHECKING:
                        account = new CheckingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6));
                        break;
                    case SECURITY:
                        account = new SecurityAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6));
                        break;
                    case LOAN:
                        account = new LoanAccount(rs.getInt(1), rs.getInt(2),
                                rs.getFloat(4), rs.getString(5), rs.getString(6));
                        break;
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean updateBalance(int aid, double balance) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("UPDATE account SET balance=%.2f WHERE aid=%d", balance, aid);
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean withdraw(int aid, double amount) {
        Account account = queryAccount(aid);
        if (account != null)
            return updateBalance(aid, account.getBalance() - amount);
        return false;
    }

    @Override
    public boolean deposit(int aid, double amount) {
        Account account = queryAccount(aid);
        if (account != null)
            return updateBalance(aid, account.getBalance() + amount);
        return false;
    }

    @Override
    public boolean transfer(int fromBAid, int toBAid, double amount) {
        return withdraw(fromBAid, amount) && deposit(toBAid, amount);
    }

//    public static void main(String[] args) {
//        AccountModel accountModel = new AccountModelImpl();
//        System.out.println(accountModel.createAccount(1, Account.AccountType.SECURITY, Account.CurrencyType.USD));
//        System.out.println(accountModel.createAccount(1, Account.AccountType.SAVING, Account.CurrencyType.USD));
//        System.out.println(accountModel.queryAllAccount(1));
//        System.out.println(accountModel.queryAccount(1));
//        System.out.println(accountModel.deposit(1, 100));
//        System.out.println(accountModel.queryAccount(1));
//        System.out.println(accountModel.withdraw(1, 10));
//        System.out.println(accountModel.queryAccount(1));
//        System.out.println(accountModel.transfer(1, 2, 50));
//        System.out.println(accountModel.queryAllAccount(1));
//    }
}
