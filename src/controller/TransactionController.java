package controller;

import bean.Transaction;
import bean.account.Account;
import bean.user.Manager;
import model.AccountModelImpl;
import model.TransactionModelImpl;

import java.time.LocalDateTime;

public class TransactionController {
    private static TransactionModelImpl transactionModel = new TransactionModelImpl();

    private static AccountModelImpl accountModel=new AccountModelImpl();

    public static boolean duoSideTransaction( int fromAid, int toAid, String transType, String currency, double amount, double fee, String detail, LocalDateTime time) {
        int fromUid=accountModel.queryAccount(fromAid).getUid();
        int toUid=accountModel.queryAccount(toAid).getUid();
        Transaction transaction1 = new Transaction(-1, fromUid, fromAid, toAid, transType, currency, amount, fee, detail, time);
        if(fromUid!=toUid){
            Transaction transaction2 = new Transaction(-1, toUid, fromAid, toAid, transType, currency, amount, fee, detail, time);
            return transactionModel.insertTransaction(transaction1) && transactionModel.insertTransaction(transaction2);
        }
        return transactionModel.insertTransaction(transaction1);

    }


    public static boolean transactionWithManager(int fromUid, int fromAid, int toAid, String transType, String currency, double amount, double fee, String detail,LocalDateTime time){




        return false;
    }

    public static boolean openAccountTransaction(int uid, int fromAid,double amount,String currency){
        int toAid;
        if(currency.equals(Account.CurrencyType.USD.toString())){
            toAid= Manager.USD_ACCOUNT_ID;
        }else if(currency.equals(Account.CurrencyType.RMB.toString())){
            toAid=Manager.RMB_ACCOUNT_ID;
        }else {
            toAid=Manager.EUR_ACCOUNT_ID;
        }
        Transaction transaction1 = new Transaction(-1, 1,fromAid, toAid, Transaction.TransType.FEE.toString(), currency, 10, 0, "Open new account fee "+fromAid,  LocalDateTime.now());
        Transaction transaction2=new Transaction(-1,uid,-1,fromAid,Transaction.TransType.DEPOSIT.toString(),currency,amount,10,"open account deposit "+amount+" fee "+ "10.0",LocalDateTime.now());
        return transactionModel.insertTransaction(transaction1) && transactionModel.insertTransaction(transaction2);
    }


}
