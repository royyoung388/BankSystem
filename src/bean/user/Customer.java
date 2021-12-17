package bean.user;

/***
 * login account
 */
public class Customer extends User {

    public Customer(int uid, String username, String pwd) {
        super(uid, User.CUSTOMER, username, pwd);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
