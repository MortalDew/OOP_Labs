package com.example.lab2;

import com.example.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Oval extends Figure {
    private String name = "Oval";
    public Oval() {
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
                graphicsContext.fillOval(currentPoint.get(1).getX(), currentPoint.get(1).getY(), w, h);
            } else if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.fillOval(currentPoint.get(1).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.fillOval(currentPoint.get(0).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.fillOval(currentPoint.get(0).getX(), currentPoint.get(1).getY(), w, h);
            }
        } else {
            if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.strokeOval(currentPoint.get(1).getX(), currentPoint.get(1).getY(), w, h);
            } else if (currentPoint.get(0).getX() > currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.strokeOval(currentPoint.get(1).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() < currentPoint.get(1).getY()) {
                graphicsContext.strokeOval(currentPoint.get(0).getX(), currentPoint.get(0).getY(), w, h);
            } else if (currentPoint.get(0).getX() < currentPoint.get(1).getX() && currentPoint.get(0).getY() > currentPoint.get(1).getY()) {
                graphicsContext.strokeOval(currentPoint.get(0).getX(), currentPoint.get(1).getY(), w, h);
            }
        }
    }
}
