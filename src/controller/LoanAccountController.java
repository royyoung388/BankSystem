package controller;

import bean.Collateral;
import bean.account.Account;
import bean.account.LoanAccount;
import model.CollateralModelImpl;

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


        return true;
    }

    public boolean removeCollateralList(Collateral collateral) {

        return true;
    }

    public boolean getLoan(double amount) {

        return true;

    }

    public boolean payBack(double amount) {


        return true;
    }


}
