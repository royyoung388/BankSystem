package bean.account;

public class LoanAccount extends Account{
    public LoanAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, Type.LOAN, balance, currency);
    }
}
