package com.musicshop.swing.albom;

import com.musicshop.databases.DAO;
import com.musicshop.entities.Albom;
import com.musicshop.entities.Singer;
import com.musicshop.swing.singer.AddSinger;
import com.musicshop.swing.singer.ChangeSinger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

public class Alboms extends JFrame {
    private JPanel panel1;
    private JScrollPane scrollPane;
    private JList listInScreen;
    private JButton addButton;
    private JButton deleteButton;
    private JButton changeButton;
    private JButton exitButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private LinkedList<Albom> alboms;
    private Albom selected;
    private DAO dao;


    public Alboms() throws HeadlessException {
        super("Альбомы");
        dao = new DAO();
        alboms = dao.getAllAlboms();
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
            AddAlbom addAlbom = new AddAlbom(this);
        });
        exitButton.addActionListener(e -> {
            this.setVisible(false);
        });
        deleteButton.addActionListener(e -> {
            if(selected != null)
            {
                dao.deleteSong(selected.getIdAlbom());
                update();
            }
        });
        changeButton.addActionListener(e -> {
            if(selected != null)
            {
                ChangeAlbom changeAlbom = new ChangeAlbom(selected, this);
            }
        });
        listInScreen.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()){
                selected = (Albom) listInScreen.getSelectedValue();
            }
        });

    }

    public void update(){
        alboms = dao.getAllAlboms();
        listInScreen.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return alboms.size();
            }

            @Override
            public Object getElementAt(int index) {
                return alboms.get(index);
            }
        });
        listInScreen.updateUI();
    }
}
