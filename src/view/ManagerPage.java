package view;

import bean.Stock;
import bean.Transaction;
import bean.user.Customer;
import bean.user.User;
import controller.AccountOverviewController;
import controller.ManagerController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManagerPage {
    private JTable userTable;
    private JTable transactionTable;
    private JTextField textField1;
    private JButton queryButton;
    private JButton calculateInterestButton;
    private JTable bankTable;
    private JTable stockTable;
    private JPanel manager;
    private JButton updateButton;
    private JTextField nameText;
    private JTextField priceText;
    private JTextField quantityText;
    private JButton addStockBt;

    private ManagerController managerController;
    private AccountOverviewController accountOverviewController;

    public ManagerPage(User user) {
        JFrame frame = new JFrame("ManagerPage");
        frame.setContentPane(manager);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        managerController = new ManagerController();
        accountOverviewController = new AccountOverviewController(user.getUid());

        // show user
        updateUser();

        // show transaction
        updateTransaction();

        // show stock
        updateStock();
    }

    private void updateStock() {
        String[] title = {"StockID", "Name", "Price", "Quantity"};
        TableModel dataModel = new DefaultTableModel(stockToArray(managerController.getStockList()), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        stockTable.setModel(dataModel);

        // update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Stock> stockList = arrayToStock(stockTable.getModel());
                for (Stock stock : stockList) {
                    managerController.updateStock(stock);
                }
            }
        });

        // add new stock
        addStockBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText().strip();
                String price = priceText.getText().strip();
                String quantity = quantityText.getText().strip();

                if (name.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Empty name, price or quantity",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                managerController.addStock(name, Double.parseDouble(price), Integer.parseInt(quantity));
                updateStock();
            }
        });

        // show bank account

    }

    private List<Stock> arrayToStock(TableModel model) {
        List<Stock> stockList = new ArrayList<>(model.getRowCount());
        for (int i = 0; i < model.getRowCount(); i++) {
            stockList.add(new Stock((int) model.getValueAt(i, 0), (String) model.getValueAt(i, 1),
                    (double) model.getValueAt(i, 2), (int) model.getValueAt(i, 3)));
        }
        return stockList;
    }

    private String[][] stockToArray(List<Stock> stockList) {
        String[][] array = new String[stockList.size()][];
        for (int i = 0; i < stockList.size(); i++) {
            Stock stock = stockList.get(i);
            String[] strings = {String.valueOf(stock.getSid()), stock.getName(),
                    String.valueOf(stock.getPrice()), String.valueOf(stock.getQuantity())};
            array[i] = strings;
        }
        return array;
    }

    private void updateTransaction() {
        String[] title = {"TransactionID", "UserID", "FromAid", "ToAid", "Type", "Currency", "Amount", "Fee", "Detail", "Time"};
        TableModel dataModel = new DefaultTableModel(transactionToArray(managerController.showAllTransaction()), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        transactionTable.setModel(dataModel);
    }

    private String[][] transactionToArray(List<Transaction> transactions) {
        String[][] array = new String[transactions.size()][];
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            String[] strings = {String.valueOf(transaction.getTid()), String.valueOf(transaction.getUid()),
                    String.valueOf(transaction.getFromAid()), String.valueOf(transaction.getToAid()),
                    String.valueOf(transaction.getType()), String.valueOf(transaction.getCurrencyType()),
                    String.valueOf(transaction.getAmount()), String.valueOf(transaction.getFee()),
                    transaction.getDetail(), transaction.getTime().format(DateTimeFormatter.ISO_DATE)};
            array[i] = strings;
        }
        return array;
    }

    private void updateUser() {
        String[] title = {"UserID", "Username", "Password"};
        TableModel dataModel = new DefaultTableModel(userToArray(managerController.showCustomers()), title) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable.setModel(dataModel);
        userTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    // your valueChanged overridden method
                    int uid = Integer.parseInt((String) userTable.getModel().getValueAt(row, 0));
                    String username = (String) userTable.getModel().getValueAt(row, 1);
                    System.out.println("double click: " + uid);
                    new AccountList(new Customer(uid, username, null));
                }
            }
        });
    }

    private String[][] userToArray(List<Customer> userList) {
        String[][] array = new String[userList.size()][];
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String[] strings = {String.valueOf(user.getUid()), user.getUsername(), user.getPwd()};
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
        manager = new JPanel();
        manager.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JTabbedPane tabbedPane1 = new JTabbedPane();
        manager.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("User", panel1);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        userTable = new JTable();
        scrollPane1.setViewportView(userTable);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Transaction", panel2);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel2.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        transactionTable = new JTable();
        scrollPane2.setViewportView(transactionTable);
        final JLabel label1 = new JLabel();
        label1.setText("Date");
        panel2.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        panel2.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        queryButton = new JButton();
        queryButton.setText("Query");
        panel2.add(queryButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Stock", panel3);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel3.add(scrollPane3, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        stockTable = new JTable();
        scrollPane3.setViewportView(stockTable);
        final JLabel label2 = new JLabel();
        label2.setText("Stock name");
        panel3.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Price");
        panel3.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Quantity");
        panel3.add(label4, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Update stock");
        panel3.add(updateButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameText = new JTextField();
        panel3.add(nameText, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        priceText = new JTextField();
        panel3.add(priceText, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        quantityText = new JTextField();
        panel3.add(quantityText, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addStockBt = new JButton();
        addStockBt.setText("Add new Stock");
        panel3.add(addStockBt, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Interest", panel4);
        calculateInterestButton = new JButton();
        calculateInterestButton.setText("Calculate Interest");
        panel4.add(calculateInterestButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane4 = new JScrollPane();
        panel4.add(scrollPane4, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bankTable = new JTable();
        scrollPane4.setViewportView(bankTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return manager;
    }

}
