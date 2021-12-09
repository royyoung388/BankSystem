package model;

import bean.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TransactionModelImpl implements TransactionModel {
    @Override
    public boolean insertTransaction(Transaction transaction) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("INSERT INTO txn VALUES (%d, %d, %d, %d, %s, %s, %d, %d, %s, %d)",
                    transaction.getTid(), transaction.getUid(), transaction.getFromAid(), transaction.getToAid(),
                    transaction.getType(), transaction.getCurrencyType(), transaction.getAmount(),
                    transaction.getFee(), transaction.getDetail(), transaction.getTimestamp());
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Transaction> queryTransactionByUser(int uid) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM txn WHERE uid=" + uid);
            while (rs.next()) {
                LocalDateTime time = Instant.ofEpochMilli(rs.getLong(10)).atZone(ZoneId.systemDefault()).toLocalDateTime();
                transactions.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getString(9), time));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public List<Transaction> queryTransactionByDay(LocalDateTime time) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            long timestamp = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
            long timestamp2 = time.plusDays(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;

            String sql = String.format("SELECT * FROM txn WHERE time>=%d AND time<=%d", timestamp, timestamp2);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                LocalDateTime localDateTime = Instant.ofEpochMilli(rs.getLong(10)).atZone(ZoneId.systemDefault()).toLocalDateTime();
                transactions.add(new Transaction(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6),
                        rs.getInt(7), rs.getInt(8), rs.getString(9), localDateTime));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
