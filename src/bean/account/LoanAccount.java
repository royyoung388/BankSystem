package bean.account;

import bean.Collateral;
import model.CollateralModelImpl;

import java.util.List;

public class LoanAccount extends Account {




    private List<Collateral> collateralList;
    CollateralModelImpl collateralModel=new CollateralModelImpl();

    public LoanAccount(int aid, int uid, double balance, CurrencyType currency, String Name) {
        super(aid, uid, AccountType.LOAN, balance, currency, Name);
    }

    public LoanAccount(int aid, int uid, double balance, String currency, String name) {
        super(aid, uid, AccountType.LOAN, balance, currency, name);
    }

    public List<Collateral> getCollateralList() {

        return collateralList;
    }

    public void updateList(){
        collateralList=collateralModel.queryAllCollateral(uid);
    }
}
