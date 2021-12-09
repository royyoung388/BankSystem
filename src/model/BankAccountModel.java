package model;

import bean.account.Account;

import java.util.List;

public interface BankAccountModel {
    /***
     * create new bank account
     * @param aid  user account id
     * @param type  saving, checking, security
     * @return create failed. everyone can only have one security account
     */
    boolean createBankAccount(int aid, int type);

    /***
     * query someone's all bank account
     * @param aid
     * @return
     */
    List<Account> queryAllBankAccount(int aid);

    /***
     * query specific bank account
     * @param aid
     * @param baid
     * @return
     */
    Account queryBankAccount(int aid, int baid);

    /***
     * with draw cash from account
     * @param aid
     * @param baid
     * @param amount
     * @return
     */
    boolean withdraw(int aid, int baid, int amount);

    /***
     * saving money to account
     * @param aid
     * @param baid
     * @param amount
     * @return
     */
    boolean deposit(int aid, int baid, int amount);

    /***
     * transfer money from one bank account to another bank account
     * @param fromBAid
     * @param toBAid
     * @param amount
     * @return
     */
    boolean transfer(int fromBAid, int toBAid, int amount);

    /***
     * buy stock
     * @param aid  account id
     * @param sid  stock id
     * @param quantity
     * @return
     */
    boolean buyStock(int aid, int sid, int quantity);
}
