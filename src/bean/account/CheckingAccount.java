package bean.account;

import bean.account.Account;

public class CheckingAccount extends Account {

    public CheckingAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, Type.CHECKING, balance, currency);
    }
}
