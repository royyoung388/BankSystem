package bean;

/***
 * login account
 */
public class Account {
    // account id
    private final int aid;
    private String username, pwd;

    public Account(int aid, String username, String pwd) {
        this.aid = aid;
        this.username = username;
        this.pwd = pwd;
    }

    public int getAid() {
        return aid;
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
