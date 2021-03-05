package com.musicshop.swing.albom;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Albom;
import com.musicshop.entities.Singer;
import com.musicshop.swing.singer.Singers;

import javax.swing.*;
import java.awt.*;

public class AddAlbom extends  JFrame {
    private JPanel panel1;
    private JTextField nameField;
    private JTextField genreField;
    private JButton addButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();

    public AddAlbom(Alboms alboms) throws HeadlessException {
        DAO dao = new DAO();
        setSize((sizeScreen.width / 3) - 100, sizeScreen.height / 3);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        addButton.addActionListener(e -> {
            try {
                Albom albom = new Albom();
                albom.setTitleAlbom(nameField.getText());
                albom.setGenre(genreField.getText());
                dao.addAlbom(albom);
                alboms.update();
                this.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
