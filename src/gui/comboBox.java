package gui;

import javax.swing.*;
import java.util.List;
//
public class comboBox{

//    private JPanel mainPanel;
    private JComboBox comboBox;

    public comboBox(List<String> strs){
        for (String s : strs){
            this.comboBox.addItem(s);
        }
    }

//    public JPanel getPanel(){
//
//        setCotentPane(new Cmbbox().mainPanel) if (mainPanel == null) {
//            mainPanel = new JPanel();
//        }
//    }


}


