package com.github.supermoonie.imgproc;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.github.supermoonie.gui.ImageViewer.imshow;
import static org.junit.Assert.*;

/**
 * @author supermoonie
 * @date 2018/10/9 18:05
 */
public class GrayFilterTest {

    @Test
    public void minMaxRgb() throws IOException, InterruptedException {
        BufferedImage src = ImageIO.read(new FileInputStream(new File("D:/opencv-images/lena.jpg")));
        GrayFilter grayFilter = new GrayFilter();
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        grayFilter.filter(src, dest);
        imshow("src", src);
        imshow("dest", dest);
        Thread.sleep(Integer.MAX_VALUE);
    }

}