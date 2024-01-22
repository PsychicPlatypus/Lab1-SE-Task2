package com.softwareengineering.labtasks12;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class Component implements Serializable {
    public StackPane getComponent() {
        Rectangle rectangle = new Rectangle(200, 100, Color.RED);
        StackPane root = new StackPane();
        root.getChildren().add(rectangle);
        return  root;
    }
}
