package com.musicshop.swing.singer;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Singer;

import javax.swing.*;
import java.awt.*;

public class ChangeSinger extends JFrame {
    private JTextField nameField;
    private JButton addButton;
    private JPanel panel1;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();

    public ChangeSinger(Singer singer, Singers singers) throws HeadlessException {
        DAO dao = new DAO();
        nameField.setText(singer.getName());
        setSize((sizeScreen.width / 3) - 100, sizeScreen.height / 3);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        addButton.addActionListener(e -> {
            try {
                singer.setName(nameField.getText());
                dao.updateSinger(singer);
                singers.update();
                this.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
