package bean;

/**
 * stock market
 */
public class Stock {
    // stock id
    private int sid;
    // stock name
    private String name;
    private double price;
    private int quantity;

    public Stock(int sid, String sname, double price, int quantity) {
        this.sid = sid;
        this.name = sname;
        this.price = price;
        this.quantity = quantity;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
