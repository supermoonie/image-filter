package com.github.supermoonie.gui;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author supermoonie
 * @date 2018/9/30 18:14
 */
public class ImageViewer extends JFrame {

    private static final Map<String, JFrame> WINDOW_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ImagePanel> PANEL_MAP = new ConcurrentHashMap<>();

    public static void namedWindow(String title) {
        if (WINDOW_MAP.containsKey(title)) {
            return;
        }
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ImagePanel panel = new ImagePanel();
        PANEL_MAP.put(title, panel);
        frame.add(panel);
        WINDOW_MAP.put(title, frame);
    }

    public static void imshow(String title, BufferedImage image) {
        if (!PANEL_MAP.containsKey(title) || !WINDOW_MAP.containsKey(title)) {
            namedWindow(title);
        }
        PANEL_MAP.get(title).setImage(image);
        WINDOW_MAP.get(title).setSize(image.getWidth(), image.getHeight());
    }

}
