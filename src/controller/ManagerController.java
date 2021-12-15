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

    private final SavingAccount USD_ACCOUNT;
    private final SavingAccount RMB_ACCOUNT;
    private final SavingAccount EUR_ACCOUNT;

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

    public SavingAccount getUSD_ACCOUNT() {
        return USD_ACCOUNT;
    }

    public SavingAccount getRMB_ACCOUNT() {
        return RMB_ACCOUNT;
    }

    public SavingAccount getEUR() {
        return EUR_ACCOUNT;
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

    public boolean setStock(Stock stock) {
        return stockMarketModel.updateStock(stock);
    }

    public boolean addStock(Stock stock) {
        List<Stock> list = new ArrayList<>();
        list.add(stock);
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
                double interest = balance * rate;
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
                accountModel.updateBalance(fromID, balance - interest);
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
            if(balance>=0){
                continue;
            }
            double interest = balance * rate;
            // update customer account
            accountModel.updateBalance(account.getAid(), balance + interest);
            Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), account.getAid(), Transaction.TransType.INTEREST,
                    account.getCurrency(), interest, 0, "loan interests added", LocalDateTime.now());
            transactionModel.insertTransaction(trans);
        }
        return true;
    }

    public List<Transaction> showTransactionByDay(LocalDateTime day){
        return transactionModel.queryTransactionByDay(day);
    }


}
