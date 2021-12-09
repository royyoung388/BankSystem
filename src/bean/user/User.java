package bean.user;

public abstract class User {
    public static final int MANAGER = 0;
    public static final int CUSTOMER = 1;

    // account id
    private final int uid;
    // type: 0 for manager, 1 for customer
    private int type;
    private String username, pwd;

    public User(int uid, int type, String username, String pwd) {
        this.uid = uid;
        this.type = type;
        this.username = username;
        this.pwd = pwd;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", type=" + type +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
