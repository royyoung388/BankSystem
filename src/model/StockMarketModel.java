package model;

import bean.Stock;

import java.util.List;

public interface StockMarketModel {

    /***
     * query all stock in stock market
     * @return
     */
    List<Stock> queryMarket();

    /**
     * query specific stock
     * @param sid
     * @return
     */
    Stock queryStock(int sid);

    /***
     * update the stock information in the market
     * @param stocks
     * @return
     */
    int updateStocks(List<Stock> stocks);

    /**
     * update single stock information
     * @param stock
     * @return
     */
    boolean updateStock(Stock stock);

    /***
     * create new stocks in market
     * @param stocks
     * @return
     */
    int insertStocks(List<Stock> stocks);

    /***
     * delete stock
     * @param id
     * @return
     */
    boolean deleteStock(int id);

    /***
     * buy stock. This is the entrance for buy stock.
     * 1. update the stock quantity in market
     * 2. update account balance
     * 3. update stock holding.
     * see also: {@link StockHoldingModel#buyStock}
     * @param aid  account id, it should be security account
     * @param sid  stock id
     * @param quantity
     * @return
     */
    boolean buyStock(int aid, int sid, int quantity);

    /**
     * sell stock. This is the entrance for buy stock.
     * 1. update the stock quantity in market
     * 2. update account balance
     * 3. update stock holding.
     * see also: {@link StockHoldingModel#sellStock}
     * @param aid
     * @param sid
     * @param quantity
     * @return
     */
    boolean sellStock(int aid, int sid, int quantity);
}
