package bean.user;

public class Manager extends User {

    public static final int USD_ACCOUNT_ID=1;
    public static final int RMB_ACCOUNT_ID=2;
    public static final int EUR_ACCOUNT_ID=3;

    public Manager(int uid, String username, String pwd) {
        super(uid, User.MANAGER, username, pwd);
    }
}
