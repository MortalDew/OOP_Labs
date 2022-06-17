package com.example.lab2;

import com.example.Figure;
import com.example.Point;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class CurveLine extends Figure {
    ArrayList<Point> points;
    private String name = "Curve";
    public CurveLine() {
        super();
        points = new ArrayList<>();
    }
    public ArrayList<Point> getPoints() {
        return points;
    }
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
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
        points.add(currentPoint.get(1));
        graphicsContext.strokeLine(currentPoint.get(0).getX(), currentPoint.get(0).getY(),
                points.get(0).getX(), points.get(0).getY());

        for(int i = 0; i < points.size() - 1; i++)
            graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(),
                    points.get(i+1).getX(), points.get(i+1).getY());

    }
}
