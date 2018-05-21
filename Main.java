
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    public static ArrayList<Rectangle> bonuses = new ArrayList<>();
    Image image = new Image(getClass().getResourceAsStream("2.png"));
    ImageView imageView = new ImageView(image);
    Character player = new Character(imageView);
    static Pane root = new Pane();

    public void bonus(){
        int random = (int)Math.floor(Math.random()*100);
        int x = (int)Math.floor(Math.random()*600);
        int y = (int)Math.floor(Math.random()*600);
        if(random == 5){

            Image money = new Image("bitcoin.png");
            Image speed = new Image("speed.jpg");

            Rectangle rect = new Rectangle(30,30);
            Rectangle rectSpeed = new Rectangle(40,40);
            rectSpeed.setFill(new ImagePattern(speed));
           rectSpeed.setX(300);
            rectSpeed.setY(300);
            rect.setFill(new ImagePattern(money));
            rect.setX(x);
            rect.setY(y);
            bonuses.add(rect);
            root.getChildren().addAll(rect,rectSpeed);
        }
    }
    public void update() {
        if (isPressed(KeyCode.UP)) {
            player.animation.play();
            player.animation.setOffsetY(64);
            player.moveY(-5);
        } else if (isPressed(KeyCode.DOWN)) {
            player.animation.play();
            player.animation.setOffsetY(0);
            player.moveY(5);
        } else if (isPressed(KeyCode.RIGHT)) {
            player.animation.play();
            player.animation.setOffsetY(192);
            player.moveX(5);
        } else if (isPressed(KeyCode.LEFT)) {
            player.animation.play();
            player.animation.setOffsetY(128);
            player.moveX(-5);
        }
        else{
            player.animation.stop();
        }
    }
    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        root.setPrefSize(600,600);
        Label label =new Label();
        Image image = new Image(getClass().getResourceAsStream("Фон3.png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(600);
        imageView.setFitHeight(600);
        label.setGraphic(imageView);





        root.getChildren().addAll(label,player);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
        scene.setOnKeyReleased(event-> {
            keys.put(event.getCode(), false);
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                bonus();
            }
        };
        timer.start();
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }


}