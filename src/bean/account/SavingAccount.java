package bean.account;

public class SavingAccount extends Account {

    public SavingAccount(int aid, int uid, int balance, CurrencyType currency) {
        super(aid, uid, AccountType.SAVING, balance, currency);
    }

    public SavingAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, AccountType.SAVING, balance, currency);
    }
}
