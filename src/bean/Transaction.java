package bean;

import java.util.Date;

/***
 * transaction
 */
public class Transaction {
    // transaction id
    private int tid;
    // transaction happened from one bank account(fromAid) to another bank account(toAid)
    // toAid maybe null, depends on the transaction type
    private int fromAid, toAid;
    private TransType type;
    private int amount, fee;
    private String note;
    private Date date;

    public Transaction(int tid, int fromBAid, int toBAid, TransType type, int amount, int fee, String note, Date date) {
        this.tid = tid;
        this.fromAid = fromBAid;
        this.toAid = toBAid;
        this.type = type;
        this.amount = amount;
        this.fee = fee;
        this.note = note;
        this.date = date;
    }

    public int getTid() {
        return tid;
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

    public int getAmount() {
        return amount;
    }

    public int getFee() {
        return fee;
    }

    public Date getDate() {
        return date;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;
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
            return super.toString();
        }
    }
}
