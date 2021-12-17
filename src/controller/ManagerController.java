package controller;

import bean.Stock;
import bean.Transaction;
import bean.account.Account;
import bean.account.LoanAccount;
import bean.account.SavingAccount;
import bean.user.Customer;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    private SavingAccount USD_ACCOUNT;
    private SavingAccount RMB_ACCOUNT;
    private SavingAccount EUR_ACCOUNT;

    private AccountModel accountModel;
    private StockMarketModel stockMarketModel;
    private TransactionModel transactionModel = new TransactionModelImpl();

    public ManagerController() {
        accountModel = new AccountModelImpl();
        stockMarketModel = new StockMarketModelImpl();
        this.USD_ACCOUNT = (SavingAccount) accountModel.queryAccount(1);
        this.RMB_ACCOUNT = (SavingAccount) accountModel.queryAccount(2);
        this.EUR_ACCOUNT = (SavingAccount) accountModel.queryAccount(3);
    }

    public void updateAccount() {
        USD_ACCOUNT = (SavingAccount) accountModel.queryAccount(1);
        RMB_ACCOUNT = (SavingAccount) accountModel.queryAccount(2);
        EUR_ACCOUNT = (SavingAccount) accountModel.queryAccount(3);
    }

    public SavingAccount getUSD_ACCOUNT() {
        return USD_ACCOUNT;
    }

    public SavingAccount getRMB_ACCOUNT() {
        return RMB_ACCOUNT;
    }

    public SavingAccount getEUR_ACCOUNT() {
        return EUR_ACCOUNT;
    }

    public List<Account> getAllBankAccount() {
        updateAccount();
        List<Account> accounts = new ArrayList<>();
        accounts.add(getUSD_ACCOUNT());
        accounts.add(getRMB_ACCOUNT());
        accounts.add(getEUR_ACCOUNT());
        return accounts;
    }

    public List<Customer> showCustomers() {
        UserModelImpl userModel = new UserModelImpl();

        return userModel.queryAllCustomer();
    }


    public List<Account> getCustomerAccounts(int uid) {
        return accountModel.queryAllAccount(uid);
    }


    public List<Stock> getStockList() {
        return stockMarketModel.queryMarket();
    }

    public boolean updateStock(Stock stock) {
        return stockMarketModel.updateStock(stock);
    }

    public boolean addStock(String name, double price, int quantity) {
        List<Stock> list = new ArrayList<>();
        list.add(new Stock(0, name, price, quantity));
        return stockMarketModel.insertStocks(list) == 1;
    }

//    public boolean deleteStock(int stockID) {
    // need check if anyone has this stock
//        deleteStock(stockID);
//        return true;
//
//    }

    public boolean giveSavingInterest(double rate, double minBalance) {
        List<SavingAccount> accounts = accountModel.querySavingAccounts();
        for (SavingAccount account : accounts
        ) {
            double balance = account.getBalance();
            if (balance >= minBalance) {
                double interest = round(balance * rate);
                int fromID;
                if (account.getCurrency() == Account.CurrencyType.USD) {
                    fromID = USD_ACCOUNT.getAid();
                } else if (account.getCurrency() == Account.CurrencyType.RMB) {
                    fromID = RMB_ACCOUNT.getAid();
                } else {
                    fromID = EUR_ACCOUNT.getAid();
                }
                // update customer account
                accountModel.updateBalance(account.getAid(), balance + interest);
                Transaction trans = new Transaction(-1, account.getUid(), fromID, account.getAid(), Transaction.TransType.INTEREST,
                        account.getCurrency(), interest, 0, "get interest", LocalDateTime.now());
                transactionModel.insertTransaction(trans);
                // update manager account
                double mBalance=accountModel.queryAccount(fromID).getBalance();
                accountModel.updateBalance(fromID, mBalance - interest);
                Transaction trans2 = new Transaction(-1, 1, fromID, account.getAid(), Transaction.TransType.INTEREST,
                        account.getCurrency(), interest, 0, "give interest to customer: " + account.getUid(),
                        LocalDateTime.now());
                transactionModel.insertTransaction(trans2);
            }
        }
        return true;
    }

    public boolean increaseLoanInterest(double rate) {
        List<LoanAccount> accounts = accountModel.queryLoanAccounts();
        for (LoanAccount account : accounts
        ) {
            double balance = account.getBalance();
            if (balance <= 0) {
                continue;
            }
            double interest = round(balance * rate);
            // update customer account
            accountModel.updateBalance(account.getAid(), balance + interest);
            Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), account.getAid(), Transaction.TransType.INTEREST,
                    account.getCurrency(), interest, 0, "loan interests added", LocalDateTime.now());
            transactionModel.insertTransaction(trans);
        }
        return true;
    }

    public List<Transaction> showTransactionByDay(LocalDateTime day) {
        return transactionModel.queryTransactionByDay(day);
    }

    public List<Transaction> showAllTransaction() {
        return transactionModel.queryAllTransaction();
    }

    private double round(double value) {
        return (double) (Math.round(value * 100) / 100);
    }
}
