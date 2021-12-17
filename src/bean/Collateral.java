package bean;

public class Collateral {
    private int cID;
    private String name;
    private double value;
    private int uID;

    public Collateral(int cID, int uID, String name, double value) {
        this.cID = cID;
        this.name = name;
        this.value = value;
        this.uID = uID;
    }

    public Collateral(String name, int uID, double value) {
        this.name = name;
        this.value = value;
        this.uID = uID;
    }

    public int getcID() {
        return cID;
    }

    public int getuID() {
        return uID;
    }


    public String getName() {
        return name;
    }


    public double getValue() {
        return value;
    }


}
