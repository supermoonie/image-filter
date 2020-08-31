package com.github.supermoonie.imgproc;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author supermoonie
 * @since 2020/8/31
 */
public class AppendTest {

    @Test
    public void appendRight() throws IOException {
        Append append = new Append(ImageIO.read(new File("/Users/supermoonie/Downloads/Lightning1.png")));
        BufferedImage bufferedImage = append.appendRight(ImageIO.read(new File("/Users/supermoonie/Downloads/Lightning2.png")));
        ImageIO.write(bufferedImage,"png",  new File("/Users/supermoonie/Downloads/Lightning5.png"));
    }
}