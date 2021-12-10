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
    private AccountType type;
    private double balance;
    private CurrencyType currency;
    private String accountName;

    public Account(int aid, int uid, AccountType type, double balance, CurrencyType currency,
                   String accountName) {
        this.aid = aid;
        this.uid = uid;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
        this.accountName = accountName;
    }

    public Account(int aid, int uid, AccountType type, double balance, String currency,
                   String accountName) {
        this(aid, uid, type, balance, CurrencyType.valueOf(currency), accountName);
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public int getAid() {
        return aid;
    }

    public int getUid() {
        return uid;
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
