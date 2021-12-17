package model;

import bean.Collateral;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CollateralModelImpl implements CollateralModel {

    @Override
    public boolean addCollateral(Collateral collateral) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("INSERT INTO collateral VALUES (NULL, %d, '%s', %.2f)",
                    collateral.getuID(), collateral.getName(), collateral.getValue());
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Collateral> queryAllCollateral(int uID) {
        List<Collateral> result = new ArrayList<>();
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM collateral where uid=" + uID);
            while (rs.next()) {
                result.add(new Collateral(rs.getInt(1), uID, rs.getString(3),
                        rs.getFloat(4)));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteCollateral(int cID) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM collateral where id=" + cID);
            statement.close();
            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
