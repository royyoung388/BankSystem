package bean.account;

import bean.account.Account;

public class SavingAccount extends Account {

    public SavingAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, Type.SAVING, balance, currency);
    }
}
