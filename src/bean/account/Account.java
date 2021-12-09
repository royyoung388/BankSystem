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

    public Account(int aid, int uid, AccountType type, double balance, CurrencyType currency) {
        this.aid = aid;
        this.uid = uid;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(int aid, int uid, AccountType type, int balance, String currency) {
        this(aid, uid, type, balance, CurrencyType.valueOf(currency));
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
}
