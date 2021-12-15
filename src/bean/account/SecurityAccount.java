package bean.account;

import bean.Stock;
import bean.StockHolding;
import model.StockHoldingModelImpl;
import model.StockMarketModelImpl;

import java.util.List;

public class SecurityAccount extends Account {


    private List<StockHolding> stockHoldingList;
    private final StockHoldingModelImpl stockHoldingModel = new StockHoldingModelImpl();
    private List<Stock> stockMarketList;
    private final StockMarketModelImpl stockMarketModel = new StockMarketModelImpl();

    public SecurityAccount(int aid, int uid, String accountName, double balance, CurrencyType currency) {
        super(aid, uid, accountName, AccountType.SECURITY, balance, currency);
    }

    public SecurityAccount(int aid, int uid, String accountName, double balance, String currency) {
        super(aid, uid, accountName, AccountType.SECURITY, balance, currency);
    }

    public void updateHoldingList() {
        stockHoldingList = stockHoldingModel.queryAll(getUid());
    }

    public List<StockHolding> getStockHoldingList() {
        return stockHoldingList;
    }

    public void updateStockMarketList() {
        stockMarketList = stockMarketModel.queryMarket();
    }

    public List<Stock> getStockMarketList() {
        return stockMarketList;
    }
}
