package bean.account;

public class SavingAccount extends Account {

    public SavingAccount(int aid, int uid, double balance, CurrencyType currency,String accountName) {
        super(aid, uid, AccountType.SAVING, balance, currency,accountName);
    }

    public SavingAccount(int aid, int uid, double balance, String currency,String accountName) {
        super(aid, uid, AccountType.SAVING, balance, currency,accountName);
    }


}
