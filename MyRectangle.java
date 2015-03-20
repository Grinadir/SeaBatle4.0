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

    static Ship[] shipSingle = new Ship[5];
    static Ship[] shipDouble = new Ship[4];
    static Ship[] shipTriple = new Ship[3];
    static Ship shipQuadruple = new Ship(4);
    int x;
    int y;
    int veto = 0;
    Ship privateShip;


    public MyRectangle(double width, double height, int e) {
        setWidth(width);
        setHeight(height);


        this.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {

                if (Gui.no.isSelected()) {

                    if (getFill() == Color.RED) {

                        setFill(Color.GREEN);
                    } else {
                        setFill(Color.RED);
                    }


                }

                if (Gui.ranking.isSelected()
                        && Gui.one.isSelected()
                        && Gui.oneAmount == 0
                        && (getFill() != Color.YELLOW && getFill() != Color.GREEN)) {


                    if (getFill() == Color.BLUE) {

                        setFill(Color.GREEN);
                        Gui.oneAmount++;
                        FunctionsOfMarkedByDifferntColor.marketGreen(x, y);
                    }
// -1-

                } else if (isSelectedSingleShip()) {


                    switch (Gui.oneAmount) {
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

    //Extract функции
    //Выборы кораблей
    private boolean isSelectedSingleShip() {
        return Gui.ranking.isSelected() && Gui.one.isSelected()
                && Gui.oneAmount != 0
                && (getFill() != Color.YELLOW);
    }

    private boolean isSelectedDoubleShip() {
        return Gui.ranking.isSelected() && Gui.two.isSelected()
                && (getFill() != Color.YELLOW)
                && Gui.twoAmount != 0;
    }

    private boolean isSelectedTripleShip() {
        return Gui.ranking.isSelected() && Gui.three.isSelected()
                && (getFill() != Color.YELLOW)
                && Gui.threeAmount != 0;
    }

    private boolean isSelectedQuadrupleShip() {
        return Gui.ranking.isSelected() && Gui.four.isSelected()
                && (getFill() != Color.YELLOW)
                && Gui.fourAmount != 0;
    }


    //Функции создания кораблей
    private void makeSingleShip() {
        shipSingle[Gui.oneAmount] = new Ship(1);
        privateShip = shipSingle[Gui.oneAmount];

        if (getFill() == Color.GREEN) {

            shipSingle[Gui.oneAmount].setX1(x);
            shipSingle[Gui.oneAmount].setY1(y);
            setFill(Color.BLUE);
            FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
            Gui.oneAmount--;
        } else {
            FunctionsOfMarkedByDifferntColor.marketGreen(x, y);
            setFill(Color.GREEN);
            privateShip = null;
            shipSingle[Gui.oneAmount] = null;
            Gui.oneAmount++;
        }
        ;
    }

    private void makeDoubleShipForThree() {


        if (Gui.count2 == 0) {
            shipDouble[Gui.twoAmount] = new Ship(2);
            privateShip = shipDouble[Gui.twoAmount];
            shipDouble[Gui.twoAmount].setX1(x);
            shipDouble[Gui.twoAmount].setY1(y);

            Gui.saveX = x;
            Gui.saveY = y;
            setFill(Color.BLUE);
            ++Gui.count2;
        } else if ((Gui.saveX == x || Gui.saveY == y)
                && Gui.count2 != 0
                && (Gui.saveX == x + 1 || Gui.saveY == y + 1
                || Gui.saveX == x - 1 || Gui.saveY == y - 1)) {
            privateShip = shipDouble[Gui.twoAmount];
            shipDouble[Gui.twoAmount].setX2(x);
            shipDouble[Gui.twoAmount].setY2(y);

            setFill(Color.BLUE);
            FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
            if (Gui.saveX == x) {
                FunctionsOfMarkedByDifferntColor.marketYellow(x, y - 1);
                FunctionsOfMarkedByDifferntColor.marketYellow(x, y + 1);

            } else if (Gui.saveY == y) {
                FunctionsOfMarkedByDifferntColor.marketYellow(x - 1, y);
                FunctionsOfMarkedByDifferntColor.marketYellow(x + 1, y);
            }
            Gui.count2 = 0;
            Gui.twoAmount--;

        }
    }


    private void makeTripleShipForBoth() {


        if (Gui.count3 == 0) {
            shipTriple[Gui.threeAmount] = new Ship(3);
            privateShip = shipTriple[Gui.threeAmount];
            shipTriple[Gui.threeAmount].setX1(x);
            shipTriple[Gui.threeAmount].setY1(y);
            Gui.saveX = x;
            Gui.saveY = y;
            setFill(Color.BLUE);
            ++Gui.count3;
        } else if ((Gui.saveX == x || Gui.saveY == y)
                && Gui.count3 == 1
                && (Gui.saveX == x + 1 || Gui.saveY == y + 1
                || Gui.saveX == x - 1 || Gui.saveY == y - 1)
                && Gui.threeAmount != 0) {
            privateShip = shipTriple[Gui.threeAmount];
            shipTriple[Gui.threeAmount].setX2(x);
            shipTriple[Gui.threeAmount].setY2(y);
            setFill(Color.BLUE);
            Gui.saveX1 = x;
            Gui.saveY1 = y;

            ++Gui.count3;

        } else if ((Gui.saveX == x || Gui.saveY == y) && Gui.count3 == 2
                && Gui.threeAmount != 0) {


            if (Gui.saveX1 == x
                    && (Gui.saveY1 == y + 1 || Gui.saveY1 == y - 1)) {
                privateShip = shipTriple[Gui.threeAmount];
                setFill(Color.BLUE);
                shipTriple[Gui.threeAmount].setX3(x);
                shipTriple[Gui.threeAmount].setY3(y);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX, Gui.saveY);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX1, Gui.saveY1);
                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);

                Gui.count3 = 0;
                Gui.threeAmount--;

            } else if (Gui.saveY == y
                    && (Gui.saveX1 == x + 1 || Gui.saveX1 == x - 1)
                    && Gui.threeAmount != 0) {
                privateShip = shipTriple[Gui.threeAmount];
                setFill(Color.BLUE);
                shipTriple[Gui.threeAmount].setX3(x);
                shipTriple[Gui.threeAmount].setY3(y);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX, Gui.saveY);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX1, Gui.saveY1);
                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                Gui.count3 = 0;
                Gui.threeAmount--;

            }

        }
    }


    private void makeQuadrupleShip() {
        privateShip = shipQuadruple;

        if (Gui.count4 == 0) {


            shipQuadruple.setX1(x);
            shipQuadruple.setY1(y);
            Gui.saveX = x;
            Gui.saveY = y;
            setFill(Color.BLUE);
            ++Gui.count4;
        } else if ((Gui.saveX == x || Gui.saveY == y)
                && Gui.count4 == 1
                && (Gui.saveX == x + 1 || Gui.saveY == y + 1
                || Gui.saveX == x - 1 || Gui.saveY == y - 1)
                && Gui.fourAmount != 0) {
            setFill(Color.BLUE);
            shipQuadruple.setX2(x);
            shipQuadruple.setY2(y);
            Gui.saveX1 = x;
            Gui.saveY1 = y;

            ++Gui.count4;

        } else if ((Gui.saveX == x || Gui.saveY == y) && Gui.fourAmount != 0
                && Gui.count4 == 2) {

            if (Gui.saveX1 == x
                    && (Gui.saveY1 == y + 1 || Gui.saveY1 == y - 1)) {
                setFill(Color.BLUE);
                shipQuadruple.setX3(x);
                shipQuadruple.setY3(y);
                Gui.saveX2 = x;
                Gui.saveY2 = y;

                ++Gui.count4;

            } else if (Gui.saveY == y
                    && (Gui.saveX1 == x + 1 || Gui.saveX1 == x - 1)
                    && Gui.fourAmount != 0) {
                setFill(Color.BLUE);
                shipQuadruple.setX3(x);
                shipQuadruple.setY3(y);
                Gui.saveX2 = x;
                Gui.saveY2 = y;

                ++Gui.count4;

            }

        } else if ((Gui.saveX == x || Gui.saveY == y) && Gui.count4 == 3
                && Gui.fourAmount != 0) {

            if (Gui.saveX2 == x
                    && (Gui.saveY2 == y + 1 || Gui.saveY2 == y - 1)) {
                setFill(Color.BLUE);
                shipQuadruple.setX4(x);
                shipQuadruple.setY4(y);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX, Gui.saveY);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX1, Gui.saveY1);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX2, Gui.saveY2);
                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                Gui.count4 = 0;
                Gui.fourAmount--;

            } else if (Gui.saveY2 == y
                    && (Gui.saveX2 == x + 1 || Gui.saveX2 == x - 1)
                    && Gui.threeAmount != 0) {
                setFill(Color.BLUE);
                shipQuadruple.setX4(x);
                shipQuadruple.setY4(y);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX, Gui.saveY);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX1, Gui.saveY1);
                FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX2, Gui.saveY2);
                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                Gui.count4 = 0;
                Gui.fourAmount--;

            }

        }
    }
}