package com;

import com.example.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Trapezoid extends Figure {
    private String name = "Trapezoid";
    public Trapezoid() {
        super();
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        super.Draw(graphicsContext);
        double l = Math.abs(currentPoint.get(0).getX() - currentPoint.get(1).getX()) / 3;

        graphicsContext.beginPath();
        graphicsContext.moveTo(currentPoint.get(0).getX() + l, currentPoint.get(0).getY());
        graphicsContext.lineTo(currentPoint.get(1).getX() - l, currentPoint.get(0).getY());
        graphicsContext.lineTo(currentPoint.get(1).getX(), currentPoint.get(1).getY());
        graphicsContext.lineTo(currentPoint.get(0).getX(), currentPoint.get(1).getY());
        graphicsContext.lineTo(currentPoint.get(0).getX() + l, currentPoint.get(0).getY());

        if(!fill)
            graphicsContext.stroke();
        else
            graphicsContext.fill();
    }
}
