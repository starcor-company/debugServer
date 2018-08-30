package kotlin.plugins.ui.form;


import kotlin.plugins.ui.InitData;

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
