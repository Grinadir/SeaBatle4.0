package sample;

/**
 * Created by User on 22.02.2015.
 */


import javafx.scene.paint.Color;

public class FunctionsOfMarkedByDifferntColor {
         /*
    * Класс Function
    * нужен для того, чтобы хранить функции для раскрашивания в разные цвета
    * квадратов
    *
    */

    public static void setColorYellowRect(int i) {

        if (!(Gui.rectMY[i].getFill() == Color.BLUE)
                && !(Gui.rectMY[i].getFill() == Color.YELLOW)) {


            Gui.rectMY[i].setFill(Color.YELLOW);
        } else {
            ;
        }
    }

    public static void setVeto(int s) {

        if (Gui.rectMY[s].veto == 1) {

            Gui.rectMY[s].setFill(Color.GREEN);
            Gui.rectMY[s].veto--;

        } else {


            Gui.rectMY[s].veto--;
        }
    }

    public static void marketYellow(int x, int y) {
        int i = y * 10 + x;
        if (!(Gui.rectMY[i].getFill() == Color.YELLOW)) {

            try {

                if (x == 0 && y == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 11);
                    Gui.rectMY[i + 1].veto++;
                    Gui.rectMY[i + 10].veto++;
                    Gui.rectMY[i + 11].veto++;


                } else if (x == 0 && y == 9) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    Gui.rectMY[i + 1].veto++;
                    Gui.rectMY[i - 10].veto++;
                    Gui.rectMY[i - 9].veto++;

                } else if (x == 9 && y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 11);

                    Gui.rectMY[i - 1].veto++;
                    Gui.rectMY[i - 10].veto++;
                    Gui.rectMY[i - 11].veto++;

                } else if (x == 9 && y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);

                    Gui.rectMY[i - 1].veto++;
                    Gui.rectMY[i + 10].veto++;
                    Gui.rectMY[i + 9].veto++;

                } else if (x == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i + 11);

                    Gui.rectMY[i + 1].veto++;
                    Gui.rectMY[i + 10].veto++;
                    Gui.rectMY[i - 10].veto++;
                    Gui.rectMY[i - 9].veto++;
                    Gui.rectMY[i + 11].veto++;

                } else if (x == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 11);

                    Gui.rectMY[i - 1].veto++;
                    Gui.rectMY[i + 10].veto++;
                    Gui.rectMY[i - 10].veto++;
                    Gui.rectMY[i + 9].veto++;
                    Gui.rectMY[i - 11].veto++;

                } else if (y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i + 11);

                    Gui.rectMY[i - 1].veto++;
                    Gui.rectMY[i + 1].veto++;
                    Gui.rectMY[i + 10].veto++;
                    Gui.rectMY[i + 9].veto++;
                    Gui.rectMY[i + 11].veto++;

                } else if (y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i - 11);

                    Gui.rectMY[i - 1].veto++;
                    Gui.rectMY[i + 1].veto++;
                    Gui.rectMY[i - 10].veto++;
                    Gui.rectMY[i - 9].veto++;
                    Gui.rectMY[i - 11].veto++;
                } else {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);

                    setColorYellowRect(i + 11);
                    setColorYellowRect(i - 11);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 9);

                    Gui.rectMY[i + 1].veto++;
                    Gui.rectMY[i - 1].veto++;
                    Gui.rectMY[i + 10].veto++;
                    Gui.rectMY[i - 10].veto++;

                    Gui.rectMY[i + 11].veto++;
                    Gui.rectMY[i - 11].veto++;
                    Gui.rectMY[i + 9].veto++;
                    Gui.rectMY[i - 9].veto++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }


    public static void marketGreen(int x, int y) {

        int i = y * 10 + x;

        try {

            if (x == 0 && y == 0) {

                setVeto(i + 1);
                setVeto(i + 10);
                setVeto(i + 11);
            } else if (x == 0 && y == 9) {
                setVeto(i + 1);
                setVeto(i - 10);
                setVeto(i - 9);

            } else if (x == 9 && y == 9) {
                setVeto(i - 1);
                setVeto(i - 10);
                setVeto(i - 11);

            } else if (x == 9 && y == 0) {
                setVeto(i - 1);
                setVeto(i + 10);
                setVeto(i + 9);

            } else if (x == 0) {
                setVeto(i + 1);
                setVeto(i + 10);
                setVeto(i - 10);
                setVeto(i - 9);
                setVeto(i + 11);

            } else if (x == 9) {
                setVeto(i - 1);
                setVeto(i + 10);
                setVeto(i - 10);
                setVeto(i + 9);
                setVeto(i - 11);

            } else if (y == 0) {
                setVeto(i - 1);
                setVeto(i + 1);
                setVeto(i + 10);
                setVeto(i + 9);
                setVeto(i + 11);

            } else if (y == 9) {
                setVeto(i - 1);
                setVeto(i + 1);
                setVeto(i - 10);
                setVeto(i - 9);
                setVeto(i - 11);

            } else {
                setVeto(i + 1);
                setVeto(i - 1);
                setVeto(i + 10);
                setVeto(i - 10);

                setVeto(i + 11);
                setVeto(i - 11);
                setVeto(i + 9);
                setVeto(i - 9);
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }

}
