package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Paddle {
    private double x;
    private double y;
    private double width;
    private double height;
    private boolean isCPU;

    public Paddle(double x, double y, double width, double height, boolean isCPU) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isCPU = isCPU;
    }

    public void update() {
        // Update the position based on user input
        // TODO: Add code for handling user input
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.y = isCPU? 600-p.y : p.y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(this.x, this.y, this.width, this.height);  // Draw the paddle
    }
}

