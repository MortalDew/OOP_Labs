package com.example.lab2;

import com.example.Figure;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class UndoRedo {
    private static int i;

    public UndoRedo() {
        i = 0;
    }

    public void Undo(ArrayList<Figure> figures, GraphicsContext gc) {
        if(i > 0 && i <= figures.size()) {
            i--;
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            for (int j = 0; j < i; j++)
                figures.get(j).Draw(gc);
        }
    }
    public void Redo(ArrayList<Figure> figures, GraphicsContext gc) {
        if (i >= 0 && i < figures.size()) {
            i++;
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            for (int j = 0; j < i; j++)
                figures.get(j).Draw(gc);
        }
    }
    public void Redraw(ArrayList<Figure> figures, GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (int j = 0; j < i; j++)
            figures.get(j).Draw(gc);
    }
    public void setI(int j) { for(int n = 0; n < j; n++) i++; }
    public int getI() { return i; }

}
