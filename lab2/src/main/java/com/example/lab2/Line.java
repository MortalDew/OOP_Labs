package com.example.lab2;

import com.example.Figure;
import com.example.Point;
import javafx.scene.canvas.GraphicsContext;

public class Line extends Figure {
    private String name = "Line";
    public Line(Point... points){
        super(points);
    }
    public Line(){
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext){
        super.Draw(graphicsContext);

        graphicsContext.strokeLine(currentPoint.get(0).getX(), currentPoint.get(0).getY(),
                currentPoint.get(1).getX(), currentPoint.get(1).getY());

    }

    @Override
    public void Move(GraphicsContext graphicsContext, Point startPoint, Point endPoint) {
        super.Move(graphicsContext, startPoint, endPoint);

    }

}
