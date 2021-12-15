package model;

import bean.account.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountModelImpl implements AccountModel {
    @Override
    public boolean createAccount(int uid, Account.AccountType type, double balance, Account.CurrencyType currency, String accountName) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("INSERT INTO account VALUES (NULL, %d, '%s','%s', %f, '%s', '%d')",
                    uid, accountName, type, balance, currency, 1);
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
            String sql = String.format("SELECT * FROM account WHERE uid=%d AND status=1", uid);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Account.AccountType type = Account.AccountType.valueOf(rs.getString(3));
                switch (type) {
                    case SAVING:
                        accounts.add(new SavingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6)));
                        break;
                    case CHECKING:
                        accounts.add(new CheckingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6)));
                        break;
                    case SECURITY:
                        accounts.add(new SecurityAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6)));
                        break;
                    case LOAN:
                        accounts.add(new LoanAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6)));
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
    public List<LoanAccount> queryLoanAccounts() {
        List<LoanAccount> accounts = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM account WHERE status=1 AND type=" + Account.AccountType.LOAN);
            while (rs.next()) {
                accounts.add(new LoanAccount(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getDouble(5), rs.getString(6)));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<SavingAccount> querySavingAccounts() {
        List<SavingAccount> accounts = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM account WHERE status=1 AND type=" + Account.AccountType.SAVING);
            while (rs.next()) {
                accounts.add(new SavingAccount(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getDouble(5), rs.getString(6)));
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
            ResultSet rs = statement.executeQuery("SELECT * FROM account WHERE status=1 AND aid=" + aid);
            if (rs.next()) {
                Account.AccountType type = Account.AccountType.valueOf(rs.getString(3));
                switch (type) {
                    case SAVING:
                        account = new SavingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6));
                        break;
                    case CHECKING:
                        account = new CheckingAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6));
                        break;
                    case SECURITY:
                        account = new SecurityAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6));
                        break;
                    case LOAN:
                        account = new LoanAccount(rs.getInt(1), rs.getInt(2),
                                rs.getString(3), rs.getDouble(5), rs.getString(6));
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
    public boolean deleteAccount(int aid) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("UPDATE account SET status=0 WHERE aid=%d", aid);
            int result = statement.executeUpdate(sql);
            statement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
//        System.out.println(accountModel.createAccount(1, Account.AccountType.SECURITY,0, Account.CurrencyType.USD));
//        System.out.println(accountModel.createAccount(1, Account.AccountType.SAVING,0, Account.CurrencyType.USD));
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
