package kotlin.plugins.ui;

import java.awt.*;

/**
 * Copyright (c) 2018, 北京视达科科技有限责任公司 All rights reserved.
 * author：MrLSM
 * date：2018/5/4
 * description：
 */
public class InitData {
    //展示比例
    private static double SHOW_SCALE = 0.8;

    public static int MAIN_WINDOW_WIDTH;
    public static int MAIN_WINDOW_HEIGHT;
    public static Point mainWindowPoint;

    public static int CONNECT_WINDOW_WIDTH;
    public static int CONNECT_WINDOW_HEIGHT;
    public static Point connectWindowPoint;

    public static void initMainWindowSize() {
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        MAIN_WINDOW_WIDTH = (int) (scrSize.getWidth() * SHOW_SCALE);
        MAIN_WINDOW_HEIGHT = (int) (scrSize.getHeight() * SHOW_SCALE);
        int x = (int) (scrSize.getWidth() * (1 - SHOW_SCALE) / 2);
        int y = (int) (scrSize.getHeight() * (1 - SHOW_SCALE) / 2);
        mainWindowPoint = new Point(x, y);
    }

    public static void initConnectWindowSize() {
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        CONNECT_WINDOW_WIDTH = (int) (scrSize.getWidth() * 0.22);
        CONNECT_WINDOW_HEIGHT = (int) (scrSize.getHeight() * 0.2);
        int x = (int) (scrSize.getWidth() * (1 - 0.3) / 2);
        int y = (int) (scrSize.getHeight() * (1 - 0.2) / 2);
        connectWindowPoint = new Point(x, y);
    }

    public static void setShowScale(double scale) {
        if (scale > 0 && scale <= 1) {
            SHOW_SCALE = scale;
        }
    }

    public static void initConnectInfo() {

    }
}
