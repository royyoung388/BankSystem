package model;

import bean.Stock;
import bean.StockHolding;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockHoldingModelImpl implements StockHoldingModel {
    private StockMarketModel marketModel;

    public StockHoldingModelImpl() {
        marketModel = new StockMarketModelImpl();
    }

    @Override
    public List<StockHolding> queryAll(int uid) {
        List<StockHolding> holdings = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("SELECT * FROM holding h, stock s WHERE uid=%d AND s.sid=h.sid", uid);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Stock stock = new Stock(rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
                holdings.add(new StockHolding(rs.getInt(1), stock, rs.getInt(3)));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holdings;
    }

    @Override
    public StockHolding queryHolding(int uid, int sid) {
        StockHolding holding = null;
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("SELECT * FROM holding h, stock s WHERE uid=%d AND h.sid=%d AND h.sid=s.sid", uid, sid);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Stock stock = new Stock(rs.getInt(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
                holding = new StockHolding(rs.getInt(1), stock, rs.getInt(3));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holding;
    }

    @Override
    public boolean updateHolding(StockHolding holding) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = null;
            if (holding.getQuantity() != 0) {
                sql = String.format("REPLACE INTO holding VALUES (%d, %d, %d)",
                        holding.getUid(), holding.getStock().getSid(), holding.getQuantity());
            } else {
                sql = String.format("DELETE FROM holding WHERE uid=%d AND sid=%d", holding.getUid(),
                        holding.getStock().getSid());
            }
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean buyStock(int uid, int sid, int quantity) {
        StockHolding holding = queryHolding(uid, sid);
        Stock stock = marketModel.queryStock(sid);
        if (holding != null)
            holding.setQuantity(holding.getQuantity() + quantity);
        else
            holding = new StockHolding(uid, stock, quantity);

        return updateHolding(holding);
    }

    @Override
    public boolean sellStock(int uid, int sid, int quantity) {
        StockHolding holding = queryHolding(uid, sid);
        if (holding != null && holding.getQuantity() >= quantity)
            holding.setQuantity(holding.getQuantity() - quantity);
        else
            return false;

        return updateHolding(holding);
    }

    public static void main(String[] args) {
        StockHoldingModel model = new StockHoldingModelImpl();
        model.buyStock(2, 1, 100);
    }
}
