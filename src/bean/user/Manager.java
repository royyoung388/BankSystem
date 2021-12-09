package bean.user;

public class Manager extends User {

    public Manager(int uid, String username, String pwd) {
        super(uid, User.MANAGER, username, pwd);
    }
}
