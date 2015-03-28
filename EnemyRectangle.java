package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

public class EnemyRectangle extends Rectangle {
    private static int targetX;
    private static int targetY;
    private int x;
    private int y;


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
    //Геттеры и сеттеры

    public void setXenemyRect(int x){
        this.x=x;
    }

    public void setYenemyRect(int y){
        this.y=y;

    }

    public static int getTargetX(){
        return targetX;
    }

    public static int getTargetY(){
        return targetY;
    }


}

