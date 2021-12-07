package bean;

import java.util.Date;

/***
 * transaction
 */
public class Transaction {
    // transaction id
    private int tid;
    // transaction happened from one bank account(fromBAid) to another bank account(toBAid)
    // toAid maybe null, depends on the transaction type
    private int fromBAid, toBAid;
    private int type, amount, fee;
    private Date date;

    public Transaction(int tid, int fromBAid, int toBAid, int type, int amount, int fee, Date date) {
        this.tid = tid;
        this.fromBAid = fromBAid;
        this.toBAid = toBAid;
        this.type = type;
        this.amount = amount;
        this.fee = fee;
        this.date = date;
    }

    public int getTid() {
        return tid;
    }

    public int getFromBAid() {
        return fromBAid;
    }

    public int getToBAid() {
        return toBAid;
    }

    public int getType() {
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
}
