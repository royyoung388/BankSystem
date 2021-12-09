package model;

import bean.user.Customer;

public interface AccountModel {
    /**
     *
     * @param username
     * @param pwd
     * @return null if login failed
     */
    Customer login(String username, String pwd);

    /***
     * is the user exists?
     * @param username
     * @return false: user doesn't exists.
     */
    boolean hasUser(String username);

    /***
     * create new account
     * @param username
     * @param pwd
     * @return
     */
    boolean signUp(String username, String pwd);
}
