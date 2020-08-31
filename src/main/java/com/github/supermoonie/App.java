package com.github.supermoonie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Hello world!
 *
 * @author supermoonie
 */
public class App extends Application {
    public static void main(String[] args) throws IOException {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        Button test = new Button("Append");
        gridPane.add(test, 0, 0, 1, 1);
    }
}
