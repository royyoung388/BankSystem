package gui;

import bean.user.Manager;
import bean.user.User;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUIHomePage extends JFrame {
    private JTabbedPane userTabs;
    private JTabbedPane managerTabs;
    private JPanel mainPanel;
    private JButton logOutButton;

    private User user;

    GUIUserStock stock;
    GUIAllAccounts allAccounts;
    GUINewAccount newAccount;
    GUISetting setting;

    // visible when user is manager
    GUIUserOverView users;
    GUITransactionOverView transactions;
    GUIStockManagement stockManagement;

    public GUIHomePage(User u, GUIMain home) {
        setContentPane(this.mainPanel);

        this.user = u;
        this.managerTabs = new JTabbedPane();

        if (u instanceof Manager) {
            userTabs.addTab("Manager", managerTabs);

            allAccounts = new GUIAllAccounts(u, this);
            userTabs.addTab("All Accounts", allAccounts.getMainPanel());

            setting = new GUISetting(u);
            userTabs.addTab("Settings", setting.getContentPane());

            users = new GUIUserOverView(home);
            managerTabs.addTab("Users Overview", users.getUsersPanel());

            transactions = new GUITransactionOverView(home);
            managerTabs.addTab("Transactions", transactions.getTransactionsPanel());

            stockManagement = new GUIStockManagement(home);
            managerTabs.addTab("Stock Management", stockManagement.getStocksPanel());


        } else {

            allAccounts = new GUIAllAccounts(u, this);
            userTabs.addTab("All Accounts", allAccounts.getMainPanel());

            stock = new GUIUserStock(u);
            userTabs.addTab("Stock", stock.getStockPanel());

            setting = new GUISetting(u);
            userTabs.addTab("Settings", setting.getContentPane());

            newAccount = new GUINewAccount(u, home);
            userTabs.addTab("Create New Account", newAccount.getNewAccPanel());

            userTabs.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    System.out.println("change tab");
                    allAccounts.update();

                    System.out.println("after event");
                }
            });
        }

    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public void update() {
        allAccounts.update();
        stock.update();
        setting.update();
        newAccount.update();
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        userTabs = new JTabbedPane();
        userTabs.setOpaque(false);
        userTabs.setVisible(true);
        mainPanel.add(userTabs, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        logOutButton = new JButton();
        logOutButton.setAutoscrolls(false);
        logOutButton.setBorderPainted(true);
        logOutButton.setText("Log out");
        mainPanel.add(logOutButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
