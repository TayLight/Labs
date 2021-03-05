package com.musicshop.swing.singer;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Singer;
import com.musicshop.entities.Song;

import javax.swing.*;
import java.awt.*;

public class AddSinger extends JFrame {
    private JPanel panel1;
    private JTextField nameField;
    private JButton addButton;
    private Singer singer;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();

    public AddSinger(Singers singers) throws HeadlessException {
        DAO dao = new DAO();
        setSize((sizeScreen.width / 3) - 100, sizeScreen.height / 3);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        addButton.addActionListener(e -> {
            try {
                Singer singer = new Singer();
                singer.setName(nameField.getText());
                dao.addSinger(singer);
                singers.update();
                this.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
