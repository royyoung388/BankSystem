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
    private String userName;
    private List<Account> accountList;
    private AccountModel accountModel;

    public AccountOverviewController(int uID, String userName) {
        this.uID = uID;
        this.userName = userName;
        accountModel = new AccountModelImpl();
        updateAccountList();
    }

    public int getuID() {
        return uID;
    }

    public String getUserName() {
        return userName;
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
        balance=balance-10;//fee
        if (accountModel.createAccount(uid, accountName, type, balance, currency)) {
            updateAccountList();
            int accountID=accountModel.getLastInsertAccount();
            boolean transSuc=TransactionController.openAccountTransaction(uid,accountModel.getLastInsertAccount(),balance,currency.toString());
            if (!transSuc){
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
