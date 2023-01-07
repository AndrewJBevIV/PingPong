package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
    private double x;
    private double y;
    private double size;
    private double velocity;
    private double[] direction;

    public Ball(double x, double y, double size, double velocity) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.velocity = velocity;
        this.direction = new double[] {1, 1};  // Initial direction is down and right
    }

    public void update() {
        // Update the position based on the velocity and direction
        this.x += this.velocity * this.direction[0];
        this.y += this.velocity * this.direction[1];
    }

    public void reflect() {
        // Reverse the direction on the x-axis
        this.direction[0] *= -1;
    }

    public void bounce() {
        // Reverse the direction on the y-axis
        this.direction[1] *= -1;
    }

    public boolean collidesWith(Paddle paddle) {
        // Check if the ball is within the x-bounds of the paddle
        if (this.x < paddle.getX() + paddle.getWidth() && this.x + this.size > paddle.getX()) {
            // Check if the ball is within the y-bounds of the paddle
            if (this.y < paddle.getY() + paddle.getHeight() && this.y + this.size > paddle.getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWith(ScreenEdge screenEdge) {
        // Check if the ball is within the y-bounds of the screen
        if (this.y < 0 || this.y + this.size > screenEdge.getHeight()) {
            return true;
        }
        return false;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillOval(this.x, this.y, this.size, this.size);  // Draw the ball
    }
}
