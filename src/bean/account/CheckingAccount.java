package bean.account;

public class CheckingAccount extends Account {

    public CheckingAccount(int aid, int uid, double balance, CurrencyType currency, String Name) {
        super(aid, uid, AccountType.CHECKING, balance, currency, Name);
    }

    public CheckingAccount(int aid, int uid, double balance, String currency, String Name) {
        super(aid, uid, AccountType.CHECKING, balance, currency, Name);
    }
}
