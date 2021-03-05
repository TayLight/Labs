package com.musicshop.swing.singer;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Singer;
import com.musicshop.entities.Song;
import com.musicshop.swing.song.AddSong;
import com.musicshop.swing.song.ChangeSong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

public class Singers extends JFrame {
    private JPanel panel1;
    private JScrollPane scrollPane;
    private JList listInScreen;
    private JButton addButton;
    private JButton deleteButton;
    private JButton changeButton;
    private JButton exitButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private LinkedList<Singer> singers;
    private Singer selected;
    private DAO dao;


    public Singers() throws HeadlessException {
        super("Песни");
        dao = new DAO();
        singers = dao.getAllSingers();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((sizeScreen.width / 2) - 100, sizeScreen.height / 2);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        update();
        setVisible(true);
        selected=null;
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
            AddSinger addSinger = new AddSinger(this);
        });
        exitButton.addActionListener(e -> {
            this.setVisible(false);
        });
        deleteButton.addActionListener(e -> {
            if(selected != null)
            {
                dao.deleteSinger(selected.getIdSinger());
                update();
            }
        });
        changeButton.addActionListener(e -> {
            if(selected != null)
            {
                ChangeSinger changeSinger = new ChangeSinger(selected, this);
            }
        });
        listInScreen.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                selected = (Singer) listInScreen.getSelectedValue();
            }
        });

    }

    public void update(){
        singers = dao.getAllSingers();
        listInScreen.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return singers.size();
            }

            @Override
            public Object getElementAt(int index) {
                return singers.get(index);
            }
        });
        listInScreen.updateUI();
    }
}
