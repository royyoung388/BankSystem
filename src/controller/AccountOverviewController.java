package controller;

import bean.Transaction;
import bean.account.Account;
import bean.account.LoanAccount;
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

    public LoanAccount getLoanAccount() {
        for (Account account : accountList) {
            if (account.getType() == Account.AccountType.LOAN)
                return (LoanAccount) account;
        }
        return null;
    }

//    public boolean createAccount(int uid, Account.AccountType type, double balance, Account.CurrencyType currency, String accountName) {
//        return accountModel.createAccount(uid, accountName, type, balance, currency);
//    }

    public boolean addAccount(int uid, Account.AccountType type, double balance, Account.CurrencyType currency, String accountName) {
        if (balance < 10) {
            return false;
        }
        updateAccountList();
        if (type == Account.AccountType.SECURITY) {
            for (Account account: accountList
                 ) {
                if(account.getType()==Account.AccountType.SECURITY){
                    return false;
                }
            }
            if (currency != Account.CurrencyType.USD) {
                return false;
            }
            if (balance < 1010) {
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
        if (type == Account.AccountType.LOAN ) {
            if(currency!= Account.CurrencyType.USD){
                return false;
            }
            for (Account account: accountList
            ) {
                System.out.println(account.getType());
                if(account.getType()==Account.AccountType.LOAN){
                    return false;
                }
            }
            return false;
        }
        balance = balance - 10;//fee
        if (accountModel.createAccount(uid, accountName, type, balance, currency)) {
            updateAccountList();
            int accountID = accountModel.getLastInsertAccount();
            boolean transSuc = TransactionController.openAccountTransaction(uid, accountModel.getLastInsertAccount(), balance, currency.toString());
            if (!transSuc) {
                System.out.println("fail to write trans");
            }
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
