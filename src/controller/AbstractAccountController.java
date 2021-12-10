package controller;

import bean.Transaction;
import bean.account.Account;
import model.AccountModelImpl;
import model.TransactionModelImpl;

import java.time.LocalDateTime;

public abstract class AbstractAccountController {
    protected Account account;
    protected AccountModelImpl accountModel = new AccountModelImpl();
    protected TransactionModelImpl transactionModel = new TransactionModelImpl();

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
                    account.getCurrency(), amount, fee, "", LocalDateTime.now());
            transactionModel.insertTransaction(trans);
        }
        return status;
    }

    public boolean deposit(double amount, double fee) {
        boolean status = increaseBalance(amount - fee);
        if (status) {
            Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), -1, Transaction.TransType.DEPOSIT,
                    account.getCurrency(), amount, fee, "", LocalDateTime.now());
            transactionModel.insertTransaction(trans);
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
        decreaseBalance(amount+fee);
        accountModel.deposit(toID,amount);
        Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), toID, Transaction.TransType.TRANSFER,
                account.getCurrency(), amount, fee, "", LocalDateTime.now());
        transactionModel.insertTransaction(trans);
        return true;
    }


}
