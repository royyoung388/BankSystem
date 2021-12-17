package bean;

/**
 * record the stock quantity for everyone held
 */
public class StockHolding {
    // who holding the stock
    private int uid;
    // stock id
    private Stock stock;
    private int quantity;

    public StockHolding(int uid, Stock stock, int quantity) {
        this.uid = uid;
        this.stock = stock;
        this.quantity = quantity;
    }

    public int getUid() {
        return uid;
    }

    public Stock getStock() {
        return stock;
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
                ", stock=" + stock +
                ", quantity=" + quantity +
                '}';
    }
}

