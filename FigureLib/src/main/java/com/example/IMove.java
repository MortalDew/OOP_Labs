package com.example;

import javafx.scene.canvas.GraphicsContext;

public interface IMove {
    void Move(GraphicsContext graphicsContext, Point startPoint, Point endPoint);
}
