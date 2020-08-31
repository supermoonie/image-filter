package com.github.supermoonie.gui;

import com.github.supermoonie.imgproc.Append;
import com.github.supermoonie.util.AlertUtil;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author supermoonie
 * @since 2020/8/31
 */
public class AppendDialog {

    private final FileChooser fileChooser;

    private final Stage owner;

    public AppendDialog(Stage owner) {
        this.owner = owner;
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.setSelectedExtensionFilter(filter);

        Image firstImage = select();
        if (null == firstImage) {
            return;
        }
        Stage firstStage = new Stage();
        new ImageViewWrapper(firstStage, firstImage, "First", false);
        firstStage.show();
        firstStage.toFront();

        Image secondImage = select();
        if (null == secondImage) {
            return;
        }
        Stage secondStage = new Stage();
        new ImageViewWrapper(secondStage, secondImage, "Second", false);
        secondStage.show();
        secondStage.toFront();

        Append append = new Append(SwingFXUtils.fromFXImage(firstImage, null));
        BufferedImage dest = append.appendRight(SwingFXUtils.fromFXImage(secondImage, null));
        Image target = SwingFXUtils.toFXImage(dest, null);

        Stage stage = new Stage();
        new ImageViewWrapper(stage, target, "Target", true);
        stage.show();
        stage.toFront();
    }

    private Image select() {
        File file = fileChooser.showOpenDialog(owner);
        if (null == file) {
            return null;
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException e) {
            AlertUtil.error(e);
            return null;
        }
    }
}
