package gui;

import bean.account.Account;
import bean.user.User;
import controller.AccountOverviewController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUIAllAccounts extends JFrame {
    CardLayout cards;
    User user;
    AccountOverviewController accountOverviewController;
    GUIHomePage home;
    JPanel mainPanel, accountsPanel;

    public GUIAllAccounts(User u, GUIHomePage home) {
        this.home = home;
        mainPanel = new JPanel();
        cards = new CardLayout();
        mainPanel.setLayout(cards);
        this.user = u;
        this.accountOverviewController = new AccountOverviewController(u.getUid(), u.getUsername());
        update();
    }

    public void update() {
        mainPanel.removeAll();
        List<Account> accounts = accountOverviewController.getAccountList();

        accountsPanel = new JPanel();
        accountsPanel.setLayout(new GridLayout(accounts.size(), 1));

        for (int i = 0; i < accounts.size(); i++) {
            Account a = accounts.get(i);
            JButton aBtn = new JButton(a.toString());
            accountsPanel.add(aBtn);

            GUIAccount acc = new GUIAccount(a, this);
            mainPanel.add(acc.getMainPanel(), i);

            acc.getGoBackButton().addActionListener(e -> cards.show(mainPanel, "All Accounts"));

            acc.getDeleteAccountButton().addActionListener(e -> {
                // only delete account when balance == 0
                if (a.getBalance() != 0) {
                    JOptionPane.showMessageDialog(null, "Cannot delete account with nonzero balance.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    accountOverviewController.deleteAccount(a.getAid());
                    accountsPanel.remove(aBtn);
                    cards.show(mainPanel, "All Accounts");
                    home.update();
                }
            });

            aBtn.addActionListener(e -> cards.show(mainPanel, String.valueOf(a.getAid())));
        }

        mainPanel.add(accountsPanel, "All Accounts");
        cards.show(mainPanel, "All Accounts");
    }

    public CardLayout getCards() {
        return cards;
    }

    public JPanel getMainPanel() {
        return mainPanel;
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
        mainPanel.setLayout(new CardLayout(0, 0));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
