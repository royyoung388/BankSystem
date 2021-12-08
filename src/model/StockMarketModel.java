package model;

import bean.Stock;

import java.util.List;

public interface StockMarketModel {

    /***
     * query all stock in stock market
     * @return
     */
    List<Stock> queryMarket();

    /***
     * update the stock information in the market
     * @param stocks
     * @return
     */
    boolean updateMarket(List<Stock> stocks);
}
