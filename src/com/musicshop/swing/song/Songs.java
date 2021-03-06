package com.musicshop.swing.song;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.LinkedList;

public class Songs extends JFrame {
    private JScrollPane scrollPane;
    private JList listInScreen;
    private JButton changeButton;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel panel1;
    private JButton exitButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private LinkedList<Song> songs;
    private Song selected;
    private DAO dao;

    public Songs() throws HeadlessException {
        super("Песни");
        try {
            dao = new DAO();
            songs = dao.getAllSongs();
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize((sizeScreen.width / 2) - 100, sizeScreen.height / 2);
            setLocationRelativeTo(null);
            setContentPane(panel1);
            update();
            setVisible(true);
            selected = null;
            this.addWindowListener(new WindowListener() {

                public void windowActivated(WindowEvent event) {

                }

                public void windowClosed(WindowEvent event) {
                    setVisible(false);
                }

                public void windowClosing(WindowEvent event) {


                }

                public void windowDeactivated(WindowEvent event) {

                }

                public void windowDeiconified(WindowEvent event) {

                }

                public void windowIconified(WindowEvent event) {

                }

                public void windowOpened(WindowEvent event) {

                }

            });
            addButton.addActionListener(e -> {
                AddSong addSong = new AddSong(this);
            });
            exitButton.addActionListener(e -> {
                this.setVisible(false);
            });
            deleteButton.addActionListener(e -> {
                if (selected != null) {
                    try {
                        dao.deleteSong(selected.getIdSong());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    update();
                }
            });
            changeButton.addActionListener(e -> {
                if (selected != null) {
                    ChangeSong changeSong = new ChangeSong(this, selected);
                }
            });
            listInScreen.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    selected = (Song) listInScreen.getSelectedValue();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(){
        try {
            songs = dao.getAllSongs();
            listInScreen.setModel(new AbstractListModel() {
                @Override
                public int getSize() {
                    return songs.size();
                }

                @Override
                public Object getElementAt(int index) {
                    return songs.get(index);
                }
            });
            listInScreen.updateUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
