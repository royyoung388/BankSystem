package controller;

import bean.account.Account;
import model.AccountModelImpl;

import java.util.List;

public class AccountOverviewController {
    private int uID;
    private String userName;
    private List<Account> accountList;
    private AccountModelImpl accountModel;

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

    public boolean addAccount(Account account) {
        if (account.getType() == Account.AccountType.SECURITY) {
            if (account.getCurrency() != Account.CurrencyType.USD) {
                return false;
            }
            int totalValue = 0;
            for (Account a : accountList) {
                if (a.getType() == Account.AccountType.SAVING && a.getCurrency() == Account.CurrencyType.USD) {
                    totalValue += a.getBalance();
                }
            }
            if(totalValue<5000) {
                return false;
            }
        }
        if (accountModel.createAccount(account.getUid(), account.getType(), account.getBalance(), account.getCurrency(), account.getAccountName())) {
            updateAccountList();
            return true;
        }
        return false;
    }


    private void updateAccountList() {
        accountList = accountModel.queryAllAccount(uID);
    }

}
