package bean.account;

public class LoanAccount extends Account {
    public LoanAccount(int aid, int uid, int balance, CurrencyType currency) {
        super(aid, uid, AccountType.LOAN, balance, currency);
    }

    public LoanAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, AccountType.LOAN, balance, currency);
    }
}
