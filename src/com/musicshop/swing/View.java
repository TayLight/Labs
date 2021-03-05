package com.musicshop.swing;

import com.musicshop.swing.albom.Alboms;
import com.musicshop.swing.singer.Singers;
import com.musicshop.swing.song.Songs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class View  extends  JFrame {
    private JButton songButton;
    private JButton singersButton;
    private JButton albomsButton;
    private static Dimension sizeScreen = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton exitButton;
    private JPanel panel1;
    private JButton taskTwoButton;
    private JButton taskButton;

    public View() throws HeadlessException {
        super("Главное меню");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((sizeScreen.width / 2) - 100, sizeScreen.height / 2);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        this.addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent event) {

            }

            public void windowClosed(WindowEvent event) {

            }

            public void windowClosing(WindowEvent event) {
                Object[] options = {"Да", "Нет!"};
                int n = JOptionPane
                        .showOptionDialog(event.getWindow(), "Закрыть окно?",
                                "Подтверждение", JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, options,
                                options[0]);
                if (n == 0) {
                    setVisible(false);
                    System.exit(0);
                }
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
        songButton.addActionListener(e -> {
            Songs songs = new Songs();
        });
        singersButton.addActionListener(e -> {
            Singers singers = new Singers();
        });
        albomsButton.addActionListener(e -> {
            Alboms alboms = new Alboms();
        });
        taskTwoButton.addActionListener(e -> {
            TaskTwo taskTwo = new TaskTwo();
        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        taskButton.addActionListener(e -> {
            Task task = new Task();
        });

    }
}
