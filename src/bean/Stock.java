package bean;

/**
 * stock market
 */
public class Stock {
    // stock id
    private int sid;
    // stock name
    private String sname;
    private int price, quantity;

    public Stock(int sid, String sname, int price, int quantity) {
        this.sid = sid;
        this.sname = sname;
        this.price = price;
        this.quantity = quantity;
    }

    public int getSid() {
        return sid;
    }

    public String getSname() {
        return sname;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
