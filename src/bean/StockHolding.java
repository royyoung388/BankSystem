package bean;

/**
 * record the stock quantity for everyone held
 */
public class StockHolding {
    // who holding the stock
    private int uid;
    // stock id
    private int sid;
    private int quantity;

    public StockHolding(int uid, int sid, int quantity) {
        this.uid = uid;
        this.sid = sid;
        this.quantity = quantity;
    }

    public int getUid() {
        return uid;
    }

    public int getSid() {
        return sid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockHolding{" +
                "uid=" + uid +
                ", sid=" + sid +
                ", quantity=" + quantity +
                '}';
    }
}

