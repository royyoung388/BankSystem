package gui;

import bean.user.User;
import model.DAO;

import javax.swing.*;
import java.awt.*;

public class GUIMain extends JFrame{
    Container c;
    CardLayout cards;
    JButton logOutButton;
    DAO dao;

    public GUIMain(){
        JFrame frame = new JFrame();

        frame.setTitle("BU Bank");
        frame.setSize(500, 500);
        frame.setLocation(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = frame.getContentPane();
        cards = new CardLayout();

        c.setLayout(cards);

        displayRegistration();

        frame.setVisible(true);
    }

    private void displayHomePage(User u){
        GUIHomePage homePage = new GUIHomePage(u);

    }

    private void displayRegistration(){
        GUIRegistration reg = new GUIRegistration();
        reg.getLoginButton().addActionListener(e -> {
            User u = reg.getUser();
            if (u != null) {
                displayHomePage(u);
                cards.show(c, "HomePage");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        reg.getRegisterNewAccountButton().addActionListener(e -> {
            User u = reg.newUserRegistration();
            if (u != null) {
                displayHomePage(u);
                cards.show(c, "HomePage");
            }
            // if u is null, there already exists user u and should directly login
        });
        c.add(reg.getUserPanel(), "Registration");
    }


}