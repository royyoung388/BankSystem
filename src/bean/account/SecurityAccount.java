package bean.account;

public class SecurityAccount extends Account {
    public SecurityAccount(int aid, int uid, int balance, CurrencyType currency) {
        super(aid, uid, AccountType.SECURITY, balance, currency);
    }

    public SecurityAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, AccountType.SECURITY, balance, currency);
    }
}
