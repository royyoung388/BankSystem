package model;

import bean.Account;

public class AccountModelImpl implements AccountModel{
    @Override
    public Account login(String username, String pwd) {
        return null;
    }

    @Override
    public boolean hasUser(String username) {
        return false;
    }

    @Override
    public boolean signUp(String username, String pwd) {
        return false;
    }
}
