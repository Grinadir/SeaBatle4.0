package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Need to create single rectangle
 * with its own parameters
 */

public class MyRectangle extends Rectangle {

    private int x;
    private int y;
    private int veto = 0;
    private InterfaceShip ship;
    private Engine engine;
    private final Settings settings;

    public MyRectangle(Settings settings, final Engine engine, double width, double height, int e) {
        this.settings = settings;
        this.engine = engine;
        setWidth(width);
        setHeight(height);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                engine.getMap().mainFunctionInMap(x, y);
            }
        });
    }

    public InterfaceShip getShip() {
        return ship;
    }

    public void setXinMyRect(int x) {
        this.x = x;
    }

    public void setYinMyRect(int y) {
        this.y = y;
    }

    public int getVeto() {
        return this.veto;
    }

    public void setVeto(int veto) {
        this.veto = veto;
    }
}