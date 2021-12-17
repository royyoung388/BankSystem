package controller;

import bean.Transaction;
import bean.account.Account;
import bean.account.SecurityAccount;
import model.AccountModel;
import model.AccountModelImpl;
import model.TransactionModelImpl;

import java.time.LocalDateTime;
import java.util.List;

public class AccountOverviewController {
    private int uID;
    private List<Account> accountList;
    private AccountModel accountModel;

    public AccountOverviewController(int uID) {
        this.uID = uID;
        accountModel = new AccountModelImpl();
        updateAccountList();
    }

    public int getuID() {
        return uID;
    }


    public List<Account> getAccountList() {
        return accountList;
    }

    public SecurityAccount getSecurityAccount() {
        for (Account account : accountList) {
            if (account.getType() == Account.AccountType.SECURITY)
                return (SecurityAccount) account;
        }
        return null;
    }

//    public boolean createAccount(int uid, Account.AccountType type, double balance, Account.CurrencyType currency, String accountName) {
//        return accountModel.createAccount(uid, accountName, type, balance, currency);
//    }

    public boolean addAccount(int uid, Account.AccountType type, double balance, Account.CurrencyType currency, String accountName) {
        if (type == Account.AccountType.SECURITY) {
            if (currency != Account.CurrencyType.USD) {
                return false;
            }
            int totalValue = 0;
            for (Account a : accountList) {
                if (a.getType() == Account.AccountType.SAVING && a.getCurrency() == Account.CurrencyType.USD) {
                    totalValue += a.getBalance();
                }
            }
            if (totalValue < 5000) {
                return false;
            }
        }

        if (accountModel.createAccount(uid, accountName, type, balance, currency)) {
            updateAccountList();
            int accountID=accountModel.getLastInsertAccount();
//            TransactionModelImpl transactionModel=new TransactionModelImpl();
//            Transaction trans = new Transaction(-1, uid, account.getAid(), -1, Transaction.TransType.WITHDRAW,
//                    account.getCurrency(), amount, fee, "withdraw: " + amount, LocalDateTime.now());
//            transactionModel.insertTransaction(trans);


            return true;
        }
        return false;
    }

    public boolean deleteAccount(int aid) {
        // already check account balance = 0, just remove it from database
        return accountModel.deleteAccount(aid);
    }

    public void updateAccountList() {
        accountList = accountModel.queryAllAccount(uID);
    }


}
