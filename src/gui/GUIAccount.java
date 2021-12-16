package gui;

import bean.Transaction;
import bean.account.Account;
import bean.user.User;
import controller.AbstractAccountController;
import controller.AccountOverviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIAccount extends JFrame {
    private Account account;
    private GUIAllAccounts home;
    private JButton goBackButton;
    private JButton deleteAccountButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton withdrawButton;
    private JPanel transactionsPanel;
    private JLabel accBalance;
    private JLabel accCurType;
    private JLabel Balance;
    private JLabel Currency;
    private JButton loanButton;
    JPanel mainPanel;
    private AbstractAccountController controller;
    private User u;
    private GUITransfer transfer;
    private GUIDeposit deposit;
    private GUILoan loan;
    private GUIWithdraw withdraw;


    public GUIAccount(Account a, GUIAllAccounts home) {
        setContentPane(mainPanel);
        this.u = u;
        this.account = a;
        this.home = home;
        controller = new AbstractAccountController(a) {
        };

        if (account.getType() == Account.AccountType.LOAN) {
            loanButton.setVisible(true);
        } else {
            loanButton.setVisible(false);
        }

        updateTransactions();

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                home.getCards().show(mainPanel, "All Accounts");
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountOverviewController overviewController = new AccountOverviewController(u.getUid(), u.getUsername());
                overviewController.deleteAccount(a.getAid());
                home.getCards().show(mainPanel, "HomePage");
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer = new GUITransfer(a);
                // display transfer panel as main panel
                JPanel homePage = newMainPanel(transfer.getTransferPanel());
                // go back
                transfer.getGoBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newMainPanel(homePage);
                        updateTransactions();
                    }
                });
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                withdraw = new GUIWithdraw(a);

                JPanel homePage = newMainPanel(withdraw.getMainPanel());

                withdraw.getGoBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newMainPanel(homePage);
                        updateTransactions();
                    }
                });

            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit = new GUIDeposit(a);

                JPanel homePage = newMainPanel(deposit.getDepositPanel());

                deposit.getGoBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newMainPanel(homePage);
                        updateTransactions();
                    }
                });
            }
        });
        loanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a.getType() == Account.AccountType.LOAN) {

                    loan = new GUILoan(u, a);

                    JPanel homePage = newMainPanel(loan.getLoanPanel());

                    loan.getGoBackButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            newMainPanel(homePage);
                            updateTransactions();
                        }
                    });
                }
            }
        });
    }

    public JPanel newMainPanel(JPanel newPanel) {
        JPanel homePage = this.mainPanel;
        this.mainPanel = newPanel;
        return homePage;
    }

    private void updateTransactions() {
        accBalance.setText(String.valueOf(account.getBalance()));
        accCurType.setText(String.valueOf(account.getCurrency()));
        java.util.List<Transaction> transactionList = controller.showTransactionByAccount();
        if (!transactionList.isEmpty()) {
            transactionsPanel.setLayout(new GridLayout(transactionList.size() + 1, 1));
            // title
            transactionsPanel.add(new GUITransaction().getTransactionPanel());
            // panels of transaction
            for (Transaction t : transactionList) {
                transactionsPanel.add(new GUITransaction(t).getTransactionPanel());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JButton getDeleteAccountButton() {
        return deleteAccountButton;
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
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 6, new Insets(0, 0, 0, 0), -1, -1));
        Balance = new JLabel();
        Balance.setText("Balance");
        mainPanel.add(Balance, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(47, 53), null, 0, false));
        transactionsPanel = new JPanel();
        transactionsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(transactionsPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 4, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        transactionsPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        transactionsPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        accBalance = new JLabel();
        accBalance.setText("b");
        mainPanel.add(accBalance, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(8, 53), null, 0, false));
        Currency = new JLabel();
        Currency.setText("Currency");
        mainPanel.add(Currency, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(56, 53), null, 0, false));
        accCurType = new JLabel();
        accCurType.setText("c");
        mainPanel.add(accCurType, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(7, 53), null, 0, false));
        goBackButton = new JButton();
        goBackButton.setText("go back");
        mainPanel.add(goBackButton, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(83, 53), null, 0, false));
        deleteAccountButton = new JButton();
        deleteAccountButton.setText("delete account");
        mainPanel.add(deleteAccountButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 53), null, 0, false));
        depositButton = new JButton();
        depositButton.setText("Deposit");
        mainPanel.add(depositButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        transferButton = new JButton();
        transferButton.setText("Transfer");
        mainPanel.add(transferButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        withdrawButton = new JButton();
        withdrawButton.setText("Withdraw");
        mainPanel.add(withdrawButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loanButton = new JButton();
        loanButton.setText("Loan");
        mainPanel.add(loanButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
