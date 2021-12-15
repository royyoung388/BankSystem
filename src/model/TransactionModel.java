package model;

import bean.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionModel {
    /**
     * insert a transaction record
     *
     * @param transaction
     * @return
     */
    boolean insertTransaction(Transaction transaction);

    /**
     * query transaction
     *
     * @param uid user id
     * @return
     */
    List<Transaction> queryTransactionByUser(int uid);


    /***
     * query transaction on some day.
     * @param time
     * @return
     */
    List<Transaction> queryTransactionByDay(LocalDateTime time);

    /***
     *
     * @param startTime
     * @param endTime
     * @param aid
     * @param uid
     * @return
     */
    List<Transaction> queryTransactionByAccountAndTime(LocalDateTime startTime, LocalDateTime endTime, int aid, int uid);

    /***
     *
     * @param aid
     * @param uid
     * @return
     */
    List<Transaction> queryTransactionByAccount(int aid, int uid);
}
