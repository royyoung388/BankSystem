package controller;

import bean.Stock;
import bean.StockHolding;
import bean.Transaction;
import bean.account.SecurityAccount;
import model.StockHoldingModelImpl;
import model.StockMarketModelImpl;

import java.time.LocalDateTime;
import java.util.List;

public class SecurityAccountController extends AbstractAccountController {

    private StockMarketModelImpl stockMarketModel = new StockMarketModelImpl();

    public SecurityAccountController(SecurityAccount securityAccount) {
        super(securityAccount);
    }

    public List<StockHolding> getStockHolding() {
        ((SecurityAccount) account).updateHoldingList();
        return ((SecurityAccount) account).getStockHoldingList();
    }

    public boolean buyStock(int stockID, int quantity) {
        double before = account.getBalance();
        Stock stock = stockMarketModel.queryStock(stockID);
        if (stock.getQuantity() < quantity) {
            return false;
        }
        boolean status = stockMarketModel.buyStock(account.getAid(), stockID, quantity);
        if (!status) {
            return false;
        }
        double after = account.getBalance();
        Transaction trans = new Transaction(-1, account.getUid(), account.getAid(), -2,
                Transaction.TransType.BUY_STOCK, account.getCurrency(), before - after, 0,
                "Buying " + stock.getName() + " : " + quantity, LocalDateTime.now());
        transactionModel.insertTransaction(trans);
        updateList();
        return true;
    }

    public boolean sellStock(int stockID, int quantity) {
        StockHoldingModelImpl stockHoldingModel = new StockHoldingModelImpl();
        StockHolding stockHolding = stockHoldingModel.queryHolding(account.getUid(), stockID);
        Stock stock = stockMarketModel.queryStock(stockID);
        if (stockHolding.getQuantity() < quantity) {
            return false;
        }
        double before = account.getBalance();
        boolean status = stockMarketModel.sellStock(account.getAid(), stockID, quantity);
        if (!status) {
            return false;
        }
        double after = account.getBalance();
        Transaction trans = new Transaction(-1, account.getUid(), -2, account.getAid(),
                Transaction.TransType.SELL_STOCK, account.getCurrency(), after - before, 0,
                "Selling " + stock.getName() + " : " + quantity, LocalDateTime.now());
        transactionModel.insertTransaction(trans);
        updateList();
        return true;
    }

    public void updateList() {
        ((SecurityAccount) account).updateStockMarketList();
        ((SecurityAccount) account).updateHoldingList();
    }

    public boolean withdraw(double amount) {
        return super.withdraw(amount, amount * WITHDRAW_RATE);
    }

    public boolean deposit(double amount) {
        return super.deposit(amount, 0);
    }

    public boolean transfer(int toID, double amount) {
        return transfer(toID, amount, 0);
    }

}
