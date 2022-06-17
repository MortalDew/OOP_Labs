package com.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Figure implements IDraw, IMove, Cloneable {
    protected ArrayList<Point> currentPoint;
    protected ArrayList<Point> field;
    protected String fillColor;
    protected String strokeColor;
    protected double width;
    protected boolean chosen;
    protected boolean fill;

    public Figure(Point... point) {
        currentPoint = new ArrayList<>();
        field = new ArrayList<>();
        chosen = false;
    }
    public Figure() {
        currentPoint = new ArrayList<>();
        field = new ArrayList<>();
        chosen = false;
    }

    public ArrayList<Point> getCurrentPoint() {
        return currentPoint;
    }
    public void setCurrentPoint(ArrayList<Point> currentPoint) {
        this.currentPoint = currentPoint;
    }
    public ArrayList<Point> getField() {
        return field;
    }
    public void setField(ArrayList<Point> field) {
        this.field = field;
    }
    public boolean isChosen() {
        return chosen;
    }
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;

    }
    public void setChosen(boolean chosen, GraphicsContext gc) {
        this.chosen = chosen;

    }
    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setFill(boolean fill) {
        this.fill = fill;
    }
    public boolean isFill() {
        return fill;
    }
    public String getFillColor() {
        return fillColor;
    }
    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
    public String getStrokeColor() {
        return strokeColor;
    }
    public double getWidth() {
        return width;
    }
    public void setFieldPoints(ArrayList<Point> points){
        this.field = points;
    }



    protected void setField(){

        if(field.get(0).getX() > field.get(1).getX()) {
            double buff = field.get(0).getX();
            field.get(0).setX(field.get(1).getX());
            field.get(1).setX(buff);

        }

        if(field.get(0).getY() > field.get(1).getY()){
            double buff = field.get(0).getY();
            field.get(0).setY(field.get(1).getY());
            field.get(1).setY(buff);

        }
    }

    public void DrawField(GraphicsContext gc) {
        double h, w;
        Paint paint = gc.getStroke();
        gc.setStroke(Color.BLACK);
        h = Math.abs(field.get(0).getY() - field.get(1).getY());
        w = Math.abs(field.get(0).getX() - field.get(1).getX());

        gc.strokeRect(field.get(0).getX(), field.get(0).getY(), w, h);

        gc.setStroke(paint);
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.valueOf(fillColor));
        graphicsContext.setStroke(Color.valueOf(strokeColor));
        graphicsContext.setLineWidth(width);
    }



    @Override
    public void Move(GraphicsContext graphicsContext, Point startPoint, Point endPoint) {

        double X = endPoint.getX() - startPoint.getX(), Y = endPoint.getY() - startPoint.getY();

        currentPoint.get(0).setX(currentPoint.get(0).getX() + X);
        currentPoint.get(1).setX(currentPoint.get(1).getX() + X);
        currentPoint.get(0).setY(currentPoint.get(0).getY() + Y);
        currentPoint.get(1).setY(currentPoint.get(1).getY() + Y);

        field.get(0).setX(field.get(0).getX() + X);
        field.get(1).setX(field.get(1).getX() + X);
        field.get(0).setY(field.get(0).getY() + Y);
        field.get(1).setY(field.get(1).getY() + Y);

        Draw(graphicsContext);

    }

    @Override
    public Figure clone() { // метод из интерфейса Cloneable позволяет клонирова значения объекта в другой объект.
        try {
            Figure clone = (Figure) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


}
