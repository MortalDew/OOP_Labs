package com.example.lab2;

import com.example.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Figure {
    private String name = "Rectangle";
    public Rectangle(){
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);

        double h, w;
        h = Math.abs(currentPoint.get(0).getY() - currentPoint.get(1).getY());
        w = Math.abs(currentPoint.get(0).getX() - currentPoint.get(1).getX());

        if(fill) {
            if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.fillRect(currentPoint.get(1).getX(), currentPoint.get(1).getY(), w, h);
            } else if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.fillRect(currentPoint.get(1).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.fillRect(currentPoint.get(0).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.fillRect(currentPoint.get(0).getX(), currentPoint.get(1).getY(), w, h);
            }
        } else {
            if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.strokeRect(currentPoint.get(1).getX(), currentPoint.get(1).getY(), w, h);
            } else if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.strokeRect(currentPoint.get(1).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.strokeRect(currentPoint.get(0).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.strokeRect(currentPoint.get(0).getX(), currentPoint.get(1).getY(), w, h);
            }
        }



    }
}
