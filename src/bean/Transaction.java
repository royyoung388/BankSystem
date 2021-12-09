package bean;

import bean.account.Account;

import java.time.LocalDateTime;
import java.time.ZoneId;

/***
 * transaction
 */
public class Transaction {
    // transaction id
    private int tid;
    // user id
    private int uid;
    // transaction happened from one bank account(fromAid) to another bank account(toAid)
    // toAid maybe null, depends on the transaction type
    private int fromAid, toAid;
    private TransType type;
    private Account.CurrencyType currencyType;
    private int amount, fee;
    private String detail;
    private LocalDateTime time;

    public Transaction(int tid, int uid, int fromBAid, int toBAid, TransType type, Account.CurrencyType currency, int amount, int fee, String detail, LocalDateTime time) {
        this.tid = tid;
        this.uid = uid;
        this.fromAid = fromBAid;
        this.toAid = toBAid;
        this.type = type;
        this.currencyType = currency;
        this.amount = amount;
        this.fee = fee;
        this.detail = detail;
        this.time = time;
    }

    public Transaction(int tid, int uid, int fromBAid, int toBAid, String type, String currency, int amount, int fee, String detail, LocalDateTime time) {
        this(tid, uid, fromBAid, toBAid, TransType.valueOf(type), Account.CurrencyType.valueOf(currency), amount, fee, detail, time);
    }

    public int getTid() {
        return tid;
    }

    public int getUid() {
        return uid;
    }

    public int getFromAid() {
        return fromAid;
    }

    public int getToAid() {
        return toAid;
    }

    public TransType getType() {
        return type;
    }

    public Account.CurrencyType getCurrencyType() {
        return currencyType;
    }

    public int getAmount() {
        return amount;
    }

    public int getFee() {
        return fee;
    }

    public String getDetail() {
        return detail;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public long getTimestamp() {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setFromAid(int fromAid) {
        this.fromAid = fromAid;
    }

    public void setToAid(int toAid) {
        this.toAid = toAid;
    }

    public void setType(TransType type) {
        this.type = type;
    }

    public void setCurrencyType(Account.CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public enum TransType {
        TRANSFER, WITHDRAW, DEPOSIT, LOAN, REPAY, BUY_STOCK, SELL_STOCK, INTEREST;

        @Override
        public String toString() {
            switch (this) {
                case TRANSFER:
                    return "TRANSFER";
                case WITHDRAW:
                    return "WITHDRAW";
                case DEPOSIT:
                    return "DEPOSIT";
                case LOAN:
                    return "LOAN";
                case REPAY:
                    return "REPAY";
                case BUY_STOCK:
                    return "BUY_STOCK";
                case SELL_STOCK:
                    return "SELL_STOCK";
                case INTEREST:
                    return "INTEREST";
            }
            return "";
        }
    }
}
