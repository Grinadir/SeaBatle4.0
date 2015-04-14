package sample;

/**
 * Created by Selkov Alexsandr on 22.02.2015.
 */


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends Rectangle {

    /*
    *Класс MyRectangle
    *Необходим для создания единичного квадратика
    *своими параметрами
    *Изначально создан в Eclipse
    * Рефактринг сделаю позже
    */

    private int x;
    private int y;
    private int veto = 0;
    private Ship PrivateShip;
    private final Gui gui;
    private FunctionsOfMarkedByDifferentColor func;
    private Counters count;


    public MyRectangle(final Gui gui, final Counters count, double width, double height, int e) {
        this.count=count;
        this.gui=gui;
        setWidth(width);
        setHeight(height);
        this.func=new FunctionsOfMarkedByDifferentColor(gui);

        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {

                if (gui.getNo().isSelected()) {
                    if (getFill() == Color.RED) {
                        setFill(Color.GREEN);
                    } else {
                        setFill(Color.RED);
                    }
                }

                if (gui.getRanking().isSelected()
                        && gui.getOne().isSelected()
                        && count.getOneAmount() == 0
                        && (getFill() != Color.YELLOW && getFill() != Color.GREEN)) {
                    if (getFill() == Color.BLUE) {
                        setFill(Color.GREEN);
                        count.setOneAmount(count.getOneAmount()+1);
                        func.marketGreen(x, y);
                    }
// -1-

                } else if (isSelectedSingleShip()) {
                    switch (count.getOneAmount()) {
                        case 4:
                            makeSingleShip();
                            break;
                        case 3:
                            makeSingleShip();
                            break;
                        case 2:
                            makeSingleShip();
                            break;
                        case 1:
                            makeSingleShip();
                            break;
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
        return gui.getRanking().isSelected() && gui.getOne().isSelected()
                && count.getOneAmount() != 0
                && (getFill() != Color.YELLOW);
    }

    private boolean isSelectedDoubleShip() {
        return gui.getRanking().isSelected() && gui.getTwo().isSelected()
                && (getFill() != Color.YELLOW)
                && count.getTwoAmount() != 0;
    }

    private boolean isSelectedTripleShip() {
        return gui.getRanking().isSelected() && gui.getThree().isSelected()
                && (getFill() != Color.YELLOW)
                && count.getThreeAmount() != 0;
    }

    private boolean isSelectedQuadrupleShip() {
        return gui.getRanking().isSelected() && gui.getFour().isSelected()
                && (getFill() != Color.YELLOW)
                && count.getFourAmount() != 0;
    }

    //Extract функций создания кораблей
    private void makeSingleShip() {
        count.getShipSingle()[count.getOneAmount()] = new Ship(1);
        PrivateShip = count.getShipSingle()[count.getOneAmount()];
        if (getFill() == Color.GREEN) {
            PrivateShip.setX1(x);
            PrivateShip.setY1(y);
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            count.setOneAmount(count.getOneAmount()-1);
        } else {
            func.marketGreen(x, y);
            setFill(Color.GREEN);
            PrivateShip = null;
            count.setOneAmount(count.getOneAmount()+1);
        }
    }

    private void makeDoubleShipForThree() {
        if (count.getCount2() == 0) {
            count.getShipDouble()[count.getTwoAmount()] = new Ship(2);
            PrivateShip = count.getShipDouble()[count.getTwoAmount()];
            PrivateShip.setX1(x);
            PrivateShip.setY1(y);
            count.setSaveX(x);
            count.setSaveY(y);
            setFill(Color.BLUE);
            count.setCount2(count.getCount2()+1);
        } else if ((count.getSaveX() == x || count.getSaveY() == y)
                && count.getCount2() != 0
                && (count.getSaveX() == x + 1 || count.getSaveY() == y + 1
                || count.getSaveX() == x - 1 || count.getSaveY() == y - 1)) {
            PrivateShip = count.getShipDouble()[count.getTwoAmount()];
            PrivateShip.setX2(x);
            PrivateShip.setY2(y);
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            if (count.getSaveX() == x) {
                func.marketYellow(x, y - 1);
                func.marketYellow(x, y + 1);
            } else if (count.getSaveY() == y) {
                func.marketYellow(x - 1, y);
                func.marketYellow(x + 1, y);
            }
            count.setCount2(0);
            count.setTwoAmount(count.getTwoAmount()-1);
        }
    }

    private void makeTripleShipForBoth() {
        if (count.getCount3() == 0) {
            count.getShipTriple()[count.getThreeAmount()] = new Ship(3);
            PrivateShip = count.getShipTriple()[count.getThreeAmount()];
            PrivateShip.setX1(x);
            PrivateShip.setY1(y);
            count.setSaveX(x);
            count.setSaveY(y);
            setFill(Color.BLUE);
            count.setCount3(count.getCount3()+1);
        } else if ((count.getSaveX() == x || count.getSaveY() == y)
                && count.getCount3() == 1
                && (count.getSaveX() == x + 1 || count.getSaveY() == y + 1
                || count.getSaveX() == x - 1 || count.getSaveY() == y - 1)
                && count.getThreeAmount() != 0) {
            PrivateShip = count.getShipTriple()[count.getThreeAmount()];
            PrivateShip.setX2(x);
            PrivateShip.setY2(y);
            setFill(Color.BLUE);
            count.setSaveX1(x);
            count.setSaveY1(y);
            count.setCount3(count.getCount3() + 1);
        } else if ((count.getSaveX() == x || count.getSaveY() == y) && count.getCount3() == 2
                && count.getThreeAmount() != 0) {
            if (count.getSaveX1() == x
                    && (count.getSaveY1() == y + 1 || count.getSaveY1() == y - 1)) {
                PrivateShip = count.getShipTriple()[count.getThreeAmount()];
                setFill(Color.BLUE);
                PrivateShip.setX3(x);
                PrivateShip.setY3(y);
                func.marketYellow(count.getSaveX(), count.getSaveY());
                func.marketYellow(count.getSaveX1(), count.getSaveY1());
                func.marketYellow(x, y);
                count.setCount3(0);
                count.setThreeAmount(count.getThreeAmount()-1);
            } else if (count.getSaveY() == y
                    && (count.getSaveX1() == x + 1 || count.getSaveX1() == x - 1)
                    && count.getThreeAmount() != 0) {
                PrivateShip = count.getShipTriple()[count.getThreeAmount()];
                setFill(Color.BLUE);
                PrivateShip.setX3(x);
                PrivateShip.setY3(y);
                func.marketYellow(count.getSaveX(), count.getSaveY());
                func.marketYellow(count.getSaveX1(), count.getSaveY1());
                func.marketYellow(x, y);
                count.setCount3(0);
                count.setThreeAmount(count.getThreeAmount()-1);
            }
        }
    }

    private void makeQuadrupleShip() {
        PrivateShip = count.getShipQuadruple();
        if (count.getCount4() == 0) {
            PrivateShip.setX1(x);
            PrivateShip.setY1(y);
            count.setSaveX (x);
            count.setSaveY (y);
            setFill(Color.BLUE);
            count.setCount4(count.getCount4()+1);
        } else if ((count.getSaveX() == x || count.getSaveY() == y)
                && count.getCount4() == 1
                && (count.getSaveX() == x + 1 || count.getSaveY() == y + 1
                || count.getSaveX() == x - 1 || count.getSaveY() == y - 1)
                && count.getFourAmount() != 0) {
            setFill(Color.BLUE);
            PrivateShip.setX2(x);
            PrivateShip.setY2(y);
            count.setSaveX1(x);
            count.setSaveY1(y);
            count.setCount4(count.getCount4()+1);
        } else if ((count.getSaveX() == x || count.getSaveY() == y) && count.getFourAmount() != 0
                && count.getCount4() == 2) {
            if (count.getSaveX1() == x
                    && (count.getSaveY1() == y + 1 || count.getSaveY1() == y - 1)) {
                setFill(Color.BLUE);
                PrivateShip.setX3(x);
                PrivateShip.setY3(y);
                count.setSaveX2(x);
                count.setSaveY2(y);
                count.setCount4(count.getCount4() + 1);
            } else if (count.getSaveY() == y
                    && (count.getSaveX1() == x + 1 || count.getSaveX1() == x - 1)
                    && count.getFourAmount() != 0) {
                setFill(Color.BLUE);
                PrivateShip.setX3(x);
                PrivateShip.setY3(y);
                count.setSaveX2(x);
                count.setSaveY2(y);
                count.setCount4(count.getCount4()+1);
            }
        } else if ((count.getSaveX() == x || count.getSaveY() == y) && count.getCount4() == 3
                && count.getFourAmount() != 0) {
            if (count.getSaveX2() == x
                    && (count.getSaveY2() == y + 1 || count.getSaveY2() == y - 1)) {
                setFill(Color.BLUE);
                PrivateShip.setX4(x);
                PrivateShip.setY4(y);
                func.marketYellow(count.getSaveX(), count.getSaveY());
                func.marketYellow(count.getSaveX1(), count.getSaveY1());
                func.marketYellow(count.getSaveX2(), count.getSaveY2());
                func.marketYellow(x, y);
                count.setCount4 (0);
                count.setFourAmount(count.getFourAmount()-1);
            } else if (count.getSaveY2() == y
                    && (count.getSaveX2() == x + 1 || count.getSaveX2() == x - 1)
                    && count.getThreeAmount() != 0) {
                setFill(Color.BLUE);
                PrivateShip.setX4(x);
                PrivateShip.setY4(y);
                func.marketYellow(count.getSaveX(), count.getSaveY());
                func.marketYellow(count.getSaveX1(), count.getSaveY1());
                func.marketYellow(count.getSaveX2(), count.getSaveY2());
                func.marketYellow(x, y);
                count.setCount4 (0);
                count.setFourAmount(count.getFourAmount()-1);
            }
        }
    }

    //Геттеры и сеттеры
    public Ship getPrivateShip(){
        return PrivateShip;
    }
    public void setXinMyRect(int x){
        this.x=x;
    }
    public void setYinMyRect(int y){
        this.y=y;
    }
    public int getVeto(){
        return this.veto;
    }
    public void setVeto(int veto){
        this.veto=veto;
    }
}