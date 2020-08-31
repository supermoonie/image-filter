package com.github.supermoonie.gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Hello world!
 *
 * @author supermoonie
 */
public class ImageViewWrapper {

    private double initX, initY;
    private int width, height;
    private double offSetX, offSetY, zoomLevel;

    private final Stage owner;

    private final Image image;

    private final String title;

    private boolean showSave;

    public ImageViewWrapper(Stage owner, Image image, String title, boolean showSave) {
        this.owner = Objects.requireNonNullElseGet(owner, Stage::new);
        this.image = image;
        this.title = Objects.requireNonNullElse(title, "Image");
        this.owner.setTitle(this.title);
        this.owner.setResizable(false);
        this.showSave = showSave;
        initView();
    }

    private void initView() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(image);
        double ratio = image.getWidth() / image.getHeight();
        if (500 / ratio < 500) {
            width = 500;
            height = (int) (500 / ratio);
        } else if (500 * ratio < 500) {
            height = 500;
            width = (int) (500 * ratio);
        } else {
            height = 500;
            width = 500;
        }
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        height = (int) image.getHeight();
        width = (int) image.getWidth();
        System.out.println("height = " + height + "\nwidth = " + width);
        HBox zoom = new HBox(10);
        zoom.setAlignment(Pos.CENTER);

        Slider zoomLevel = new Slider();
        zoomLevel.setMax(4);
        zoomLevel.setMin(1);
        zoomLevel.setMaxWidth(200);
        zoomLevel.setMinWidth(200);
        Label hint = new Label("Zoom Level");
        Label value = new Label("1.0");

        offSetX = width / 2.0;
        offSetY = height / 2.0;

        zoom.getChildren().addAll(hint, zoomLevel, value);

        Slider hScroll = new Slider();
        hScroll.setMin(0);
        hScroll.setMax(width);
        hScroll.setMaxWidth(imageView.getFitWidth());
        hScroll.setMinWidth(imageView.getFitWidth());
        hScroll.setTranslateY(-20);
        Slider vScroll = new Slider();
        vScroll.setMin(0);
        vScroll.setMax(height);
        vScroll.setMaxHeight(imageView.getFitHeight());
        vScroll.setMinHeight(imageView.getFitHeight());
        vScroll.setOrientation(Orientation.VERTICAL);
        vScroll.setTranslateX(-20);


        BorderPane borderPane = new BorderPane();
        BorderPane.setAlignment(hScroll, Pos.CENTER);
        BorderPane.setAlignment(vScroll, Pos.CENTER_LEFT);
        hScroll.valueProperty().addListener(e -> {
            offSetX = hScroll.getValue();
            this.zoomLevel = zoomLevel.getValue();
            double newValue = (double) ((int) (this.zoomLevel * 10)) / 10;
            value.setText(newValue + "");
            if (offSetX < (width / newValue) / 2) {
                offSetX = (width / newValue) / 2;
            }
            if (offSetX > width - ((width / newValue) / 2)) {
                offSetX = width - ((width / newValue) / 2);
            }

            imageView.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
        });
        vScroll.valueProperty().addListener(e -> {
            offSetY = height - vScroll.getValue();
            this.zoomLevel = zoomLevel.getValue();
            double newValue = (double) ((int) (this.zoomLevel * 10)) / 10;
            value.setText(newValue + "");
            if (offSetY < (height / newValue) / 2) {
                offSetY = (height / newValue) / 2;
            }
            if (offSetY > height - ((height / newValue) / 2)) {
                offSetY = height - ((height / newValue) / 2);
            }
            imageView.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
        });
        borderPane.setCenter(imageView);
        borderPane.setTop(hScroll);
        borderPane.setRight(vScroll);
        zoomLevel.valueProperty().addListener(e -> {
            this.zoomLevel = zoomLevel.getValue();
            double newValue = (double) ((int) (this.zoomLevel * 10)) / 10;
            value.setText(newValue + "");
            if (offSetX < (width / newValue) / 2) {
                offSetX = (width / newValue) / 2;
            }
            if (offSetX > width - ((width / newValue) / 2)) {
                offSetX = width - ((width / newValue) / 2);
            }
            if (offSetY < (height / newValue) / 2) {
                offSetY = (height / newValue) / 2;
            }
            if (offSetY > height - ((height / newValue) / 2)) {
                offSetY = height - ((height / newValue) / 2);
            }
            hScroll.setValue(offSetX);
            vScroll.setValue(height - offSetY);
            imageView.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
        });
        borderPane.setCursor(Cursor.OPEN_HAND);
        imageView.setOnMousePressed(e -> {
            initX = e.getSceneX();
            initY = e.getSceneY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });
        imageView.setOnMouseReleased(e -> borderPane.setCursor(Cursor.OPEN_HAND));
        imageView.setOnMouseDragged(e -> {
            hScroll.setValue(hScroll.getValue() + (initX - e.getSceneX()));
            vScroll.setValue(vScroll.getValue() - (initY - e.getSceneY()));
            initX = e.getSceneX();
            initY = e.getSceneY();
        });


        root.getChildren().addAll(new Label(title), borderPane, zoom);
        if (showSave) {
            HBox saveBox = new HBox();
            saveBox.setStyle("-fx-padding: -5 10 0 0;");
            Button saveButton = new Button("Save");
            saveBox.getChildren().add(saveButton);
            saveBox.setAlignment(Pos.CENTER_RIGHT);
            root.getChildren().addAll(new Separator(), saveBox);
            Scene scene = new Scene(root, (imageView.getFitWidth()) + 70, (imageView.getFitHeight()) + 173);
            owner.setScene(scene);
        } else {
            Scene scene = new Scene(root, (imageView.getFitWidth()) + 70, (imageView.getFitHeight()) + 150);
            owner.setScene(scene);
        }

    }

}
