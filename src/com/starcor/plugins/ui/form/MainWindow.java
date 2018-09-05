package com.starcor.plugins.ui.form;

import com.starcor.plugins.ui.InitData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class MainWindow extends JDialog {

    private JPanel contentPane;

    public MainWindow() {
        setContentPane(contentPane);
        setModal(true);
    }

    public static void main(String[] args) {
        InitData.setShowScale(0.9);
        InitData.initMainWindowSize();
        MainWindow dialog = new MainWindow();
        dialog.pack();
        dialog.setSize(InitData.MAIN_WINDOW_WIDTH, InitData.MAIN_WINDOW_HEIGHT);
        dialog.setLocation(InitData.mainWindowPoint);
        dialog.setVisible(true);
        System.exit(0);
    }
}
