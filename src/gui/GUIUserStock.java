package gui;

import bean.Collateral;
import bean.Stock;
import bean.StockHolding;
import bean.account.Account;
import bean.account.SecurityAccount;
import bean.user.User;
import controller.AccountOverviewController;
import controller.ManagerController;
import controller.SecurityAccountController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIUserStock extends JFrame {
    private JComboBox stockInput;
    private JButton buyButton;
    private JButton sellButton;
    private JTextField stockQtyInput;
    private JComboBox secAccInput;
    private JButton selectButton;
    private JPanel stockPanel;
    private JPanel secAccPanel;
    private JPanel tradePanel;
    private JScrollPane holdPanel;
    private JLabel notAvail;
    private User user;
    private SecurityAccount securityAccount;
    private ArrayList<SecurityAccount> secAccounts;
    private AccountOverviewController controller;
    private List<StockHolding> userStocks;
    private List<Stock> stocks;
    private Stock stock;


    private HashMap<String, SecurityAccount> secAccMap;
    private HashMap<String, Stock> stockMap;

    public GUIUserStock(User u) {
        setContentPane(stockPanel);
        this.user = u;
        this.controller = new AccountOverviewController(u.getUid(), u.getUsername());
        this.secAccounts = new ArrayList<>();
        this.stocks = getStocks();

        // todo add stock
        for (Stock s : stocks) {
            stockInput.addItem(s.toString());
        }

        List<Account> accounts = controller.getAccountList();

        for (Account a : accounts) {
            if (a.getType() == Account.AccountType.SECURITY) {
                secAccounts.add((SecurityAccount) a);
                // todo add security account
                securityAccount = (SecurityAccount) a;
                secAccInput.addItem(securityAccount.getAccountName() + "(" + securityAccount.getAid() + ")");
            }
        }

        if (secAccounts.size() == 0) {
            notAvail.setVisible(true);
            secAccPanel.setVisible(false);
            tradePanel.setVisible(false);
            holdPanel.setVisible(false);
        } else {
            notAvail.setVisible(false);
            this.secAccMap = getSecAccMap(u);
            this.stockMap = getStockMap();

            secAccInput.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (secAccInput.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null,
                                "Missing security account selection.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //todo: get account
                        SecurityAccount s = secAccMap.get(secAccInput.getSelectedItem());
                        updateSecAcc(s);
                    }
                }
            });

            stockInput.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stockInput.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null,
                                "Missing stock selection.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // todo: get stock
                        Stock s = stockMap.get(stockInput.getSelectedItem());
                        updateStock(s);
                    }
                }
            });

            //todo: buy
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String strStockQty = stockQtyInput.getText();
                    if (strStockQty == null) {
                        JOptionPane.showMessageDialog(null,
                                "Missing stock quantity.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Integer stockQty = Integer.parseInt(strStockQty);
                        if (stockQty <= 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter positive stock quantity.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            SecurityAccountController controller = new SecurityAccountController(securityAccount);
                            if (controller.buyStock(stock.getSid(), stockQty)) {
                                controller.updateList();
                                JOptionPane.showMessageDialog(null,
                                        "Stock buy success.",
                                        "Success", JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Stock buy failed.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }

                }
            });

            // todo: sell
            sellButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String strStockQty = stockQtyInput.getText();
                    if (strStockQty == null) {
                        JOptionPane.showMessageDialog(null,
                                "Missing stock quantity.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Integer stockQty = Integer.parseInt(strStockQty);
                        if (stockQty <= 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter positive stock quantity.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            SecurityAccountController controller = new SecurityAccountController(securityAccount);
                            if (controller.sellStock(stock.getSid(), stockQty)) {
                                controller.updateList();
                                JOptionPane.showMessageDialog(null,
                                        "Stock sell success.",
                                        "Success", JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Stock sell failed.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            });
            JPanel stockList = new JPanel();
            SecurityAccountController secController = new SecurityAccountController(securityAccount);
            userStocks = secController.getStockHolding();

            stockList.setLayout(new GridLayout(userStocks.size(), 1));
            for (StockHolding s : userStocks) {
                GUIStockHolding stock = new GUIStockHolding(s, stocks);
                stockList.add(stock.getStockHoldPanel());
            }
            this.holdPanel = new JScrollPane(stockList);
        }


    }

    public void updateSecAcc(SecurityAccount s) {
        this.securityAccount = s;
    }

    public void updateStock(Stock s) {
        this.stock = s;
    }

    public void update() {
        setContentPane(stockPanel);
        this.controller = new AccountOverviewController(user.getUid(), user.getUsername());
        this.secAccounts = new ArrayList<>();
        this.stocks = getStocks();
        List<Account> accounts = controller.getAccountList();
        for (Account a : accounts) {
            if (a.getType() == Account.AccountType.SECURITY) {
                secAccounts.add((SecurityAccount) a);
            }
        }
        if (secAccounts.size() == 0) {
            notAvail.setVisible(true);
            secAccPanel.setVisible(false);
            tradePanel.setVisible(false);
            holdPanel.setVisible(false);
        } else {
            notAvail.setVisible(false);
            this.secAccMap = getSecAccMap(user);
            this.stockMap = getStockMap();
            JPanel stockList = new JPanel();
            SecurityAccountController secController = new SecurityAccountController(this.securityAccount);
            userStocks = secController.getStockHolding();
            stockList.setLayout(new GridLayout(userStocks.size(), 1));
            for (StockHolding s : userStocks) {
                GUIStockHolding stock = new GUIStockHolding(s, stocks);
                stockList.add(stock.getStockHoldPanel());
            }
            this.holdPanel = new JScrollPane(stockList);
        }
    }

    public static HashMap<String, SecurityAccount> getSecAccMap(User u) {
        AccountOverviewController controller = new AccountOverviewController(u.getUid(), u.getUsername());
        ArrayList<SecurityAccount> secAccounts = new ArrayList<>();

        List<Account> accounts = controller.getAccountList();

        for (Account a : accounts) {
            if (a.getType() == Account.AccountType.SECURITY) {
                secAccounts.add((SecurityAccount) a);
            }
        }

        HashMap<String, SecurityAccount> secAccMap = new HashMap<>();
        for (SecurityAccount s : secAccounts) {
            secAccMap.put(s.toString(), s);
        }
        return secAccMap;
    }

    public static String[] getSecAccKeys(HashMap<String, SecurityAccount> secAccMap) {
        return secAccMap.keySet().toArray(new String[0]);
    }

    public static HashMap<String, Stock> getStockMap() {
        ManagerController controller = new ManagerController();

        List<Stock> stocks = controller.getStockList();

        HashMap<String, Stock> stockMap = new HashMap<>();
        for (Stock h : stocks) {
            stockMap.put(h.toString(), h);
        }
        return stockMap;
    }

    public static List<Stock> getStocks() {
        ManagerController controller = new ManagerController();
        return controller.getStockList();
    }

    public static String[] getStockMap(HashMap<String, Stock> stockMap) {
        return stockMap.keySet().toArray(new String[0]);
    }

    public JPanel getStockPanel() {
        return stockPanel;
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
        stockPanel = new JPanel();
        stockPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        stockPanel.setBorder(BorderFactory.createTitledBorder(null, "Stock", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tradePanel = new JPanel();
        tradePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        stockPanel.add(tradePanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(270, 74), null, 0, false));
        tradePanel.setBorder(BorderFactory.createTitledBorder(null, "Stock Trade", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Choose Stock: ");
        tradePanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stockInput = new JComboBox();
        tradePanel.add(stockInput, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Quantity");
        tradePanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stockQtyInput = new JTextField();
        stockQtyInput.setText("");
        tradePanel.add(stockQtyInput, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buyButton = new JButton();
        buyButton.setText("Buy");
        tradePanel.add(buyButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sellButton = new JButton();
        sellButton.setText("Sell");
        tradePanel.add(sellButton, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secAccPanel = new JPanel();
        secAccPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        stockPanel.add(secAccPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        secAccPanel.setBorder(BorderFactory.createTitledBorder(null, "Security Account: ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label3 = new JLabel();
        label3.setText("Choose Security Account to trade: ");
        secAccPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secAccInput = new JComboBox();
        secAccPanel.add(secAccInput, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        holdPanel = new JScrollPane();
        stockPanel.add(holdPanel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        holdPanel.setBorder(BorderFactory.createTitledBorder(null, "Stock Hold", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        notAvail = new JLabel();
        notAvail.setText("No available Security Account to trade stock.");
        notAvail.setVisible(false);
        stockPanel.add(notAvail, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return stockPanel;
    }
}


