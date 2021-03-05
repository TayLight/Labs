package com.musicshop.swing.song;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Song;

import javax.swing.*;
import java.awt.*;

public class ChangeSong extends JFrame {
    private JPanel panel1;
    private JTextField nameField;
    private JSpinner durationField;
    private JSpinner idSinger;
    private JSpinner idAlbom;
    private JLabel nameLabel;
    private JLabel durationLabel;
    private JLabel idSingerLabel;
    private JLabel idAlbomLabel;
    private JButton changeButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();

    public ChangeSong(Songs songs, Song song) throws HeadlessException {
        DAO dao = new DAO();
        durationField.setModel(new SpinnerNumberModel(song.getDuration(), 0 , 1000, 1));
        idAlbom.setModel(new SpinnerNumberModel(song.getIdAlbom(),0,1000,1));
        idSinger.setModel(new SpinnerNumberModel(song.getIdSinger(),0,1000,1));
        nameField.setText(song.getTitleSong());
        setSize((sizeScreen.width / 3) - 100, sizeScreen.height / 3);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        changeButton.addActionListener(e -> {
            try {
                song.setTitleSong(nameField.getText());
                song.setDuration((Integer) durationField.getValue());
                song.setIdSinger((Integer) idSinger.getValue());
                song.setIdAlbom((Integer) idAlbom.getValue());
                dao.updateSong(song);
                songs.update();
                this.setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
