package model;

import bean.user.Customer;
import bean.user.Manager;
import bean.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserModelImpl implements UserModel {
    @Override
    public User login(String username, String pwd) {
        User user = null;
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("SELECT * FROM user WHERE username='%s' AND pwd='%s'", username, pwd);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                int type = rs.getInt("type");
                if (type == User.MANAGER)
                    // manager
                    user = new Manager(rs.getInt(1), rs.getString(3), rs.getString(4));
                else
                    // customer
                    user = new Customer(rs.getInt(1), rs.getString(3), rs.getString(4));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean isUserExists(String username) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("SELECT * FROM user WHERE username='%s'", username);
            ResultSet rs = statement.executeQuery(sql);
            boolean result = rs.next();
            rs.close();
            statement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean signUp(int type, String username, String pwd) {
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            String sql = String.format("INSERT INTO user VALUES (NULL, %d, '%s', '%s')", type, username, pwd);
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User queryManager() {
        User manager = null;
        try {
            Statement statement = DAO.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE type=" + User.MANAGER);
            if (rs.next()) {
                manager = new Manager(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manager;
    }

    public static void main(String[] args) {
        UserModel userModel = new UserModelImpl();
        userModel.signUp(User.CUSTOMER, "test1", "test1");
        userModel.signUp(User.CUSTOMER, "test2", "test2");
        userModel.signUp(User.MANAGER, "m1", "m1");

        System.out.println(userModel.isUserExists("test1"));
        System.out.println(userModel.isUserExists("test2"));
        System.out.println(userModel.isUserExists("m1"));
        System.out.println(userModel.isUserExists("m2"));

        System.out.println(userModel.login("test1", "test1"));
        System.out.println(userModel.queryManager());
    }
}
