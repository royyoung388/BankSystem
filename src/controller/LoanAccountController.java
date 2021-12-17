package controller;

import bean.Collateral;
import bean.Transaction;
import bean.account.Account;
import bean.account.LoanAccount;
import model.CollateralModel;
import model.CollateralModelImpl;

import java.time.LocalDateTime;
import java.util.List;

public class LoanAccountController extends AbstractAccountController {

    private final CollateralModel collateralModel = new CollateralModelImpl();

    public LoanAccountController(LoanAccount account) {
        super(account);
    }

    public List<Collateral> getCollateralList() {
        ((LoanAccount) account).updateList();
        return ((LoanAccount) account).getCollateralList();
    }

    public boolean addCollateralList(Collateral collateral) {
        return collateralModel.addCollateral(collateral);
    }

    public boolean removeCollateralList(Collateral collateral) {
        int totalValue = 0;
        for (Collateral c : ((LoanAccount) account).getCollateralList()) {
            totalValue += c.getValue();
        }
        if (totalValue - collateral.getValue() + account.getBalance() < 0) {
            return false;
        }
        return collateralModel.deleteCollateral(collateral.getcID());
    }

    // amount is positive value
    // loanAccount balance is negative value
    public boolean getLoan(double amount) {
        amount = -amount;
        int totalValue = 0;
        for (Collateral c : ((LoanAccount) account).getCollateralList()) {
            totalValue += c.getValue();
        }
        if (totalValue + account.getBalance() - amount < 0) {
            return false;
        }
        decreaseBalance(amount);
        Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), -1, Transaction.TransType.LOAN,
                account.getCurrency(), amount, 0, "", LocalDateTime.now());
        transactionModel.insertTransaction(trans);
        return true;
    }

    public boolean payBack(double amount) {
        increaseBalance(amount);
        Transaction trans = new Transaction(-1, account.getUid(), -1, account.getAid(), Transaction.TransType.REPAY,
                account.getCurrency(), amount, 0, "", LocalDateTime.now());
        transactionModel.insertTransaction(trans);
        return true;
    }

    public void updateCollateralList() {
        ((LoanAccount) account).updateList();
    }


    @Override
    public boolean deposit(double amount) {
        return false;
    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public boolean transfer(int toID, double amount) {
        Account toAccount = accountModel.queryAccount(toID);
        if (toAccount.getType() == Account.AccountType.SECURITY) {
            if (amount < 1000 || account.getBalance() - amount < 2500) {
                return false;
            }
        }
        return transfer(toID, amount, 0);
    }

    public boolean transfer(double amount){
        return false;
    }
}
