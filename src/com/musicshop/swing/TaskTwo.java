package com.musicshop.swing;

import com.musicshop.databases.DAO;
import com.musicshop.databases.FindedMetaData;
import com.musicshop.entities.Albom;
import com.musicshop.swing.albom.AddAlbom;
import com.musicshop.swing.albom.ChangeAlbom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TaskTwo extends JFrame {
    private JPanel panel1;
    private JScrollPane scrollPane;
    private JList listInScreen;
    private JButton exitButton;
    private JList listColumns;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private LinkedList<String> tables;
    private LinkedList<String> columns;
    private String selected;
    private DAO dao;
    private FindedMetaData md;


    public TaskTwo() throws HeadlessException {
        super("Мета данные");
        try {
            dao = new DAO();
            md = dao.getTablesAndColumns();
            tables = md.getTables();
            if (tables.size() != 0) {
                columns = md.getColumns(0);
                selected = tables.get(0);
            }
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize((sizeScreen.width / 2) - 100, sizeScreen.height / 2);
            setLocationRelativeTo(null);
            setContentPane(panel1);
            update();
            setVisible(true);
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
                    selected = (String) listInScreen.getSelectedValue();
                    try {
                        columns.removeAll(columns);
                        ResultSet rs = dao.getColumns(selected);
                        while (rs.next()) {
                            columns.add(rs.getString(4));
                        }
                        listColumns.setModel(new AbstractListModel() {
                            @Override
                            public int getSize() {
                                return columns.size();
                            }

                            @Override
                            public Object getElementAt(int index) {
                                return columns.get(index);
                            }
                        });
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        columns= md.getColumns(selected);
        listInScreen.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return tables.size();
            }

            @Override
            public Object getElementAt(int index) {
                return tables.get(index);
            }
        });
            listColumns.setModel(new AbstractListModel() {
                @Override
                public int getSize() {
                    return columns.size();
                }

                @Override
                public Object getElementAt(int index) {
                    return columns.get(index);
                }
            });
        listColumns.updateUI();
        listInScreen.updateUI();
    }
}
