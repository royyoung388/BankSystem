package view;

import bean.account.Account;
import bean.user.User;
import controller.AccountOverviewController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainPage {
    private JTabbedPane tab;
    private JPanel MainPage;
    private JButton refreshButton;
    private JTable accountTable;

    private AccountOverviewController controller;
    private User user;

    public MainPage(User user) {
        JFrame frame = new JFrame("MainPage");
        frame.setContentPane(MainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.user = user;
        controller = new AccountOverviewController(user.getUid(), user.getUsername());

        updateAccount();
    }

    private void updateAccount() {
        // init account
        controller.updateAccountList();
        List<Account> accountList = controller.getAccountList();

        String[] title = {"AccountID", "Name", "Type", "Balance", "Currency"};
        TableModel dataModel = new DefaultTableModel(userToArray(accountList), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        accountTable.setModel(dataModel);
        accountTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method
                    int aid = Integer.parseInt((String) accountTable.getModel().getValueAt(row, 0));
                    System.out.println("double click: " + aid);
                    new AccountDetail(getAccount(aid));
                }
            }
        });
    }

    private Account getAccount(int aid) {
        for (Account account : controller.getAccountList())
            if (aid == account.getAid())
                return account;
        return null;
    }

    private String[][] userToArray(List<Account> accountList) {
        String[][] array = new String[accountList.size()][];
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            String[] strings = {String.valueOf(account.getAid()), account.getAccountName(), String.valueOf(account.getType()),
                    String.valueOf(account.getBalance()), String.valueOf(account.getCurrency())};
            array[i] = strings;
        }
        return array;
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
        MainPage = new JPanel();
        MainPage.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tab = new JTabbedPane();
        MainPage.add(tab, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tab.addTab("Account", panel1);
        refreshButton = new JButton();
        refreshButton.setText("Refresh");
        panel1.add(refreshButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 1, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        accountTable = new JTable();
        accountTable.setEnabled(true);
        scrollPane1.setViewportView(accountTable);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tab.addTab("Stock", panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tab.addTab("Setting", panel3);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tab.addTab("Create New Account", panel4);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPage;
    }

}


