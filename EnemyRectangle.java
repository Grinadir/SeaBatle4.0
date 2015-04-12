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
    private Gui gui;
    private Counters count;

    public EnemyRectangle(final Gui gui, double width, double height, final Counters count) {
        this.gui=gui;
        this.count=count;
        setWidth(width);
        setHeight(height);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (getFill() == javafx.scene.paint.Color.GREEN) {
                    if((count.getTargetX()+count.getTargetY())!=880){
                        new FunctionsOfMarkedByDifferentColor(gui).undoTarget(count.getTargetX(), count.getTargetY());
                    }
                    setFill(javafx.scene.paint.Color.RED);
                    count.setTargetX(x);
                    count.setTargetY(y);
                    gui.setTargetIndex(x+10*y);
                } else {
                    setFill(javafx.scene.paint.Color.GREEN);
                    gui.setTargetIndex(999);

                }
            }

        });

    }
    //Геттеры и сеттеры

    public void setXenemyRect(int x){
        this.x=x;
    }
    public void setYenemyRect(int y){
        this.y=y;
    }
}

