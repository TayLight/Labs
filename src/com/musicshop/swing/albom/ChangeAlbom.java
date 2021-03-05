package com.musicshop.swing.albom;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Albom;

import javax.swing.*;
import java.awt.*;

public class ChangeAlbom extends JFrame{
    private JPanel panel1;
    private JTextField nameField;
    private JTextField genreField;
    private JButton addButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();

    public ChangeAlbom(Albom albom,Alboms alboms) throws HeadlessException {
        DAO dao = new DAO();
        setSize((sizeScreen.width / 3) - 100, sizeScreen.height / 3);
        nameField.setText(albom.getTitleAlbom());
        genreField.setText(albom.getGenre());
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        addButton.addActionListener(e -> {
            try {
                albom.setTitleAlbom(nameField.getText());
                albom.setGenre(genreField.getText());
                dao.updateAlbom(albom);
                alboms.update();
                this.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
