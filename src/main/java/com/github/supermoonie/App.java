package com.github.supermoonie;

import com.github.supermoonie.gui.AppendDialog;
import com.github.supermoonie.gui.LightningImageView;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 * @author supermoonie
 */
public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-padding: 10;");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        initButtons(gridPane);
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.show();
        primaryStage.toFront();
    }

    private void initButtons(GridPane gridPane) {
        Button appendButton = new Button("Append Right");
        appendButton.setOnMouseClicked(mouseEvent -> new AppendDialog(primaryStage));
        Button openButton = new Button("Open");
        openButton.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            Image image;
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                image = SwingFXUtils.toFXImage(bufferedImage, null);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ui/LightningImageView.fxml"));
            try {
                Parent root = fxmlLoader.load();
                LightningImageView imageView = fxmlLoader.getController();
                imageView.setImage(image);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setMinWidth(560);
                stage.setMinHeight(575);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        gridPane.add(appendButton, 0, 0, 1, 1);
        gridPane.add(openButton, 1, 0, 1, 1);
    }
}
