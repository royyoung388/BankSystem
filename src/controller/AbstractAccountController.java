package controller;

import bean.Transaction;
import bean.account.Account;
import bean.user.Manager;
import model.AccountModel;
import model.AccountModelImpl;
import model.TransactionModel;
import model.TransactionModelImpl;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractAccountController implements AccountControllerInterface{

    public static final double WITHDRAW_RATE = 0.01;
    public static final double CHECKING_RATE = 0.01;


    protected Account account;
    protected AccountModel accountModel = new AccountModelImpl();
    protected TransactionModel transactionModel = new TransactionModelImpl();

    public AbstractAccountController(Account account) {
        this.account = account;
    }

    public boolean increaseBalance(double amount) {
        double balance = account.getBalance();
        balance += amount;
        account.setBalance(balance);
        accountModel.updateBalance(account.getAid(), balance);
        return true;
    }

    public boolean decreaseBalance(double amount) {
        double balance = account.getBalance();
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        account.setBalance(balance);
        accountModel.updateBalance(account.getAid(), balance);
        return true;
    }

    public boolean withdraw(double amount, double fee) {
        boolean status = decreaseBalance(amount + fee);
        if (status) {
            Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), -1, Transaction.TransType.WITHDRAW,
                    account.getCurrency(), amount, fee, "withdraw: " + amount, LocalDateTime.now());
            transactionModel.insertTransaction(trans);
            if (fee > 0) {
                int toid;
                if (account.getCurrency() == Account.CurrencyType.USD) {
                    toid = Manager.USD_ACCOUNT_ID;
                } else if (account.getCurrency() == Account.CurrencyType.RMB) {
                    toid = Manager.RMB_ACCOUNT_ID;
                } else {
                    toid = Manager.EUR_ACCOUNT_ID;
                }
                Transaction managerIncome = new Transaction(-1, 1, account.getAid(), toid, Transaction.TransType.FEE,
                        account.getCurrency(), fee, 0, "withdraw fee income " + fee, LocalDateTime.now());
                transactionModel.insertTransaction(managerIncome);
            }
        }
        return status;
    }

    public boolean deposit(double amount, double fee) {
        boolean status = increaseBalance(amount - fee);
        if (status) {
            Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), -1, Transaction.TransType.DEPOSIT,
                    account.getCurrency(), amount, fee, "", LocalDateTime.now());
            transactionModel.insertTransaction(trans);
            if (fee > 0) {
                int toid;
                if (account.getCurrency() == Account.CurrencyType.USD) {
                    toid = Manager.USD_ACCOUNT_ID;
                } else if (account.getCurrency() == Account.CurrencyType.RMB) {
                    toid = Manager.RMB_ACCOUNT_ID;
                } else {
                    toid = Manager.EUR_ACCOUNT_ID;
                }
                Transaction managerIncome = new Transaction(-1, 1, account.getAid(), toid, Transaction.TransType.FEE,
                        account.getCurrency(), fee, 0, "deposit fee income " + fee, LocalDateTime.now());
                transactionModel.insertTransaction(managerIncome);
            }
        }
        return status;
    }


    public boolean transfer(int toID, double amount, double fee) {
        Account toAccount = accountModel.queryAccount(toID);
        if (toAccount == null) {
            return false;
        }
        if (toAccount.getCurrency() != account.getCurrency()) {
            return false;
        }
        if (account.getBalance() < amount + fee) {
            return false;
        }
        decreaseBalance(amount + fee);
        accountModel.deposit(toID, amount);
        Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), toID, Transaction.TransType.TRANSFER,
                account.getCurrency(), amount, fee, "", LocalDateTime.now());
        transactionModel.insertTransaction(trans);
        if (fee > 0) {
            int toid;
            if (account.getCurrency() == Account.CurrencyType.USD) {
                toid = Manager.USD_ACCOUNT_ID;
            } else if (account.getCurrency() == Account.CurrencyType.RMB) {
                toid = Manager.RMB_ACCOUNT_ID;
            } else {
                toid = Manager.EUR_ACCOUNT_ID;
            }
            Transaction managerIncome = new Transaction(-1, 1, account.getAid(), toid, Transaction.TransType.FEE,
                    account.getCurrency(), fee, 0, "transfer fee income " + fee, LocalDateTime.now());
            transactionModel.insertTransaction(managerIncome);
        }
        return true;
    }

    public List<Transaction> showTransactionByTime(LocalDateTime startTime, LocalDateTime endTime) {
        return transactionModel.queryTransactionByAccountAndTime(startTime, endTime, account.getAid(), account.getUid());
    }

    public List<Transaction> showTransactionByAccount() {
        return transactionModel.queryTransactionByAccount(account.getAid(), account.getUid());
    }


}
