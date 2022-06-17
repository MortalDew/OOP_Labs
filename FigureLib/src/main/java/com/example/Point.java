package com.example;

import com.example.IDraw;
import javafx.scene.canvas.GraphicsContext;

public class Point implements IDraw {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;

    }

    public Point() {
    }


    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }


    @Override
    public void Draw(GraphicsContext graphicsContext){

    }

}
