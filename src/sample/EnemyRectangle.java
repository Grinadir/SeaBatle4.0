package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

public class EnemyRectangle extends Rectangle {
    private int x;
    private int y;
    private Engine engine;

    public EnemyRectangle(final Engine engine, double width, double height) {
        this.engine = engine;

        setWidth(width);
        setHeight(height);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (getFill() == javafx.scene.paint.Color.GREEN) {
                    if ((engine.getTargetX() + engine.getTargetY()) != 880) {
                        new FunctionsOfMarkedByDifferentColor(engine.getRects()).undoTarget(engine.getTargetX(), engine.getTargetY());
                    }
                    setFill(javafx.scene.paint.Color.RED);
                    engine.setTargetX(x);
                    engine.setTargetY(y);
                    engine.setTargetIndex(x + 10 * y);
                } else {
                    setFill(javafx.scene.paint.Color.GREEN);
                    engine.setTargetIndex(999);

                }
            }

        });

    }
    //Геттеры и сеттеры

    public void setXEnemyRect(int x) {
        this.x = x;
    }

    public void setYEnemyRect(int y) {
        this.y = y;
    }
}

