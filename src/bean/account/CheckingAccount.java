package bean.account;

public class CheckingAccount extends Account {

    public CheckingAccount(int aid, int uid, String accountName, double balance, CurrencyType currency) {
        super(aid, uid, accountName, AccountType.CHECKING, balance, currency);
    }

    public CheckingAccount(int aid, int uid, String accountName, double balance, String currency) {
        super(aid, uid, accountName, AccountType.CHECKING, balance, currency);
    }
}
