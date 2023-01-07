package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
        import javafx.scene.Group;
        import javafx.scene.Scene;
        import javafx.scene.canvas.Canvas;
        import javafx.scene.canvas.GraphicsContext;
        import javafx.stage.Stage;

public class Main extends Application {
    // Set up the game constants
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int PADDLE_WIDTH = 20;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_SIZE = 20;
    private static final int BALL_VELOCITY = 5;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the game window
        primaryStage.setTitle("Ping Pong");
        Group root = new Group();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create the game objects
        Ball ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, BALL_SIZE, BALL_VELOCITY);
        Paddle paddleLeft = new Paddle(0, SCREEN_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT,true);
        Paddle paddleRight = new Paddle(SCREEN_WIDTH - PADDLE_WIDTH, SCREEN_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT,false);
        ScreenEdge screenEdge = new ScreenEdge(SCREEN_WIDTH,SCREEN_HEIGHT);

        // Set up the game loop
        GraphicsContext gc = canvas.getGraphicsContext2D();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Update the game state
                ball.update();
                paddleLeft.update();
                paddleRight.update();

                // Check for collisions
                if (ball.collidesWith(paddleLeft) || ball.collidesWith(paddleRight)) {
                    ball.reflect();
                }
                if (ball.collidesWith(screenEdge)) {
                    ball.bounce();
                }

                // Render the game
                gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);  // Clear the screen
                ball.draw(gc);
                paddleLeft.draw(gc);
                paddleRight.draw(gc);
            }
        }.start();
    }
}
