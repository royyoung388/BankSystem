package view;

import bean.account.Account;
import bean.user.User;
import controller.AccountOverviewController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class AccountList {
    private JTable accountTable;
    private JPanel accountList;

    public AccountList(User user) {
        JFrame frame = new JFrame("AccountList");
        frame.setContentPane(accountList);
        frame.pack();
        frame.setVisible(true);

        AccountOverviewController controller = new AccountOverviewController(user.getUid());
        String[] title = {"AccountID", "Name", "Type", "Balance", "Currency"};
        TableModel dataModel = new DefaultTableModel(accountToArray(controller.getAccountList()), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        accountTable.setModel(dataModel);
    }

    private String[][] accountToArray(List<Account> accountList) {
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
        accountList = new JPanel();
        accountList.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        accountList.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        accountTable = new JTable();
        scrollPane1.setViewportView(accountTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return accountList;
    }
}
