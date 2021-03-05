package com.musicshop.swing.song;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Song;

import javax.swing.*;
import java.awt.*;

public class AddSong extends JFrame{
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private JPanel panel1;
    private JTextField nameField;
    private JSpinner idSinger;
    private JSpinner idAlbom;
    private JLabel nameLabel;
    private JLabel durationLabel;
    private JLabel idSingerLabel;
    private JLabel idAlbomLabel;
    private JSpinner durationField;
    private JButton addButton;
    private Songs songs;

    public AddSong(Songs songs) throws HeadlessException {
        DAO dao = new DAO();
        durationField.setModel(new SpinnerNumberModel(0, 0 , 1000, 1));
        idAlbom.setModel(new SpinnerNumberModel(0,0,1000,1));
        idSinger.setModel(new SpinnerNumberModel(0,0,1000,1));
        setSize((sizeScreen.width / 3) - 100, sizeScreen.height / 3);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        addButton.addActionListener(e -> {
            try {
                Song song = new Song();
                song.setTitleSong(nameField.getText());
                song.setDuration((Integer) durationField.getValue());
                song.setIdSinger((Integer) idSinger.getValue());
                song.setIdAlbom((Integer) idAlbom.getValue());
                dao.addSong(song);
                songs.update();
                this.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }
}
