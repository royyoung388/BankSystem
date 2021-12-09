package model;

import bean.Transaction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TransactionModel {
    /**
     * insert a transaction record
     * @param transaction
     * @return
     */
    boolean insertTransaction(Transaction transaction);

    /**
     * query transaction
     * @param uid  user id
     * @return
     */
    List<Transaction> queryTransactionByUser(int uid);


    /***
     * query transaction on some day.
     * @param time
     * @return
     */
    List<Transaction> queryTransactionByDay(LocalDateTime time);
}
