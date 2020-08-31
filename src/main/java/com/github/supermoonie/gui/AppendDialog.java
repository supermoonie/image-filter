package com.github.supermoonie.gui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * @author supermoonie
 * @since 2020/8/31
 */
public class AppendDialog {

    private BorderPane borderPane;

    private Stage stage;

    private Scene scene;

    private FileChooser fileChooser;

    public AppendDialog() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("*.png; *.jpg");
        fileChooser.setSelectedExtensionFilter(filter);
        borderPane = new BorderPane();
        scene = new Scene(borderPane);
        stage = new Stage();
        stage.setScene(scene);
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-padding: 10;");
        Button add = new Button("Add");
        add.setOnMouseClicked(mouseEvent -> {
            List<File> files = fileChooser.showOpenMultipleDialog(stage);
            if (null == files || files.size() == 0) {
                return;
            }

        });
        gridPane.add(add, 0, 0, 4, 1);
        gridPane.setAlignment(Pos.CENTER);

    }
}
