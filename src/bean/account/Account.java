package bean.account;

/***
 * bank account for user
 * everyone can have multiple bank account
 */
public abstract class Account {
    // account id
    private int aid;
    // user id
    private int uid;
    // account type: saving, checking, security
    private Type type;
    private int balance;
    private String currency;

    public Account(int aid, int uid, Type type, int balance, String currency) {
        this.aid = aid;
        this.uid = uid;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
    }

    public int getAid() {
        return aid;
    }

    public int getUid() {
        return uid;
    }

    public Type getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public enum Type {
        SAVING, CHECKING, SECURITY, LOAN
    }
}
