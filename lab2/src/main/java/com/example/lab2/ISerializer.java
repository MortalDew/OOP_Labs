package com.example.lab2;

import com.example.Figure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public interface ISerializer {
    void Serialize(ArrayList<Figure> figures, File file) throws IOException;
    ArrayList<Figure> Deserialize(File file) throws IOException;
}
