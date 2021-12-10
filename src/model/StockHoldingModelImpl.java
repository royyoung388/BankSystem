package model;

import bean.StockHolding;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockHoldingModelImpl implements StockHoldingModel {
    @Override
    public List<StockHolding> queryAll(int uid) {
        List<StockHolding> holdings = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM holding WHERE uid=" + uid);
            while (rs.next()) {
                holdings.add(new StockHolding(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
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
            String sql = String.format("SELECT * FROM holding WHERE uid=%d AND sid=%d", uid, sid);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                holding = new StockHolding(rs.getInt(1), rs.getInt(2), rs.getInt(3));
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
                        holding.getUid(), holding.getSid(), holding.getQuantity());
            } else {
                sql = String.format("DELETE FROM holding WHERE uid=%d AND sid=%d", holding.getUid(), holding.getSid());
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
        if (holding != null)
            holding.setQuantity(holding.getQuantity() + quantity);
        else
            holding = new StockHolding(uid, sid, quantity);

        return updateHolding(holding);
    }

    @Override
    public boolean sellStock(int uid, int sid, int quantity) {
        StockHolding holding = queryHolding(uid, sid);
        if (holding != null)
            holding.setQuantity(holding.getQuantity() - quantity);
        else
            return false;

        return updateHolding(holding);
    }
}
