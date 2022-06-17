package com.example.lab2;

import com.example.Figure;
import javafx.scene.canvas.GraphicsContext;

public class Polygon extends Figure {
    private String name = "Polygon";
    public Polygon() {
        super();
        //points = new ArrayList<>();
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        // FIXME: 28.05.2022 для Али параллелограм.
        super.Draw(graphicsContext);
        double l = Math.abs(currentPoint.get(0).getX() - currentPoint.get(1).getX()) / 3;

        graphicsContext.beginPath();
        graphicsContext.moveTo(currentPoint.get(0).getX() + l, currentPoint.get(0).getY());
        graphicsContext.lineTo(currentPoint.get(1).getX(), currentPoint.get(0).getY());
        graphicsContext.lineTo(currentPoint.get(1).getX() - l, currentPoint.get(1).getY());
        graphicsContext.lineTo(currentPoint.get(0).getX(), currentPoint.get(1).getY());
        graphicsContext.lineTo(currentPoint.get(0).getX() + l, currentPoint.get(0).getY());

        if(!fill)
            graphicsContext.stroke();
        else
            graphicsContext.fill();


    }
}
