package bean.account;

public class SecurityAccount extends Account {








    public SecurityAccount(int aid, int uid, double balance, CurrencyType currency,String Name) {
        super(aid, uid, AccountType.SECURITY, balance, currency,Name);
    }

    public SecurityAccount(int aid, int uid, double balance, String currency,String Name) {
        super(aid, uid, AccountType.SECURITY, balance, currency,Name);
    }
}
