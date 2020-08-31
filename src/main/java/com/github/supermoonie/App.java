package com.github.supermoonie;

import com.github.supermoonie.gui.AppendDialog;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
        Button appendButton = new Button("Append");
        appendButton.setOnMouseClicked(mouseEvent -> new AppendDialog(primaryStage));
        gridPane.add(appendButton, 0, 0, 1, 1);
    }
}
