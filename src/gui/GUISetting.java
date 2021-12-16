package gui;

import bean.user.User;
import controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISetting extends JFrame {
    private JLabel curUserName;
    private JTextField usernameInput;
    private JLabel curUserNameTxt;
    private JLabel curPwdTxt;
    private JButton change1;
    private JButton submit1;
    private JButton change2;
    private JButton submit2;
    private JPanel setPanel;
    private JPasswordField pwdInput;
    private JLabel curPwd;
    private User u;
    private UserController controller;


    public GUISetting(User u) {
        this.setContentPane(setPanel);
        this.u = u;
        this.controller = new UserController();

        curUserName.setText(u.getUsername());
        curPwd.setText(u.getPwd());
        usernameInput.setVisible(false);
        submit1.setVisible(false);
        pwdInput.setVisible(false);
        submit2.setVisible(false);

        change1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameInput.setVisible(true);
                change1.setVisible(false);
                submit1.setVisible(true);
            }
        });

        submit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUserName = usernameInput.getText();
                if (newUserName != "") {
                    String old = u.getUsername();
                    u.setUsername(newUserName);
                    if (controller.updateUser(u)) {
                        curUserName.setText(newUserName);
                        controller.updateUser(u);
                    } else {
                        u.setUsername(old);
                        JOptionPane.showMessageDialog(null, "User name already exists.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Empty user name.", "Error", JOptionPane.WARNING_MESSAGE);
                }
                curUserNameTxt.setVisible(true);
                usernameInput.setVisible(false);
                change1.setVisible(true);
                submit1.setVisible(false);
            }
        });

        change2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pwdInput.setVisible(true);
                change2.setVisible(false);
                submit2.setVisible(true);
            }
        });

        submit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPwd = String.valueOf(pwdInput.getPassword());
                if (newPwd != "") {
                    String old = u.getPwd();
                    u.setPwd(newPwd);
                    if (controller.updateUser(u)) {
                        curPwd.setText(newPwd);
                        controller.updateUser(u);
                    } else {
                        u.setPwd(old);
                        JOptionPane.showMessageDialog(null, "Update password failed.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Empty password.", "Error", JOptionPane.WARNING_MESSAGE);
                }
                curPwdTxt.setVisible(true);
                pwdInput.setVisible(false);
                change2.setVisible(true);
                submit2.setVisible(false);
            }
        });

    }

    public void update() {
        this.setContentPane(setPanel);

        curUserName.setText(u.getUsername());
        curPwd.setText(u.getPwd());
        usernameInput.setVisible(false);
        submit1.setVisible(false);
        pwdInput.setVisible(false);
        submit2.setVisible(false);
    }


    public JPanel getSetPanel() {
        return setPanel;
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
        setPanel = new JPanel();
        setPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        curUserNameTxt = new JLabel();
        curUserNameTxt.setText("Currect username");
        setPanel.add(curUserNameTxt, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(110, 156), null, 0, false));
        curUserName = new JLabel();
        curUserName.setText("n");
        setPanel.add(curUserName, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        curPwdTxt = new JLabel();
        curPwdTxt.setText("Currect Password");
        setPanel.add(curPwdTxt, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 4, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        curPwd = new JLabel();
        curPwd.setText("p");
        setPanel.add(curPwd, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        change1 = new JButton();
        change1.setActionCommand("Button");
        change1.setText("Change");
        setPanel.add(change1, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameInput = new JTextField();
        setPanel.add(usernameInput, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        submit1 = new JButton();
        submit1.setText("Submit");
        setPanel.add(submit1, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        change2 = new JButton();
        change2.setText("Change");
        setPanel.add(change2, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submit2 = new JButton();
        submit2.setText("Submit");
        setPanel.add(submit2, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pwdInput = new JPasswordField();
        pwdInput.setText("");
        setPanel.add(pwdInput, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return setPanel;
    }


}
