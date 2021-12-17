package view;

import bean.Transaction;
import bean.account.*;
import controller.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utils.Utils.transactionToArray;

public class AccountDetail {
    private JLabel currencyText;
    private JLabel balanceText;
    private JButton withdrawButton;
    private JButton depositButton;
    private JTextField withdrawInput;
    private JTextField depositInput;
    private JTable transactionTable;
    private JScrollPane scroll;
    private JPanel transaction;
    private JLabel withdrawLabel;
    private JLabel depositLabel;
    private JButton refreshButton;
    private JButton deleteBt;
    private JTextField transferInput;
    private JTextField toAidInput;
    private JButton transferButton;

    private AbstractAccountController controller;
    private AccountOverviewController overviewController;
    private Account account;

    public AccountDetail(Account account) {
        JFrame frame = new JFrame("AccountDetail");
        frame.setContentPane(transaction);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(700, 500);
        frame.setVisible(true);

        this.account = account;

        // get controller
        overviewController = new AccountOverviewController(account.getUid());
        if (account instanceof CheckingAccount) {
            controller = new CheckingAccountController((CheckingAccount) account);
        } else if (account instanceof SavingAccount) {
            controller = new SavingAccountController((SavingAccount) account);
        } else if (account instanceof LoanAccount) {
            controller = new LoanAccountController((LoanAccount) account);
        } else if (account instanceof SecurityAccount) {
            controller = new SecurityAccountController((SecurityAccount) account);
        }

        // show text
        currencyText.setText(String.valueOf(account.getCurrency()));
        updateTransaction();

        // hide withdraw and deposit: loan and security
        if (account.getType() != Account.AccountType.SAVING && account.getType() != Account.AccountType.CHECKING) {
            withdrawLabel.setVisible(false);
            withdrawInput.setVisible(false);
            withdrawButton.setVisible(false);
            depositLabel.setVisible(false);
            depositInput.setVisible(false);
            depositButton.setVisible(false);
        }

        // withdraw, only for saving and deposit
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = withdrawInput.getText().strip();
                if (amount.isEmpty())
                    return;
                double money = Double.parseDouble(amount);
                if (controller.withdraw(money))
                    updateTransaction();
                else
                    JOptionPane.showMessageDialog(null,
                            "Withdraw failed",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // deposit, only for saving and deposit
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = depositInput.getText().strip();
                if (amount.isEmpty())
                    return;
                double money = Double.parseDouble(amount);
                if (controller.deposit(money))
                    updateTransaction();
                else
                    JOptionPane.showMessageDialog(null,
                            "Deposit failed",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // transfer
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = transferInput.getText().strip();
                String toaid = toAidInput.getText().strip();
                if (amount.isEmpty() || toaid.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Empty amount or account id",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (controller.transfer(Integer.parseInt(toaid), Double.parseDouble(amount)))
                    updateTransaction();
                else
                    JOptionPane.showMessageDialog(null,
                            "Transfer failed",
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // delete
        deleteBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (account.getBalance() != 0) {
                    JOptionPane.showMessageDialog(null,
                            "Account balance is not 0!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                overviewController.deleteAccount(account.getAid());
            }
        });

        // refresh
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTransaction();
            }
        });
    }

    private void updateTransaction() {
        balanceText.setText(String.valueOf(account.getBalance()));
        // transaction
        List<Transaction> transactionList = controller.showTransactionByAccount();
        String[] title = {"ToAid", "Type", "Currency", "Amount", "Fee", "Detail", "Time"};
        TableModel dataModel = new DefaultTableModel(transactionToArray(transactionList), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionTable.setModel(dataModel);
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
        transaction = new JPanel();
        transaction.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        transaction.setBorder(BorderFactory.createTitledBorder(null, "Transaction", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Balance:");
        transaction.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        balanceText = new JLabel();
        balanceText.setText("");
        transaction.add(balanceText, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Currency: ");
        transaction.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currencyText = new JLabel();
        currencyText.setText("");
        transaction.add(currencyText, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        depositButton = new JButton();
        depositButton.setText("Deposit");
        transaction.add(depositButton, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        depositLabel = new JLabel();
        depositLabel.setText("Amount:");
        transaction.add(depositLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        depositInput = new JTextField();
        transaction.add(depositInput, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        scroll = new JScrollPane();
        transaction.add(scroll, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scroll.setBorder(BorderFactory.createTitledBorder(null, "Transaction", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        transactionTable = new JTable();
        scroll.setViewportView(transactionTable);
        refreshButton = new JButton();
        refreshButton.setText("Refresh");
        transaction.add(refreshButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteBt = new JButton();
        deleteBt.setText("Delete Account");
        transaction.add(deleteBt, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Amount");
        transaction.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        transferInput = new JTextField();
        transaction.add(transferInput, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("AccountID");
        transaction.add(label4, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toAidInput = new JTextField();
        transaction.add(toAidInput, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        transferButton = new JButton();
        transferButton.setText("Transfer");
        transaction.add(transferButton, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        withdrawInput = new JTextField();
        transaction.add(withdrawInput, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        withdrawButton = new JButton();
        withdrawButton.setText("Withdraw");
        transaction.add(withdrawButton, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        withdrawLabel = new JLabel();
        withdrawLabel.setText("Amount:");
        transaction.add(withdrawLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return transaction;
    }

}
