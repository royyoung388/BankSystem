package controller;

import bean.StockHolding;
import bean.account.SecurityAccount;

import javax.print.attribute.standard.Severity;
import java.util.List;

public class SecurityAccountController extends AbstractAccountController {

    public SecurityAccountController(SecurityAccount securityAccount) {
        super(securityAccount);
    }

    public List<StockHolding> getStockHolding() {
        ((SecurityAccount) account).updateHoldingList();
        return ((SecurityAccount) account).getStockHoldingList();
    }


    public boolean buyStock(int stockID, int quantity) {

        return true;
    }

    public boolean sellStock(int stockID, int quantity) {

        return true;
    }

}
