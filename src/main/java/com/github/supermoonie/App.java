package com.github.supermoonie;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.github.supermoonie.gui.ImageViewer.imshow;

/**
 * Hello world!
 *
 * @author supermoonie
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        imshow("src_1", ImageIO.read(new FileInputStream(new File("D:/opencv-images/lena.jpg"))));
        imshow("src_2", ImageIO.read(new FileInputStream(new File("D:/opencv-images/baboon.jpg"))));
    }
}
