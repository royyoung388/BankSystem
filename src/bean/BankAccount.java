package bean;

/***
 * bank account for user
 * everyone can have multiple bank account
 */
public class BankAccount {
    // bank account id
    private int baid;
    // account type: saving, checking, security
    private int type;
    private int balance;
    private String currency;

    public BankAccount(int baid, int type, int balance, String currency) {
        this.baid = baid;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
    }

    public int getBaid() {
        return baid;
    }

    public int getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
