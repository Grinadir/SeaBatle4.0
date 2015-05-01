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
    private FunctionsOfMarkedByDifferentColor func;

    public MyRectangle(Settings settings, final Engine engine, double width, double height, int e) {
        this.settings = settings;
        this.engine = engine;
        setWidth(width);
        setHeight(height);
        this.func = new FunctionsOfMarkedByDifferentColor(engine.getRects());

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                if (settings.isOne()
                        && engine.getOneAmount() == 0
                        && (getFill() != Color.YELLOW && getFill() != Color.GREEN)) {
                    if (getFill() == Color.BLUE) {
                        setFill(Color.GREEN);
                        engine.increaseAmountByOne("one");
                        func.marketGreen(x, y);
                    }
                } else if (isSelectedSingleShip()) {
                    if (engine.getOneAmount() <= 4 && engine.getOneAmount() >= 1) {
                        makeSingleShip();
                    }
                } else if (isSelectedDoubleShip()) {
                    makeDoubleShipForThree();
                } else if (isSelectedTripleShip()) {
                    makeTripleShipForBoth();
                } else if (isSelectedQuadrupleShip()) {
                    makeQuadrupleShip();
                }
            }
        });
    }


    //Extract-function to choose a type of ship
    private boolean isSelectedSingleShip() {
        return settings.isOne()
                && engine.getOneAmount() != 0
                && (getFill() != Color.YELLOW);
    }

    private boolean isSelectedDoubleShip() {
        return settings.isTwo()
                && (getFill() != Color.YELLOW)
                && engine.getTwoAmount() != 0;
    }

    private boolean isSelectedTripleShip() {
        return settings.isThree()
                && (getFill() != Color.YELLOW)
                && engine.getThreeAmount() != 0;
    }

    private boolean isSelectedQuadrupleShip() {
        return settings.isFour()
                && (getFill() != Color.YELLOW)
                && engine.getFourAmount() != 0;
    }

    //Extract-function for foundation ship
    private void makeSingleShip() {
        engine.getShipSingle()[engine.getOneAmount()] = new ShipSingle(engine);
        ship = engine.getShipSingle()[engine.getOneAmount()];

        if (getFill() == Color.GREEN && engine.getShipSingle()[engine.getOneAmount()].make(x, y)) {
            setFill(Color.BLUE);
            func.marketYellow(x, y);
        } else {
            engine.getShipSingle()[engine.getOneAmount()].clean();
            func.marketGreen(x, y);
            setFill(Color.GREEN);
            ship = null;
        }
    }

    private void makeDoubleShipForThree() {
        if (engine.getCount2() == 0) {
            engine.getShipDouble()[engine.getTwoAmount()] = new ShipDouble(engine);
            ship = engine.getShipDouble()[engine.getTwoAmount()];
            if (engine.getShipDouble()[engine.getTwoAmount()].make(x, y)) {
                setFill(Color.BLUE);
            }
        } else if (engine.getCount2() == 1 && engine.getShipDouble()[engine.getTwoAmount()].make(x, y)) {
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            if (engine.getSaveX() == x) {
                func.marketYellow(x, y - 1);
                func.marketYellow(x, y + 1);
            } else if (engine.getSaveY() == y) {
                func.marketYellow(x - 1, y);
                func.marketYellow(x + 1, y);
            }
        }
    }

    private void makeTripleShipForBoth() {

        if (engine.getCount3() == 0 && engine.getThreeAmount() != -1) {
            engine.getShipTriple()[engine.getThreeAmount()] = new ShipTriple(engine);
            ship = engine.getShipTriple()[engine.getThreeAmount()];
            if (ship.make(x, y)) {
                setFill(Color.BLUE);
            }
        } else if (engine.getCount3() == 1 && engine.getThreeAmount() != -1) {
            if (engine.getShipTriple()[engine.getThreeAmount()].make(x, y)) {
                setFill(Color.BLUE);
            }
        } else if (engine.getCount3() == 2 && engine.getThreeAmount() != -1) {
            if (engine.getShipTriple()[engine.getThreeAmount()].make(x, y)) {
                setFill(Color.BLUE);
                func.marketYellow(engine.getSaveX(), engine.getSaveY());
                func.marketYellow(engine.getSaveX1(), engine.getSaveY1());
                func.marketYellow(x, y);
            }
        }
    }

    private void makeQuadrupleShip() {
        ship = engine.getShipQuadruple();
        if (engine.getCount4() == 0 && ship.make(x, y)) {
            setFill(Color.BLUE);
        } else if (engine.getCount4() == 1 && ship.make(x, y)) {
            setFill(Color.BLUE);
        } else if (engine.getCount4() == 2 && ship.make(x, y)) {
            setFill(Color.BLUE);
        } else if (engine.getCount4() == 3 && ship.make(x, y)) {
            setFill(Color.BLUE);
            func.marketYellow(engine.getSaveX(), engine.getSaveY());
            func.marketYellow(engine.getSaveX1(), engine.getSaveY1());
            func.marketYellow(engine.getSaveX2(), engine.getSaveY2());
            func.marketYellow(x, y);
        }
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