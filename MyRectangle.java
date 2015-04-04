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

    private static Ship[] shipSingle = new Ship[5];//Эту статику не могу убрать, так как она должна быть по логике
    private static Ship[] shipDouble = new Ship[4];//Эту статику не могу убрать, так как она должна быть по логике
    private static Ship[] shipTriple = new Ship[3];//Эту статику не могу убрать, так как она должна быть по логике
    private static Ship shipQuadruple = new Ship(4);//Эту статику не могу убрать, так как она должна быть по логике

    private static int count2 = 0;//Эту статику не могу убрать, так как она должна быть по логике
    private static int count3 = 0;//Эту статику не могу убрать, так как она должна быть по логике
    private static int count4 = 0;//Эту статику не могу убрать, так как она должна быть по логике

    private static int oneAmount = 4;//Эту статику не могу убрать, так как она должна быть по логике
    private static int twoAmount = 3;//Эту статику не могу убрать, так как она должна быть по логике
    private static int threeAmount = 2;//Эту статику не могу убрать, так как она должна быть по логике
    private static int fourAmount = 1;//Эту статику не могу убрать, так как она должна быть по логике

    private static int saveX;//Эту статику не могу убрать, так как она должна быть по логике
    private static int saveY;//Эту статику не могу убрать, так как она должна быть по логике
    public static int saveX1;//Эту статику не могу убрать, так как она должна быть по логике
    public static int saveY1;//Эту статику не могу убрать, так как она должна быть по логике
    public static int saveX2;//Эту статику не могу убрать, так как она должна быть по логике
    public static int saveY2;//Эту статику не могу убрать, так как она должна быть по логике

    private int x;
    private int y;
    private int veto = 0;
    private Ship getPrivateShip;
    private final Gui gui;
    private FunctionsOfMarkedByDifferentColor func;


    public MyRectangle(final Gui gui, double width, double height, int e) {
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
                        && oneAmount == 0
                        && (getFill() != Color.YELLOW && getFill() != Color.GREEN)) {


                    if (getFill() == Color.BLUE) {

                        setFill(Color.GREEN);
                        oneAmount++;
                        func.marketGreen(x, y);
                    }
// -1-

                } else if (isSelectedSingleShip()) {


                    switch (oneAmount) {
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

    //
    //Extract функции выборов кораблей
    private boolean isSelectedSingleShip() {
        return gui.getRanking().isSelected() && gui.getOne().isSelected()
                && oneAmount != 0
                && (getFill() != Color.YELLOW);
    }

    private boolean isSelectedDoubleShip() {
        return gui.getRanking().isSelected() && gui.getTwo().isSelected()
                && (getFill() != Color.YELLOW)
                && twoAmount != 0;
    }

    private boolean isSelectedTripleShip() {
        return gui.getRanking().isSelected() && gui.getThree().isSelected()
                && (getFill() != Color.YELLOW)
                && threeAmount != 0;
    }

    private boolean isSelectedQuadrupleShip() {
        return gui.getRanking().isSelected() && gui.getFour().isSelected()
                && (getFill() != Color.YELLOW)
                && fourAmount != 0;
    }


    //Extract функций создания кораблей
    private void makeSingleShip() {
        shipSingle[oneAmount] = new Ship(1);
        getPrivateShip = shipSingle[oneAmount];

        if (getFill() == Color.GREEN) {

            shipSingle[oneAmount].setX1(x);
            shipSingle[oneAmount].setY1(y);
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            oneAmount--;
        } else {
            func.marketGreen(x, y);
            setFill(Color.GREEN);
            getPrivateShip = null;
            shipSingle[oneAmount] = null;
            oneAmount++;
        }
        ;
    }

    private void makeDoubleShipForThree() {
        if (count2 == 0) {
            shipDouble[twoAmount] = new Ship(2);
            getPrivateShip = shipDouble[twoAmount];
            shipDouble[twoAmount].setX1(x);
            shipDouble[twoAmount].setY1(y);
            saveX = x;
            saveY = y;
            setFill(Color.BLUE);
            ++count2;
        } else if ((saveX == x || saveY == y)
                && count2 != 0
                && (saveX == x + 1 || saveY == y + 1
                || saveX == x - 1 || saveY == y - 1)) {
            getPrivateShip = shipDouble[twoAmount];
            shipDouble[twoAmount].setX2(x);
            shipDouble[twoAmount].setY2(y);
            setFill(Color.BLUE);
            func.marketYellow(x, y);
            if (saveX == x) {
                func.marketYellow(x, y - 1);
                func.marketYellow(x, y + 1);
            } else if (saveY == y) {
                func.marketYellow(x - 1, y);
                func.marketYellow(x + 1, y);
            }
            count2 = 0;
            twoAmount--;
        }
    }


    private void makeTripleShipForBoth() {
        if (count3 == 0) {
            shipTriple[threeAmount] = new Ship(3);
            getPrivateShip = shipTriple[threeAmount];
            shipTriple[threeAmount].setX1(x);
            shipTriple[threeAmount].setY1(y);
            saveX = x;
            saveY = y;
            setFill(Color.BLUE);
            ++count3;
        } else if ((saveX == x || saveY == y)
                && count3 == 1
                && (saveX == x + 1 || saveY == y + 1
                || saveX == x - 1 || saveY == y - 1)
                && threeAmount != 0) {
            getPrivateShip = shipTriple[threeAmount];
            shipTriple[threeAmount].setX2(x);
            shipTriple[threeAmount].setY2(y);
            setFill(Color.BLUE);
            saveX1 = x;
            saveY1 = y;
            ++count3;
        } else if ((saveX == x || saveY == y) && count3 == 2
                && threeAmount != 0) {
            if (saveX1 == x
                    && (saveY1 == y + 1 || saveY1 == y - 1)) {
                getPrivateShip = shipTriple[threeAmount];
                setFill(Color.BLUE);
                shipTriple[threeAmount].setX3(x);
                shipTriple[threeAmount].setY3(y);
                func.marketYellow(saveX, saveY);
                func.marketYellow(saveX1, saveY1);
                func.marketYellow(x, y);
                count3 = 0;
                threeAmount--;
            } else if (saveY == y
                    && (saveX1 == x + 1 || saveX1 == x - 1)
                    && threeAmount != 0) {
                getPrivateShip = shipTriple[threeAmount];
                setFill(Color.BLUE);
                shipTriple[threeAmount].setX3(x);
                shipTriple[threeAmount].setY3(y);
                func.marketYellow(saveX, saveY);
                func.marketYellow(saveX1, saveY1);
                func.marketYellow(x, y);
                count3 = 0;
                threeAmount--;
            }
        }
    }


    private void makeQuadrupleShip() {
        getPrivateShip = shipQuadruple;
        if (count4 == 0) {
            shipQuadruple.setX1(x);
            shipQuadruple.setY1(y);
            saveX = x;
            saveY = y;
            setFill(Color.BLUE);
            ++count4;
        } else if ((saveX == x || saveY == y)
                && count4 == 1
                && (saveX == x + 1 || saveY == y + 1
                || saveX == x - 1 || saveY == y - 1)
                && fourAmount != 0) {
            setFill(Color.BLUE);
            shipQuadruple.setX2(x);
            shipQuadruple.setY2(y);
            saveX1 = x;
            saveY1 = y;
            ++count4;
        } else if ((saveX == x || saveY == y) && fourAmount != 0
                && count4 == 2) {
            if (saveX1 == x
                    && (saveY1 == y + 1 || saveY1 == y - 1)) {
                setFill(Color.BLUE);
                shipQuadruple.setX3(x);
                shipQuadruple.setY3(y);
                saveX2 = x;
                saveY2 = y;
                ++count4;
            } else if (saveY == y
                    && (saveX1 == x + 1 || saveX1 == x - 1)
                    && fourAmount != 0) {
                setFill(Color.BLUE);
                shipQuadruple.setX3(x);
                shipQuadruple.setY3(y);
                saveX2 = x;
                saveY2 = y;
                ++count4;
            }

        } else if ((saveX == x || saveY == y) && count4 == 3
                && fourAmount != 0) {
            if (saveX2 == x
                    && (saveY2 == y + 1 || saveY2 == y - 1)) {
                setFill(Color.BLUE);
                shipQuadruple.setX4(x);
                shipQuadruple.setY4(y);
                func.marketYellow(saveX, saveY);
                func.marketYellow(saveX1, saveY1);
                func.marketYellow(saveX2, saveY2);
                func.marketYellow(x, y);
                count4 = 0;
                fourAmount--;
            } else if (saveY2 == y
                    && (saveX2 == x + 1 || saveX2 == x - 1)
                    && threeAmount != 0) {
                setFill(Color.BLUE);
                shipQuadruple.setX4(x);
                shipQuadruple.setY4(y);
                func.marketYellow(saveX, saveY);
                func.marketYellow(saveX1, saveY1);
                func.marketYellow(saveX2, saveY2);
                func.marketYellow(x, y);
                count4 = 0;
                fourAmount--;
            }
        }
    }

    //Геттеры и сеттеры
    public Ship getPrivateShip(){
        return getPrivateShip;
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