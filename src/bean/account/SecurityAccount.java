package bean.account;

public class SecurityAccount extends Account {
    public SecurityAccount(int aid, int uid, int balance, String currency) {
        super(aid, uid, Type.SECURITY, balance, currency);
    }
}
