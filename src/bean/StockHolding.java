package bean;

/**
 * record the stock quantity for everyone held
 */
public class StockHolding {
    // who holding the stock
    private int aid;
    // stock id
    private int sid;
    private int quantity;

    public StockHolding(int aid, int sid, int quantity) {
        this.aid = aid;
        this.sid = sid;
        this.quantity = quantity;
    }

    public int getAid() {
        return aid;
    }

    public int getSid() {
        return sid;
    }

    public int getQuantity() {
        return quantity;
    }
}
