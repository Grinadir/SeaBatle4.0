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

    int x;
    int y;
    int veto = 0;
    int type = 0;

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

                } else if (Gui.ranking.isSelected() && Gui.one.isSelected()
                        && Gui.oneAmount != 0
                        && (getFill() != Color.YELLOW)) {


                    switch (Gui.oneAmount) {
                        case 4:
                            System.out.println(Gui.oneAmount);


                            type = 1;


                            if (getFill() == Color.GREEN) {

                                setFill(Color.BLUE);
                                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);

                                Gui.oneAmount--;
                            } else {
                                FunctionsOfMarkedByDifferntColor.marketGreen(x, y);
                                setFill(Color.GREEN);
                                System.out
                                        .println("else i+1 " + Gui.rectMY[1].veto);
                                System.out.println("else i+10 "
                                        + Gui.rectMY[10].veto);
                                System.out.println("else i+11 "
                                        + Gui.rectMY[11].veto);
                                Gui.oneAmount++;
                            }
                            ;
                            break;

                        case 3:
                            System.out.println(Gui.oneAmount);
                            // o2.x = x;
                            // o2.y = y;
                            type = 1;

                            if (getFill() == Color.GREEN) {
                                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                                setFill(Color.BLUE);

                                Gui.oneAmount--;
                            } else {

                                FunctionsOfMarkedByDifferntColor.marketGreen(x, y);

                                setFill(Color.GREEN);
                                Gui.oneAmount++;
                            }
                            ;
                            break;

                        case 2:
                            type = 1;

                            if (getFill() == Color.GREEN) {
                                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);

                                setFill(Color.BLUE);
                                Gui.oneAmount--;
                            } else {
                                FunctionsOfMarkedByDifferntColor.marketGreen(x, y);
                                setFill(Color.GREEN);
                                Gui.oneAmount++;
                            }
                            ;
                            break;
                        case 1:
                            if (getFill() == Color.GREEN) {
                                System.out.println("oneAmount");
                                FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                                setFill(Color.BLUE);
                                Gui.oneAmount--;
                            } else {
                                FunctionsOfMarkedByDifferntColor.marketGreen(x, y);
                                setFill(Color.GREEN);
                                Gui.oneAmount++;
                            }
                            ;
                            break;

                    }

                }

                // ///////////////////////////////////////////////////
                else if (Gui.ranking.isSelected() && Gui.two.isSelected()
                        && (getFill() != Color.YELLOW)
                        && Gui.twoAmount != 0) {

                    if (Gui.count2 == 0) {
                        Gui.saveX = x;
                        Gui.saveY = y;
                        setFill(Color.BLUE);
                        ++Gui.count2;
                    } else if ((Gui.saveX == x || Gui.saveY == y)
                            && Gui.count2 != 0
                            && (Gui.saveX == x + 1 || Gui.saveY == y + 1
                            || Gui.saveX == x - 1 || Gui.saveY == y - 1)) {
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
                // ////////////////////////////////////////////////////////////

                else if (Gui.ranking.isSelected() && Gui.three.isSelected()
                        && (getFill() != Color.YELLOW)
                        && Gui.threeAmount != 0) {

                    if (Gui.count3 == 0) {
                        Gui.saveX = x;
                        Gui.saveY = y;
                        setFill(Color.BLUE);
                        ++Gui.count3;
                    } else if ((Gui.saveX == x || Gui.saveY == y)
                            && Gui.count3 == 1
                            && (Gui.saveX == x + 1 || Gui.saveY == y + 1
                            || Gui.saveX == x - 1 || Gui.saveY == y - 1)
                            && Gui.threeAmount != 0) {
                        setFill(Color.BLUE);
                        Gui.saveX1 = x;
                        Gui.saveY1 = y;

                        ++Gui.count3;

                    } else if ((Gui.saveX == x || Gui.saveY == y) && Gui.count3 == 2
                            && Gui.threeAmount != 0) {

                        if (Gui.saveX1 == x
                                && (Gui.saveY1 == y + 1 || Gui.saveY1 == y - 1)) {
                            setFill(Color.BLUE);
                            FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX, Gui.saveY);
                            FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX1, Gui.saveY1);
                            FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                            Gui.count3 = 0;
                            Gui.threeAmount--;

                        } else if (Gui.saveY == y
                                && (Gui.saveX1 == x + 1 || Gui.saveX1 == x - 1)
                                && Gui.threeAmount != 0) {
                            setFill(Color.BLUE);
                            FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX, Gui.saveY);
                            FunctionsOfMarkedByDifferntColor.marketYellow(Gui.saveX1, Gui.saveY1);
                            FunctionsOfMarkedByDifferntColor.marketYellow(x, y);
                            Gui.count3 = 0;
                            Gui.threeAmount--;

                        }

                    }

                }

                // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                else if (Gui.ranking.isSelected() && Gui.four.isSelected()
                        && (getFill() != Color.YELLOW)
                        && Gui.fourAmount != 0) {

                    if (Gui.count4 == 0) {
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
                        Gui.saveX1 = x;
                        Gui.saveY1 = y;

                        ++Gui.count4;

                    } else if ((Gui.saveX == x || Gui.saveY == y) && Gui.fourAmount != 0
                            && Gui.count4 == 2) {

                        if (Gui.saveX1 == x
                                && (Gui.saveY1 == y + 1 || Gui.saveY1 == y - 1)) {
                            setFill(Color.BLUE);
                            Gui.saveX2 = x;
                            Gui.saveY2 = y;

                            ++Gui.count4;

                        } else if (Gui.saveY == y
                                && (Gui.saveX1 == x + 1 || Gui.saveX1 == x - 1)
                                && Gui.fourAmount != 0) {
                            setFill(Color.BLUE);
                            Gui.saveX2 = x;
                            Gui.saveY2 = y;

                            ++Gui.count4;

                        }

                    } else if ((Gui.saveX == x || Gui.saveY == y) && Gui.count4 == 3
                            && Gui.fourAmount != 0) {

                        if (Gui.saveX2 == x
                                && (Gui.saveY2 == y + 1 || Gui.saveY2 == y - 1)) {
                            setFill(Color.BLUE);
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

        });

        // Слушатель на кнопку старт

    }

}