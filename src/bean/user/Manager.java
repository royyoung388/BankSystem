package bean.user;

public class Manager extends User {

    public Manager(int aid, String username, String pwd) {
        super(aid, User.MANAGER, username, pwd);
    }
}
