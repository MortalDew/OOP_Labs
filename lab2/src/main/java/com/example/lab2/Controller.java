package com.example.lab2;

import com.example.Figure;
import com.example.Point;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

public class Controller {
    private boolean chosen;
    private UndoRedo undoRedo;
    private Figure figure = new Figure();
    private Figure copyFigure;
    private String str;
    private ArrayList<Figure> figures;
    private double lastX, lastY;

    @FXML
    private AnchorPane AP;

    @FXML
    private Button Build;

    @FXML
    private Canvas Canvas;

    @FXML
    private ColorPicker Color;

    @FXML
    private TextField LineWidth;

    @FXML
    private TextField X1;

    @FXML
    private TextField X2;

    @FXML
    private TextField Y1;

    @FXML
    private TextField Y2;

    @FXML
    private Button choseButton;

    @FXML
    private TextField count;

    @FXML
    private Button curveLineButton;

    @FXML
    private CheckBox fillCheck;

    @FXML
    private Button lineButton;

    @FXML
    private MenuItem openMenuItem;

    @FXML
    private Button ovalButton;

    @FXML
    private Button polygonButton;

    @FXML
    private Button rectangleButton;

    @FXML
    private Button redoButton;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private Button undoButton;

    @FXML
    private Label xLabel;

    @FXML
    private Label yLabel;


    @FXML
    void MouseDragged(MouseEvent event) {
        if(!chosen) {
            figure.getCurrentPoint().add(new Point(event.getX(), event.getY()));
            figure.getField().add(new Point(event.getX(), event.getY()));
            undoRedo.Undo(figures, Canvas.getGraphicsContext2D());
            undoRedo.setI(1);
            figure.Draw(Canvas.getGraphicsContext2D());
            figure.getCurrentPoint().remove(1);
            figure.getField().remove(1);

        } else if(event.getButton() == MouseButton.PRIMARY &&
                figure.getField().get(0).getX() < event.getX() && figure.getField().get(0).getY() < event.getY() &&
                figure.getField().get(1).getX() > event.getX() && figure.getField().get(1).getY() > event.getY()) {

            figure.Move(Canvas.getGraphicsContext2D(), new Point(lastX, lastY), new Point(event.getX(), event.getY()));
            undoRedo.Redraw(figures, Canvas.getGraphicsContext2D());
            lastY += event.getY() - lastY;
            lastX += event.getX() - lastX;

        }
    }

    @FXML
    void MouseClicked(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY  && chosen) {
            undoRedo.Redraw(figures, Canvas.getGraphicsContext2D());

            for (int i = figures.size() - 1 ; i >= 0; i--) {
                if (figures.get(i).getField().get(0).getX() < event.getX() && figures.get(i).getField().get(0).getY() < event.getY() &&
                        figures.get(i).getField().get(1).getX() > event.getX() && figures.get(i).getField().get(1).getY() > event.getY()) {
                    figure = figures.get(i);
                    fillCheck.setSelected(figure.isFill());
                    figure.setChosen(true, Canvas.getGraphicsContext2D());
                    figure.DrawField(Canvas.getGraphicsContext2D());
                    break;

                } else {
                    figures.get(i).setChosen(false, Canvas.getGraphicsContext2D());

                }
            }
        }

    }

    @FXML
    void MouseMoved(MouseEvent event) {
        yLabel.setText(String.valueOf(event.getY()));
        xLabel.setText(String.valueOf(event.getX()));

    }

    @FXML
    void MousePressed(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && !chosen) {
            if(undoRedo.getI() < figures.size()){
                for(int i = figures.size() - 1; i >= undoRedo.getI(); i--)
                    figures.remove(i);
            }
            figure = Factory.FactoryMethod(str);
            figure.setFillColor(Color.getValue().toString());
            figure.setStrokeColor(Color.getValue().toString());

            try {
                figure.setWidth(Double.parseDouble(LineWidth.getText()));
            } catch (NumberFormatException e) {
                figure.setWidth(1.0);
                LineWidth.setText(String.valueOf(1.0));
            }

            if(fillCheck.isSelected())
                figure.setFill(true);
            else if (!fillCheck.isSelected())
                figure.setFill(false);

            figures.add(figure);
            undoRedo.setI(1);
            figure.getCurrentPoint().add(new Point(event.getX(), event.getY()));
            figure.getField().add(new Point(event.getX(), event.getY()));

        } else if(chosen) {
            lastX = event.getX();
            lastY = event.getY();

        }

    }

    @FXML
    void MouseReleased(MouseEvent event) {
        if(!chosen) {
            figure.getCurrentPoint().add(new Point(event.getX(), event.getY()));
            figure.getField().add(new Point(event.getX(), event.getY()));
            figure.Draw(Canvas.getGraphicsContext2D());
            figure.setField(figure.getField());

        }
    }

    @FXML
    void initialize() {
        Color.setValue(javafx.scene.paint.Color.BLACK);
        figures = new ArrayList<>();
        undoRedo = new UndoRedo();
        chosen = false;
        choseButton.setDisable(true);
        str = "Curve";
        LineWidth.setText(String.valueOf(Canvas.getGraphicsContext2D().getLineWidth()));

        lineButton.setOnAction(event -> {
            chosen = false;
            choseButton.setDisable(false);
            str = "Line";
        });
        rectangleButton.setOnAction(event -> {
            chosen = false;
            choseButton.setDisable(false);
            str = "Rectangle";
        });
        ovalButton.setOnAction(event -> {
            chosen = false;
            choseButton.setDisable(false);
            str = "Oval";
        });
        curveLineButton.setOnAction(event -> {
            chosen = false;
            choseButton.setDisable(true);
            str = "Curve";
        });
        polygonButton.setOnAction(event -> {
            chosen = false;
            choseButton.setDisable(false);
            str = "lol";
        });

        Color.setOnAction(event -> {
            if(!chosen) {
                Canvas.getGraphicsContext2D().setFill(Color.getValue());
                Canvas.getGraphicsContext2D().setStroke(Color.getValue());

            } else {
                figure.setFillColor(Color.getValue().toString());
                figure.setStrokeColor(Color.getValue().toString());
                undoRedo.Redraw(figures, Canvas.getGraphicsContext2D());
                figure.DrawField(Canvas.getGraphicsContext2D());

            }
        });
        choseButton.setOnAction(event -> chosen = true);
        undoButton.setOnAction(event -> undoRedo.Undo(figures, Canvas.getGraphicsContext2D()));
        redoButton.setOnAction(event -> undoRedo.Redo(figures, Canvas.getGraphicsContext2D()));
        LineWidth.setOnAction(event -> {
            try {
                Canvas.getGraphicsContext2D().setLineWidth(Double.parseDouble(LineWidth.getText())) ;

            } catch (NumberFormatException e) {
                Canvas.getGraphicsContext2D().setLineWidth(1.0);
                LineWidth.setText(String.valueOf(1.0));

            }
        });
        fillCheck.setOnAction(event -> {
            if(chosen && fillCheck.isSelected())
                figure.setFill(true);
            else if (chosen && !fillCheck.isSelected())
                figure.setFill(false);

            undoRedo.Redraw(figures, Canvas.getGraphicsContext2D());
        });

        final KeyCombination copy = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
        final KeyCombination paste = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);

        EventHandler<KeyEvent> eventHandler = event -> {
            if (copy.match(event) && figure.isChosen()) {
                copyFigure = figure.clone();
            } else if(paste.match(event) && figure.isChosen()) {
                double X = Double.parseDouble(xLabel.getText());
                double Y = Double.parseDouble(yLabel.getText());
                double x = X - figure.getCurrentPoint().get(0).getX();
                double y = Y - figure.getCurrentPoint().get(0).getY();

                ArrayList<Point> points = new ArrayList<>();
                points.add(new Point(X, Y));
                points.add(new Point(figure.getCurrentPoint().get(1).getX() + x, figure.getCurrentPoint().get(1).getY() + y));
                copyFigure.setCurrentPoint(points);
                copyFigure.setFieldPoints(points);
                figures.add(copyFigure);
                undoRedo.setI(1);
                undoRedo.Redraw(figures, Canvas.getGraphicsContext2D());
            }
        };

        Canvas.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                oldValue.removeEventFilter(KEY_PRESSED, eventHandler);
            }

            if (newValue != null) {
                newValue.addEventFilter(KEY_PRESSED, eventHandler);
            }
        });

        Build.setOnAction(event -> {
            figure = Factory.FactoryMethod(str);
            ArrayList<Point> points = new ArrayList<>();
            try {
                points.add(new Point(Double.parseDouble(X1.getText()), Double.parseDouble(Y1.getText())));
                points.add(new Point(Double.parseDouble(X2.getText()), Double.parseDouble(Y2.getText())));
            } catch (NumberFormatException e) {
                X1.setText("");
                X2.setText("");
                Y1.setText("");
                Y2.setText("");
            }
            figure.setFillColor(Color.getValue().toString());
            figure.setStrokeColor(Color.getValue().toString());
            figure.setFieldPoints(points);
            figure.setCurrentPoint(points);
            figures.add(figure);
            figure.Draw(Canvas.getGraphicsContext2D());
            undoRedo.setI(1);
            X1.setText("");
            X2.setText("");
            Y1.setText("");
            Y2.setText("");
        });

        saveMenuItem.setOnAction(event -> {
            Window mainStage = Canvas.getScene().getWindow();
            Serializer serializer = new Serializer();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON", "*.json"));
            File selectedFile = fileChooser.showSaveDialog(mainStage);

            if(selectedFile != null)
                try {
                    serializer.Serialize(figures, selectedFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });

        openMenuItem.setOnAction(event -> {
            Canvas.getGraphicsContext2D().clearRect(0,0, Canvas.getWidth(), Canvas.getHeight());
            figures.clear();
            Window mainStage = Canvas.getScene().getWindow();
            Serializer serializer = new Serializer();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JSON", "*.json"));
            File selectedFile = fileChooser.showOpenDialog(mainStage);

            if(selectedFile != null)
                try {
                    figures.addAll(serializer.Deserialize(selectedFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            for(Figure figure : figures)
                figure.Draw(Canvas.getGraphicsContext2D());
            undoRedo.setI(figures.size());
        });
    }

}