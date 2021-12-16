package gui;

import bean.account.Account;
import bean.user.User;
import controller.AccountOverviewController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GUINewAccount extends JFrame {
    private JPanel newAccPanel;
    private JButton createAccountButton;
    private JComboBox<String> accType;
    private JComboBox<String> accCurType;
    private JTextField accBalanceInput;
    private JTextField accNameInput;
    private JPanel accTypePanel;
    private JPanel accTypeCB;
    private AccountOverviewController controller;
    private User u;

    HashMap<String, Account.AccountType> accTypeMap;

    HashMap<String, Account.CurrencyType> curTypeMap;


    public GUINewAccount(User u, GUIMain home) {
        this.u = u;
        this.controller = new AccountOverviewController(u.getUid(), u.getUsername());
        this.curTypeMap = getCurTypeMap();
        this.accTypeMap = getAccTypeMap();
        setContentPane(newAccPanel);
        createUIComponents();
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strAccBalance = accBalanceInput.getText();
                String accName = accNameInput.getText();

                if (accName.length() == 0) {
                    JOptionPane.showMessageDialog(null,
                            "Missing account name.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (strAccBalance.length() == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Missing account balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        double accBalance = Double.parseDouble(strAccBalance);
                        if (accBalance <= 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter positive amount of money as account balance.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (accType.getSelectedItem() == null || accCurType.getSelectedItem() == null) {
                                JOptionPane.showMessageDialog(null,
                                        "Missing account type or account currency selection.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                System.out.println("type");
                                System.out.println(accTypeMap.get(accType.getSelectedItem()));
                                System.out.println(accType.getSelectedItem());
                                System.out.println(curTypeMap.get(accCurType.getSelectedItem()));
                                System.out.println(accCurType.getSelectedItem());


                                Account.AccountType type = accTypeMap.get(accType.getSelectedItem());
                                Account.CurrencyType cur = curTypeMap.get(accCurType.getSelectedItem());

                                accType.addItemListener(new ItemListener()
                                {
                                    @Override
                                    public void itemStateChanged(ItemEvent e)
                                    {
                                        if(e.getID() == ItemEvent.ITEM_STATE_CHANGED)
                                        {
                                            if(e.getStateChange() == ItemEvent.SELECTED)
                                            {
                                                JComboBox<String> accType = (JComboBox<String>) e.getSource();
                                                String newSelection = (String) accType.getSelectedItem();
                                                System.out.println("newSelection: " + newSelection);
                                            }
                                        }
                                    }
                                });

                                if (!controller.addAccount(u.getUid(), type, accBalance, cur, accName)) {
                                    JOptionPane.showMessageDialog(null,
                                            "Failed to create new account.",
                                            "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
        });
        update();
        accType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == accType){
                    System.out.println(accType.getSelectedItem());
                }
            }
        });
    }


    public JPanel getNewAccPanel() {
        return newAccPanel;
    }

    public static HashMap<String, Account.AccountType> getAccTypeMap() {

        List<Account.AccountType> accountTypeList = Arrays.asList(Account.AccountType.SAVING,
                Account.AccountType.CHECKING, Account.AccountType.LOAN, Account.AccountType.SECURITY);

        HashMap<String, Account.AccountType> accTypeMap = new HashMap<>();
        for (Account.AccountType t : accountTypeList) {
            accTypeMap.put(t.toString(), t);
        }

        return accTypeMap;
    }

    public static String[] getAccTypeKeys(HashMap<String, Account.AccountType> accTypeMap) {
        return accTypeMap.keySet().toArray(new String[0]);
    }

    public static HashMap<String, Account.CurrencyType> getCurTypeMap() {

        List<Account.CurrencyType> currencyTypeList = Arrays.asList(Account.CurrencyType.USD,
                Account.CurrencyType.RMB, Account.CurrencyType.EUR);

        HashMap<String, Account.CurrencyType> curTypeMap = new HashMap<>();

        for (Account.CurrencyType c : currencyTypeList) {
            curTypeMap.put(c.toString(), c);
        }

        return curTypeMap;
    }

    public static String[] getCurTypeKeys(HashMap<String, Account.CurrencyType> curTypeMap) {
        return curTypeMap.keySet().toArray(new String[0]);
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
        newAccPanel = new JPanel();
        newAccPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        newAccPanel.setBorder(BorderFactory.createTitledBorder(null, "Create new account", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        newAccPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "Account Type", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Select Account Type: ");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accType = new JComboBox();
        panel1.add(accType, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        newAccPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(null, "Account Currency", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label2 = new JLabel();
        label2.setText("Select Account Currency: ");
        panel2.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accCurType = new JComboBox();
        panel2.add(accCurType, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        newAccPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(null, "Account balance", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label3 = new JLabel();
        label3.setText("Account Balance: ");
        panel3.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accBalanceInput = new JTextField();
        panel3.add(accBalanceInput, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        newAccPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(null, "Account Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label4 = new JLabel();
        label4.setText("Account Name: ");
        panel4.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accNameInput = new JTextField();
        panel4.add(accNameInput, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        newAccPanel.add(createAccountButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */


    public void update() {
        setContentPane(newAccPanel);
        this.controller = new AccountOverviewController(u.getUid(), u.getUsername());
        this.curTypeMap = getCurTypeMap();
        this.accTypeMap = getAccTypeMap();
        createUIComponents();
//        this.accTypeInput = new JComboBox<String>();
//        for (String s : getAccTypeKeys(accTypeMap)){
//            System.out.println(s);
//            accTypeInput.addItem(s);
//        }
//        this.accCurType = new JComboBox<>(getCurTypeKeys(curTypeMap));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        accType = new JComboBox<>();
        accTypeMap = getAccTypeMap();
        this.accType.setModel(new DefaultComboBoxModel<String>(getAccTypeKeys(accTypeMap)));

        accCurType = new JComboBox<>();
        curTypeMap = getCurTypeMap();
        this.accCurType.setModel(new DefaultComboBoxModel<String>(getCurTypeKeys(curTypeMap)));
    }
}
