package model;

import bean.StockHolding;

import java.util.List;

public interface StockHoldingModel {

    /**
     * query the all stock holding for someone
     * @param uid
     * @return
     */
    List<StockHolding> queryAll(int uid);

    /**
     * query the stock holding for specific stock
     * @param uid
     * @param sid
     * @return
     */
    StockHolding queryHolding(int uid, int sid);

    /**
     * update stock holding. Be used at {@link StockMarketModel#buyStock}
     * @param holding
     * @return
     */
    boolean updateHolding(StockHolding holding);

    /**
     * buy stock, only update the holding quantity.
     * @param uid
     * @param sid
     * @param quantity
     * @return
     */
    boolean buyStock(int uid, int sid, int quantity);

    /**
     * sell stock, only update the holding quantity.
     * @param uid
     * @param sid
     * @param quantity
     * @return
     */
    boolean sellStock(int uid, int sid, int quantity);
}
