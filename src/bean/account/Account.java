package bean.account;

/***
 * bank account for user
 * everyone can have multiple bank account
 */
public abstract class Account {
    // account id
    protected int aid;
    // user id
    protected int uid;
    protected String accountName;
    // account type: saving, checking, security
    protected AccountType type;
    protected double balance;
    protected CurrencyType currency;
    // account status: 1 open. 0 closed.
//    protected int status;

    public Account(int aid, int uid, String accountName, AccountType type, double balance, CurrencyType currency) {
        this.aid = aid;
        this.uid = uid;
        this.accountName = accountName;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(int aid, int uid, String accountName, AccountType type, double balance, String currency) {
        this(aid, uid, accountName, type, balance, CurrencyType.valueOf(currency));
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public int getAid() {
        return aid;
    }

    public int getUid() {
        return uid;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountType getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public enum AccountType {
        SAVING, CHECKING, SECURITY, LOAN;

        @Override
        public String toString() {
            switch (this) {
                case SAVING:
                    return "SAVING";
                case CHECKING:
                    return "CHECKING";
                case SECURITY:
                    return "SECURITY";
                case LOAN:
                    return "LOAN";
            }
            return "";
        }
    }

    public enum CurrencyType {
        USD, RMB, EUR;

        @Override
        public String toString() {
            switch (this) {
                case USD:
                    return "USD";
                case RMB:
                    return "RMB";
                case EUR:
                    return "EUR";
            }
            return "";
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid = " + aid +
                ", uid = " + uid +
                ", name = " + accountName +
                ", type = " + type +
                ", balance = " + balance +
                ", currency = " + currency +
                '}';
    }
}
