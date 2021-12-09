package bean.user;

public abstract class User {
    public static final int MANAGER = 0;
    public static final int USER = 1;

    // account id
    private final int uid, type;
    private String username, pwd;

    public User(int aid, int type, String username, String pwd) {
        this.uid = aid;
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
}
