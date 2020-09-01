package com.github.supermoonie.imgproc;

import com.github.supermoonie.util.ImageUtil;

import java.awt.image.BufferedImage;

/**
 * @author supermoonie
 * @since 2020/8/31
 */
public class AppendFilter {

    private final BufferedImage center;

    public AppendFilter(BufferedImage center) {
        this.center = center;
    }

    public BufferedImage appendRight(BufferedImage right) {
        int centerWidth = center.getWidth();
        int centerHeight = center.getHeight();
        int rightWidth = right.getWidth();
        int rightHeight = right.getHeight();
        if (centerHeight != rightHeight) {
            throw new IllegalArgumentException("center.height() != right.height()!");
        }
        int[] centerInPixels = new int[centerWidth * centerHeight];
        ImageUtil.getRGB(center, 0, 0, centerWidth, centerHeight, centerInPixels);
        int[] rightInPixels = new int[rightWidth * rightHeight];
        ImageUtil.getRGB(right, 0, 0, centerWidth, centerHeight, rightInPixels);
        int destWidth = centerWidth + rightWidth;
        BufferedImage dest = new BufferedImage(destWidth, centerHeight, center.getType());
        int[] outPixels = new int[destWidth * centerHeight];
        int index;
        for (int row = 0; row < centerHeight; row++) {
            for (int col = 0; col < centerWidth; col++) {
                index = row * centerWidth + col;
                outPixels[(row * destWidth) + col] = centerInPixels[index];
            }
        }
        for (int row = 0; row < rightHeight; row++) {
            for (int col = 0; col < rightWidth; col++) {
                index = row * rightWidth + col;
                int pix = rightInPixels[index];
                index = row * destWidth + (col + centerWidth);
                outPixels[index] = pix;
            }
        }
        ImageUtil.setRGB(dest, 0, 0, destWidth, centerHeight, outPixels);
        return dest;
    }
}
