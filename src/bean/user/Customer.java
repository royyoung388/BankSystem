package bean.user;

/***
 * login account
 */
public class Customer extends User {

    public Customer(int aid, String username, String pwd) {
        super(aid, User.USER, username, pwd);
    }
}
