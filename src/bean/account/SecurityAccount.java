package bean.account;

import bean.Stock;
import bean.StockHolding;
import model.StockHoldingModel;
import model.StockHoldingModelImpl;
import model.StockMarketModelImpl;

import java.util.ArrayList;
import java.util.List;

public class SecurityAccount extends Account {


    private List<StockHolding> stockHoldingList;
    private final StockHoldingModelImpl stockHoldingModel=new StockHoldingModelImpl();
    private List<Stock> stockMarketList;
    private final StockMarketModelImpl stockMarketModel=new StockMarketModelImpl();

    public SecurityAccount(int aid, int uid, double balance, CurrencyType currency,String Name) {
        super(aid, uid, AccountType.SECURITY, balance, currency,Name);
    }

    public SecurityAccount(int aid, int uid, double balance, String currency,String Name) {
        super(aid, uid, AccountType.SECURITY, balance, currency,Name);
    }

    public void updateHoldingList(){
        stockHoldingList=stockHoldingModel.queryAll(getUid());
    }

    public List<StockHolding> getStockHoldingList(){
        return stockHoldingList;
    }

    public void updateStockMarketList(){
        stockMarketList=stockMarketModel.queryMarket();
    }

    public List<Stock> getStockMarketList() {
        return stockMarketList;
    }
}
