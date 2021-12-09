package model;

import bean.user.User;

public interface UserModel {
    /**
     *
     * @param username
     * @param pwd
     * @return null if login failed or not found
     */
    User login(String username, String pwd);

    /***
     * is the user exists?
     * @param username
     * @return false: user doesn't exists.
     */
    boolean isUserExists(String username);

    /***
     * create new account
     *
     * @param type
     * @param username
     * @param pwd
     * @return
     */
    boolean signUp(int type, String username, String pwd);

    /**
     * query manager
     * @return
     */
    User queryManager();
}
