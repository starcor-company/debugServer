package com.starcor.plugins.ui.form;

import com.starcor.plugins.ui.InitData;

import javax.swing.*;

public class ConnectWindow extends JDialog {
    private JPanel contentPane;
    private JButton btnConnect;
    private JComboBox editBox;

    public ConnectWindow() {
        setContentPane(contentPane);
        setModal(true);
        editBox.setEditable(true);
    }

    public static void main(String[] args) {
        InitData.initConnectWindowSize();
        ConnectWindow dialog = new ConnectWindow();
        dialog.pack();
        dialog.setLocation(InitData.connectWindowPoint);
        dialog.setSize(InitData.CONNECT_WINDOW_WIDTH, InitData.CONNECT_WINDOW_HEIGHT);
        dialog.setVisible(true);
        System.exit(0);
    }
}
