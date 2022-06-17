package com.example.lab2;

import com.example.Figure;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;


public class Serializer implements ISerializer {

    @Override
    public void Serialize(ArrayList<Figure> figures, File file) throws IOException {
        ArrayList<String> jsonStrings = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for(Figure figure : figures) {
            StringWriter writer = new StringWriter();
            mapper.writeValue(writer, figure);
            jsonStrings.add(writer.toString());
        }

        if(file.createNewFile()) {
            try (FileWriter fileWriter = new FileWriter(file.getPath(), false)) {
                for (String str : jsonStrings) {
                    fileWriter.write(str);
                    fileWriter.append('\n');
                    fileWriter.flush();
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    public ArrayList<Figure> Deserialize(File file) throws IOException {
        ArrayList<String> jsonStrings = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file.getPath())) {
            int i = 0, c;

            while ((c = fileReader.read()) != -1) {
                if((char)c == '\n') {
                    jsonStrings.add(stringBuilder.toString());
                    stringBuilder.delete(0, stringBuilder.length());
                    continue;
                }
                stringBuilder.append((char) c);
            }

        } catch (IOException ex) {
            System.err.print(ex.getMessage());
        }

        ArrayList<Figure> figures = new ArrayList<>();
        int i = 0;
        for(String str : jsonStrings){
            String[] test = str.split("\"");
            StringReader reader = new StringReader(str);
            System.out.print(str);
            Figure figure = Factory.FactoryMethod(test[test.length - 2]);

            ObjectMapper mapper = new ObjectMapper();

            figure = mapper.readValue(reader, figure.getClass());

            figures.add(figure);

        }
        return figures;
    }
}
