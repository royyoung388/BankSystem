package model;

import bean.account.Account;

import java.util.List;

public interface AccountModel {
    /***
     * create new bank account
     * everyone can only have one security account
     * @param uid  user id
     * @param type  saving, checking, security
     * @param currency  currency type
     * @return
     */
    boolean createAccount(int uid, Account.AccountType type, double balance,Account.CurrencyType currency,String accountName);

    /***
     * query someone's all bank account
     * @param uid  user id
     * @return
     */
    List<Account> queryAllAccount(int uid);

    /***
     * query specific bank account
     * @param aid
     * @return
     */
    Account queryAccount(int aid);

    /***
     * update balance. Be used at {@link AccountModel#withdraw} and {@link AccountModel#deposit}
     * @param aid
     * @param balance
     * @return
     */
    boolean updateBalance(int aid, double balance);

    /***
     * with draw cash from account
     * @param aid
     * @param amount
     * @return
     */
    boolean withdraw(int aid, double amount);

    /***
     * saving money to account
     * @param aid
     * @param amount
     * @return
     */
    boolean deposit(int aid, double amount);

    /***
     * transfer money from one bank account to another bank account
     * @param fromAid
     * @param toAid
     * @param amount
     * @return
     */
    boolean transfer(int fromAid, int toAid, double amount);
}
