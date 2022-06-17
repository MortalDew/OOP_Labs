package com.example.lab2;

import com.LibFabric;
import com.example.Figure;

public class Factory {
    public static Figure FactoryMethod(String str) {
        return switch (str) {
            case "Line" -> new Line();
            case "Rectangle" -> new Rectangle();
            case "Oval" -> new Oval();
            case "Curve" -> new CurveLine();
            // case "Polygon" -> new Polygon();
            default -> LibFabric.Fabric(str);
        };
    }
}
