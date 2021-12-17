package controller;

import bean.Transaction;
import model.TransactionModelImpl;

import java.time.LocalDateTime;

public class TransactionController {
    private static TransactionModelImpl transactionModel = new TransactionModelImpl();

    public static boolean duoSideTransaction(int fromUid, int toUid, int fromAid, int toAid, String transType, String currency, double amount, double fee, String detail, LocalDateTime time) {
        Transaction transaction1 = new Transaction(-1, fromUid, fromAid, toAid, transType, currency, amount, fee, detail, time);
        Transaction transaction2 = new Transaction(-1, toUid, fromAid, toAid, transType, currency, amount, fee, detail, time);
        return transactionModel.insertTransaction(transaction1) && transactionModel.insertTransaction(transaction2);
    }


    public static boolean transactionWithManager(int fromUid, int fromAid, int toAid, String transType, String currency, double amount, double fee, String detail,LocalDateTime time){

        return false;
    }


}
