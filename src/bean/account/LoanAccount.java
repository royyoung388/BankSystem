package bean.account;

public class LoanAccount extends Account {
    public LoanAccount(int aid, int uid, double balance, CurrencyType currency, String Name) {
        super(aid, uid, AccountType.LOAN, balance, currency, Name);
    }

    public LoanAccount(int aid, int uid, double balance, String currency, String name) {
        super(aid, uid, AccountType.LOAN, balance, currency, name);
    }
}
