package model;

import bean.Stock;
import bean.account.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockMarketModelImpl implements StockMarketModel {
    @Override
    public List<Stock> queryMarket() {
        List<Stock> stocks = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM stock");
            while (rs.next()) {
                stocks.add(new Stock(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    @Override
    public Stock queryStock(int sid) {
        Stock stock = null;
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM stock WHERE sid=" + sid);
            if (rs.next()) {
                stock = new Stock(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }


    @Override
    public int updateStocks(List<Stock> stocks) {
        int count = 0;
        for (Stock stock : stocks) {
            if (updateStock(stock))
                count++;
        }
        return count;
    }

    @Override
    public boolean updateStock(Stock stock) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("UPDATE stock SET name=%s, price=%f, quantity=%d WHERE sid=%d",
                    stock.getName(), stock.getPrice(), stock.getQuantity(), stock.getSid());
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int insertStocks(List<Stock> stocks) {
        int count = 0;
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            for (Stock stock : stocks) {
                String sql = String.format("INSERT INTO stock VALUES (%d, %s, %f, %d)",
                        stock.getSid(), stock.getName(), stock.getPrice(), stock.getQuantity());
                count += statement.executeUpdate(sql);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public boolean buyStock(int aid, int sid, int quantity) {
        AccountModel accountModel = new AccountModelImpl();
        StockHoldingModel holdingModel = new StockHoldingModelImpl();
        Account account = accountModel.queryAccount(aid);
        Stock stock = queryStock(sid);

        if (account == null || stock == null || account.getType() != Account.AccountType.SECURITY)
            return false;

        // 1. update stock quantity in market
        stock.setQuantity(stock.getQuantity() - quantity);
        boolean result = updateStock(stock);
        if (!result)
            return false;
        // 2. update account balance
        result = accountModel.updateBalance(account.getAid(), account.getBalance() - stock.getPrice() * quantity);
        if (!result)
            return false;
        // 3. update stock holding
        result = holdingModel.buyStock(account.getUid(), sid, quantity);
        return result;
    }

    @Override
    public boolean sellStock(int aid, int sid, int quantity) {
        AccountModel accountModel = new AccountModelImpl();
        StockHoldingModel holdingModel = new StockHoldingModelImpl();
        Account account = accountModel.queryAccount(aid);
        Stock stock = queryStock(sid);

        if (account == null || stock == null || account.getType() != Account.AccountType.SECURITY)
            return false;

        // 1. update stock quantity in market
        stock.setQuantity(stock.getQuantity() + quantity);
        boolean result = updateStock(stock);
        if (!result)
            return false;
        // 2. update account balance
        result = accountModel.updateBalance(account.getAid(), account.getBalance() + stock.getPrice() * quantity);
        if (!result)
            return false;
        // 3. update stock holding
        result = holdingModel.sellStock(account.getUid(), sid, quantity);
        return result;
    }


}
