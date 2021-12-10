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
        accountModel= new AccountModelImpl();
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

    public boolean addAccount(Account account){
        if(accountModel.createAccount(account.getUid(),account.getType(),account.getCurrency(),account.getAccountName())){
            updateAccountList();
            return true;
        }
        return false;
    }

    private void updateAccountList(){
        accountList =accountModel.queryAllAccount(uID);
    }


}
