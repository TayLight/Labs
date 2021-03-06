package com.musicshop.swing;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Song;
import com.musicshop.swing.song.AddSong;
import com.musicshop.swing.song.ChangeSong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.LinkedList;

public class Task extends JFrame {
    private JPanel panel1;
    private JScrollPane scrollPane;
    private JList listInScreen;
    private JButton exitButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private LinkedList<Song> songs;
    private Song selected;
    private DAO dao;

    public Task() throws HeadlessException {
        super("Скрипт");
        try {
            dao = new DAO();
            songs = dao.taskTwo();
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
            exitButton.addActionListener(e -> {
                this.setVisible(false);
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
            songs = dao.taskTwo();
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
