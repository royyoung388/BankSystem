package gui;

import bean.Transaction;

import javax.swing.*;
import java.awt.*;

public class GUITransaction extends JFrame {
    private JPanel transactionPanel;
    private JLabel time;
    private JLabel fee;
    private JLabel amount;
    private JLabel cur;
    private JLabel type;
    private JLabel toid;
    private JLabel fromid;
    private JLabel uid;
    private JLabel tid;

    public GUITransaction() {
        setContentPane(transactionPanel);
    }


    public GUITransaction(Transaction t) {
        setContentPane(transactionPanel);
        time.setText(String.valueOf(t.getTime()));
        fee.setText(String.valueOf(t.getFee()));
        amount.setText(String.valueOf(t.getAmount()));
        cur.setText(String.valueOf(t.getCurrencyType()));
        type.setText(String.valueOf(t.getType()));
        toid.setText(String.valueOf(t.getToAid()));
        fromid.setText(String.valueOf(t.getFromAid()));
        uid.setText(String.valueOf(t.getUid()));
        tid.setText(String.valueOf(t.getTid()));
    }

    public JPanel getTransactionPanel() {
        return transactionPanel;
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
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 9, new Insets(0, 0, 0, 0), -1, -1));
        tid = new JLabel();
        tid.setText("tid");
        transactionPanel.add(tid, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        uid = new JLabel();
        uid.setText("uid");
        transactionPanel.add(uid, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fromid = new JLabel();
        fromid.setText("fromAid");
        transactionPanel.add(fromid, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        toid = new JLabel();
        toid.setText("toAid");
        transactionPanel.add(toid, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        type = new JLabel();
        type.setText("type");
        transactionPanel.add(type, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cur = new JLabel();
        cur.setText("currency");
        transactionPanel.add(cur, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        amount = new JLabel();
        amount.setText("amount");
        transactionPanel.add(amount, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fee = new JLabel();
        fee.setText("fee");
        transactionPanel.add(fee, new com.intellij.uiDesigner.core.GridConstraints(0, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        time = new JLabel();
        time.setText("time");
        transactionPanel.add(time, new com.intellij.uiDesigner.core.GridConstraints(0, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return transactionPanel;
    }
}