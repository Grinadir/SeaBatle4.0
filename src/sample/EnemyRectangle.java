package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

public class EnemyRectangle extends Rectangle {
    static int targetX;
    static int targetY;
    int x;
    int y;


    public EnemyRectangle(double width, double height, int e) {

        setWidth(width);
        setHeight(height);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (getFill() == javafx.scene.paint.Color.GREEN) {
                    setFill(javafx.scene.paint.Color.RED);
                    targetX = x;
                    targetY = y;

                } else {
                    setFill(javafx.scene.paint.Color.GREEN);
                    targetX = 999;
                    targetY = 999;
                }
            }

        });

    }
}

