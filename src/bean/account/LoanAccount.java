package bean.account;

import bean.Collateral;
import model.CollateralModelImpl;

import java.util.List;

public class LoanAccount extends Account {


    private List<Collateral> collateralList;
    CollateralModelImpl collateralModel = new CollateralModelImpl();

    public LoanAccount(int aid, int uid, String accountName, double balance, CurrencyType currency) {
        super(aid, uid, accountName, AccountType.LOAN, balance, currency);
    }

    public LoanAccount(int aid, int uid, String accountName, double balance, String currency) {
        super(aid, uid, accountName, AccountType.LOAN, balance, currency);
    }

    public List<Collateral> getCollateralList() {

        return collateralList;
    }

    public void updateList() {
        collateralList = collateralModel.queryAllCollateral(uid);
    }
}
