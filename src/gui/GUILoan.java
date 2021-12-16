package gui;

import bean.Collateral;
import bean.account.Account;
import bean.account.LoanAccount;
import bean.user.User;
import controller.LoanAccountController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GUILoan extends JFrame {
    private JPanel loanPanel;
    private JComboBox<String> colComboBox;
    private JButton getCollatoralButton;
    private JTextField cName;
    private JTextField cPrice;
    private JButton addCollatoralButton;
    private JTextField getLoanInput;
    private JButton getLoanButton;
    private JTextField payBackInput;
    private JButton payBackButton;
    private JLabel accBalance;
    private JButton goBackButton;
    private JPanel getColBackPanel;
    private JLabel accCurType;
    private Account a;
    private User u;
    private static LoanAccountController controller;
    private HashMap<String, Collateral> colMap;

    public GUILoan(User u, Account a) {
        setContentPane(loanPanel);
        this.u = u;
        this.a = a;
        controller = new LoanAccountController((LoanAccount) a);
        // this UI will only generated when account is loan account
        this.colMap = getColMap(u);
        // get collateral back panel only visible when exists collateral
        this.accBalance.setText(String.valueOf(a.getBalance()));
        this.accCurType.setText(String.valueOf(a.getCurrency()));
//        this.colComboBox = new JComboBox<>(getColKeys(colMap));

        if (colMap.size() == 0) {
            getColBackPanel.setVisible(false);
        } else {
            getColBackPanel.setVisible(true);
        }

        getLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amt = getLoanInput.getText();
                if (amt == "") {
                    JOptionPane.showMessageDialog(null,
                            "Missing Loan input.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double loanAmount = Double.parseDouble(amt);
                    if (loanAmount <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter positive amount of money you want to get.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (!controller.getLoan(loanAmount)) {
                            JOptionPane.showMessageDialog(null,
                                    "You exceed the get loan limit.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        payBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amt = payBackInput.getText();
                if (amt == "") {
                    JOptionPane.showMessageDialog(null,
                            "Missing pay back input.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double payAmount = Double.parseDouble(amt);
                    if (payAmount < 0) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter positive amount of money you want to pay back.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        controller.payBack(payAmount);
                    }
                }
            }
        });
        addCollatoralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = cName.getText();
                String price = cPrice.getText();
                if (name == "") {
                    JOptionPane.showMessageDialog(null,
                            "Missing collateral name.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (price == "") {
                        JOptionPane.showMessageDialog(null,
                                "Missing collateral price.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        double doublePrice = Double.parseDouble(price);
                        if (doublePrice < 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter positive collateral price.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Collateral newCol = new Collateral(name, u.getUid(), doublePrice);
                            if (!controller.addCollateralList(newCol)) {
                                JOptionPane.showMessageDialog(null,
                                        "Failed to add collateral.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            controller.updateCollateralList();
                        }
                    }
                }
            }
        });
        getCollatoralButton.addActionListener(e -> {
            try {
                 if (colComboBox.getSelectedItem() == null) {
                     JOptionPane.showMessageDialog(null,
                             "Missing collateral selection.",
                             "Error", JOptionPane.ERROR_MESSAGE);
                 } else {
                     Collateral c = colMap.get(colComboBox.getSelectedItem());
                     if (!controller.removeCollateralList(c)) {
                         JOptionPane.showMessageDialog(null,
                                 "Failed to get selected collateral.", "Error", JOptionPane.ERROR_MESSAGE);
                     }
                 }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "Please choose valid collateral.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

    }

    public static HashMap<String, Collateral> getColMap(User u) {
        // create hashmap between string of collatoral and collatoral
        java.util.List<Collateral> collaterals = controller.getCollateralList();
        HashMap<String, Collateral> colMap = new HashMap<>();
        for (Collateral c : collaterals) {
            colMap.put(c.toString(), c);
        }
        return colMap;
    }

    public static String[] getColKeys(HashMap<String, Collateral> colMap) {
        return colMap.keySet().toArray(new String[0]);
    }

    public JButton getGoBackButton() {
        return goBackButton;
    }

    public JPanel getLoanPanel() {
        return this.loanPanel;
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
        loanPanel = new JPanel();
        loanPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        loanPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "Pay back", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Pay back loan value: ");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        payBackInput = new JTextField();
        panel1.add(payBackInput, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        payBackButton = new JButton();
        payBackButton.setText("Pay Back");
        panel1.add(payBackButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        getColBackPanel = new JPanel();
        getColBackPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        loanPanel.add(getColBackPanel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        getColBackPanel.setBorder(BorderFactory.createTitledBorder(null, "Get collatoral back", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        colComboBox = new JComboBox();
        getColBackPanel.add(colComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        getCollatoralButton = new JButton();
        getCollatoralButton.setText("Get Collatoral");
        getColBackPanel.add(getCollatoralButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        loanPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Balance");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accCurType = new JLabel();
        accCurType.setText("c");
        panel2.add(accCurType, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accBalance = new JLabel();
        accBalance.setText("b");
        panel2.add(accBalance, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Currency");
        panel2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        loanPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(null, "Get Loan", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        getLoanInput = new JTextField();
        panel3.add(getLoanInput, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        getLoanButton = new JButton();
        getLoanButton.setText("Get Loan");
        panel3.add(getLoanButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Required loan value: ");
        panel3.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 8, new Insets(0, 0, 0, 0), -1, -1));
        loanPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(null, "Add new collatoral", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label5 = new JLabel();
        label5.setText("Name:");
        panel4.add(label5, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cName = new JTextField();
        panel4.add(cName, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Price: ");
        panel4.add(label6, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cPrice = new JTextField();
        panel4.add(cPrice, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addCollatoralButton = new JButton();
        addCollatoralButton.setText("Add Collatoral");
        panel4.add(addCollatoralButton, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        goBackButton = new JButton();
        goBackButton.setText("Go Back");
        loanPanel.add(goBackButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return loanPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        colComboBox = new JComboBox<>();
        colMap = getColMap(this.u);
        this.colComboBox.setModel(new DefaultComboBoxModel<String>(getColKeys(colMap)));
    }
}
