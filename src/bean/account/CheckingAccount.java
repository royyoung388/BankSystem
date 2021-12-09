package bean.account;

public class CheckingAccount extends Account {

    public CheckingAccount(int aid, int uid, int balance, CurrencyType currency) {
        super(aid, uid, AccountType.CHECKING, balance, currency);
    }

    public CheckingAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, AccountType.CHECKING, balance, currency);
    }
}
