package controller;

import bean.Collateral;
import bean.Transaction;
import bean.account.Account;
import bean.account.LoanAccount;
import model.CollateralModelImpl;

import java.time.LocalDateTime;
import java.util.List;

public class LoanAccountController extends AbstractAccountController {

    private final CollateralModelImpl collateralModel = new CollateralModelImpl();

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
        Transaction trans = new Transaction(-1, account.getUid(),  account.getAid(),-1, Transaction.TransType.LOAN,
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

}
