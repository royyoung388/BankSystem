package bean.account;

public class SavingAccount extends Account {

    public SavingAccount(int aid, int uid, String accountName, double balance, CurrencyType currency) {
        super(aid, uid, accountName, AccountType.SAVING, balance, currency);
    }

    public SavingAccount(int aid, int uid, String accountName, double balance, String currency) {
        super(aid, uid, accountName, AccountType.SAVING, balance, currency);
    }


}
