package com.github.supermoonie.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author supermoonie
 * @date 2020-09-01
 */
public class LightningImageView implements Initializable {

    private static final int LIMIT = 500;

    @FXML
    public BorderPane root;

    @FXML
    public ImageView imageView;

    @FXML
    public Button zoomOutButton;

    @FXML
    public Button zoomInButton;

    @FXML
    public Button originalSwitchButton;

    @FXML
    public Label colorLabel;

    @FXML
    public Button saveButton;

    private Image image;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        imageView.setImage(image);
        // 宽高比
        double ratio = image.getWidth() / image.getHeight();
        if (ratio == 1) {
            // 正方形
            if (image.getWidth() < LIMIT) {
                imageView.setFitWidth(image.getWidth());
                imageView.setFitHeight(image.getHeight());
            }
        } else if (ratio < 1) {
            // 长图
        } else {
            // 宽图
        }
        // 小图
        // 长图
        // 宽图
        // 大图
    }

}
