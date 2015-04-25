package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Необходим для создания единичного квадратика
 * со своими параметрами
 */

public class MyRectangle extends Rectangle {

    private int x;
    private int y;
    private int veto = 0;
    private Ship privateShip;
    private Engine engine;
    private final Settings settings;
    private FunctionsOfMarkedByDifferentColor func;

    public MyRectangle(Settings settings, final Engine engine, double width, double height, int e) {
        this.settings=settings;
        this.engine = engine;
        setWidth(width);
        setHeight(height);
        this.func = new FunctionsOfMarkedByDifferentColor(engine.getRects());

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {

                if (settings.isNo()) {
                    if (getFill() == Color.RED) {
                        setFill(Color.GREEN);
                    } else {
                        setFill(Color.RED);
                    }
                }

                if (settings.isRanking()
                        && settings.isOne()
                        && engine.getOneAmount() == 0
                        && (getFill() != Color.YELLOW && getFill() != Color.GREEN)) {
                    if (getFill() == Color.BLUE) {
                        setFill(Color.GREEN);
                        engine.setOneAmount(engine.getOneAmount() + 1);
                        func.marketGreen(x, y);
                    }
// -1-

                } else if (isSelectedSingleShip()) {
                    if (engine.getOneAmount()<=4&&engine.getOneAmount()>=1) {
                            makeSingleShip();
                    }
                }

// -2-
                else if (isSelectedDoubleShip()) {
                    makeDoubleShipForThree();
                }
// -3-
                else if (isSelectedTripleShip()) {
                    makeTripleShipForBoth();
                }

// -4-
                else if (isSelectedQuadrupleShip()) {
                    makeQuadrupleShip();
                }
            }
        });
        // Слушатель на кнопку старт
    }


    //Extract функции выборов кораблей
    private boolean isSelectedSingleShip() {
        return settings.isRanking() && settings.isOne()
                && engine.getOneAmount() != 0
                && (getFill() != Color.YELLOW);
    }

    private boolean isSelectedDoubleShip() {
        return settings.isRanking() && settings.isTwo()
                && (getFill() != Color.YELLOW)
                && engine.getTwoAmount() != 0;
    }

    private boolean isSelectedTripleShip() {
        return settings.isRanking() && settings.isThree()
                && (getFill() != Color.YELLOW)
                && engine.getThreeAmount() != 0;
    }

    private boolean isSelectedQuadrupleShip() {
        return settings.isRanking() && settings.isFour()
                && (getFill() != Color.YELLOW)
                && engine.getFourAmount() != 0;
    }

    //Extract функций создания кораблей
    private void makeSingleShip() {
        engine.getShipSingle()[engine.getOneAmount()] = new Ship(1);
        privateShip = engine.getShipSingle()[engine.getOneAmount()];
        if (getFill() == Color.GREEN) {
            privateShip.setX1(x);
            privateShip.setY1(y);
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            engine.setOneAmount(engine.getOneAmount() - 1);
        } else {
            func.marketGreen(x, y);
            setFill(Color.GREEN);
            privateShip = null;
            engine.setOneAmount(engine.getOneAmount() + 1);
        }
    }

    private void makeDoubleShipForThree() {
        if (engine.getCount2() == 0) {
            engine.getShipDouble()[engine.getTwoAmount()] = new Ship(2);
            privateShip = engine.getShipDouble()[engine.getTwoAmount()];
            privateShip.setX1(x);
            privateShip.setY1(y);
            engine.setSaveX(x);
            engine.setSaveY(y);
            setFill(Color.BLUE);
            engine.setCount2(engine.getCount2() + 1);
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y)
                && engine.getCount2() != 0
                && (engine.getSaveX() == x + 1 || engine.getSaveY() == y + 1
                || engine.getSaveX() == x - 1 || engine.getSaveY() == y - 1)) {
            privateShip = engine.getShipDouble()[engine.getTwoAmount()];
            privateShip.setX2(x);
            privateShip.setY2(y);
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            if (engine.getSaveX() == x) {
                func.marketYellow(x, y - 1);
                func.marketYellow(x, y + 1);
            } else if (engine.getSaveY() == y) {
                func.marketYellow(x - 1, y);
                func.marketYellow(x + 1, y);
            }
            engine.setCount2(0);
            engine.setTwoAmount(engine.getTwoAmount() - 1);
        }
    }

    private void makeTripleShipForBoth() {
        if (engine.getCount3() == 0) {
            engine.getShipTriple()[engine.getThreeAmount()] = new Ship(3);
            privateShip = engine.getShipTriple()[engine.getThreeAmount()];
            privateShip.setX1(x);
            privateShip.setY1(y);
            engine.setSaveX(x);
            engine.setSaveY(y);
            setFill(Color.BLUE);
            engine.setCount3(engine.getCount3() + 1);
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y)
                && engine.getCount3() == 1
                && (engine.getSaveX() == x + 1 || engine.getSaveY() == y + 1
                || engine.getSaveX() == x - 1 || engine.getSaveY() == y - 1)
                && engine.getThreeAmount() != 0) {
            privateShip = engine.getShipTriple()[engine.getThreeAmount()];
            privateShip.setX2(x);
            privateShip.setY2(y);
            setFill(Color.BLUE);
            engine.setSaveX1(x);
            engine.setSaveY1(y);
            engine.setCount3(engine.getCount3() + 1);
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y) && engine.getCount3() == 2
                && engine.getThreeAmount() != 0) {
            if (engine.getSaveX1() == x
                    && (engine.getSaveY1() == y + 1 || engine.getSaveY1() == y - 1)) {
                privateShip = engine.getShipTriple()[engine.getThreeAmount()];
                setFill(Color.BLUE);
                privateShip.setX3(x);
                privateShip.setY3(y);
                func.marketYellow(engine.getSaveX(), engine.getSaveY());
                func.marketYellow(engine.getSaveX1(), engine.getSaveY1());
                func.marketYellow(x, y);
                engine.setCount3(0);
                engine.setThreeAmount(engine.getThreeAmount() - 1);
            } else if (engine.getSaveY() == y
                    && (engine.getSaveX1() == x + 1 || engine.getSaveX1() == x - 1)
                    && engine.getThreeAmount() != 0) {
                privateShip = engine.getShipTriple()[engine.getThreeAmount()];
                setFill(Color.BLUE);
                privateShip.setX3(x);
                privateShip.setY3(y);
                func.marketYellow(engine.getSaveX(), engine.getSaveY());
                func.marketYellow(engine.getSaveX1(), engine.getSaveY1());
                func.marketYellow(x, y);
                engine.setCount3(0);
                engine.setThreeAmount(engine.getThreeAmount() - 1);
            }
        }
    }

    private void makeQuadrupleShip() {
        privateShip = engine.getShipQuadruple();
        if (engine.getCount4() == 0) {
            privateShip.setX1(x);
            privateShip.setY1(y);
            engine.setSaveX(x);
            engine.setSaveY(y);
            setFill(Color.BLUE);
            engine.setCount4(engine.getCount4() + 1);
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y)
                && engine.getCount4() == 1
                && (engine.getSaveX() == x + 1 || engine.getSaveY() == y + 1
                || engine.getSaveX() == x - 1 || engine.getSaveY() == y - 1)
                && engine.getFourAmount() != 0) {
            setFill(Color.BLUE);
            privateShip.setX2(x);
            privateShip.setY2(y);
            engine.setSaveX1(x);
            engine.setSaveY1(y);
            engine.setCount4(engine.getCount4() + 1);
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y) && engine.getFourAmount() != 0
                && engine.getCount4() == 2) {
            if (engine.getSaveX1() == x
                    && (engine.getSaveY1() == y + 1 || engine.getSaveY1() == y - 1)) {
                setFill(Color.BLUE);
                privateShip.setX3(x);
                privateShip.setY3(y);
                engine.setSaveX2(x);
                engine.setSaveY2(y);
                engine.setCount4(engine.getCount4() + 1);
            } else if (engine.getSaveY() == y
                    && (engine.getSaveX1() == x + 1 || engine.getSaveX1() == x - 1)
                    && engine.getFourAmount() != 0) {
                setFill(Color.BLUE);
                privateShip.setX3(x);
                privateShip.setY3(y);
                engine.setSaveX2(x);
                engine.setSaveY2(y);
                engine.setCount4(engine.getCount4() + 1);
            }
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y) && engine.getCount4() == 3
                && engine.getFourAmount() != 0) {
            if (engine.getSaveX2() == x
                    && (engine.getSaveY2() == y + 1 || engine.getSaveY2() == y - 1)) {
                setFill(Color.BLUE);
                privateShip.setX4(x);
                privateShip.setY4(y);
                func.marketYellow(engine.getSaveX(), engine.getSaveY());
                func.marketYellow(engine.getSaveX1(), engine.getSaveY1());
                func.marketYellow(engine.getSaveX2(), engine.getSaveY2());
                func.marketYellow(x, y);
                engine.setCount4(0);
                engine.setFourAmount(engine.getFourAmount() - 1);
            } else if (engine.getSaveY2() == y
                    && (engine.getSaveX2() == x + 1 || engine.getSaveX2() == x - 1)
                    && engine.getThreeAmount() != 0) {
                setFill(Color.BLUE);
                privateShip.setX4(x);
                privateShip.setY4(y);
                func.marketYellow(engine.getSaveX(), engine.getSaveY());
                func.marketYellow(engine.getSaveX1(), engine.getSaveY1());
                func.marketYellow(engine.getSaveX2(), engine.getSaveY2());
                func.marketYellow(x, y);
                engine.setCount4(0);
                engine.setFourAmount(engine.getFourAmount() - 1);
            }
        }
    }

    //Геттеры и сеттеры
    public Ship getPrivateShip() {
        return privateShip;
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