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

        if (Gui.rectMY[s].getVeto() == 1) {

            Gui.rectMY[s].setFill(Color.GREEN);
            Gui.rectMY[s].setVeto(Gui.rectMY[s].getVeto()-1);


        } else {


            Gui.rectMY[s].setVeto(Gui.rectMY[s].getVeto()-1);
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
                    Gui.rectMY[i + 1].setVeto(Gui.rectMY[i+1].getVeto()+1);
                    Gui.rectMY[i + 10].setVeto(Gui.rectMY[i+10].getVeto()+1);
                    Gui.rectMY[i + 11].setVeto(Gui.rectMY[i+1].getVeto()+11);


                } else if (x == 0 && y == 9) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    Gui.rectMY[i + 1].setVeto(Gui.rectMY[i+1].getVeto()+1);
                    Gui.rectMY[i - 10].setVeto(Gui.rectMY[i-10].getVeto()+1);
                    Gui.rectMY[i - 9].setVeto(Gui.rectMY[i-9].getVeto()+1);

                } else if (x == 9 && y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 11);

                    Gui.rectMY[i - 1].setVeto(Gui.rectMY[i-1].getVeto()+1);
                    Gui.rectMY[i - 10].setVeto(Gui.rectMY[i-10].getVeto()+1);
                    Gui.rectMY[i - 11].setVeto(Gui.rectMY[i-11].getVeto()+1);

                } else if (x == 9 && y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);

                    Gui.rectMY[i - 1].setVeto(Gui.rectMY[i-1].getVeto()+1);
                    Gui.rectMY[i + 10].setVeto(Gui.rectMY[i+10].getVeto()+1);
                    Gui.rectMY[i + 9].setVeto(Gui.rectMY[i+9].getVeto()+1);

                } else if (x == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i + 11);

                    Gui.rectMY[i + 1].setVeto(Gui.rectMY[i+1].getVeto()+1);
                    Gui.rectMY[i + 10].setVeto(Gui.rectMY[i+10].getVeto()+1);
                    Gui.rectMY[i - 10].setVeto(Gui.rectMY[i-10].getVeto()+1);
                    Gui.rectMY[i - 9].setVeto(Gui.rectMY[i-9].getVeto()+1);
                    Gui.rectMY[i + 11].setVeto(Gui.rectMY[i+11].getVeto()+1);

                } else if (x == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 11);

                    Gui.rectMY[i - 1].setVeto(Gui.rectMY[i-1].getVeto()+1);
                    Gui.rectMY[i + 10].setVeto(Gui.rectMY[i+10].getVeto()+1);
                    Gui.rectMY[i - 10].setVeto(Gui.rectMY[i-10].getVeto()+1);
                    Gui.rectMY[i + 9].setVeto(Gui.rectMY[i+9].getVeto()+1);
                    Gui.rectMY[i - 11].setVeto(Gui.rectMY[i-11].getVeto()+1);

                } else if (y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i + 11);

                    Gui.rectMY[i - 1].setVeto(Gui.rectMY[i-1].getVeto()+1);
                    Gui.rectMY[i + 1].setVeto(Gui.rectMY[i+1].getVeto()+1);
                    Gui.rectMY[i + 10].setVeto(Gui.rectMY[i+10].getVeto()+1);
                    Gui.rectMY[i + 9].setVeto(Gui.rectMY[i+9].getVeto()+1);
                    Gui.rectMY[i + 11].setVeto(Gui.rectMY[i+11].getVeto()+1);

                } else if (y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i - 11);

                    Gui.rectMY[i - 1].setVeto(Gui.rectMY[i-1].getVeto()+1);
                    Gui.rectMY[i + 1].setVeto(Gui.rectMY[i+1].getVeto()+1);
                    Gui.rectMY[i - 10].setVeto(Gui.rectMY[i-10].getVeto()+1);
                    Gui.rectMY[i - 9].setVeto(Gui.rectMY[i-9].getVeto()+1);
                    Gui.rectMY[i - 11].setVeto(Gui.rectMY[i-11].getVeto()+1);
                } else {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);

                    setColorYellowRect(i + 11);
                    setColorYellowRect(i - 11);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 9);

                    Gui.rectMY[i + 1].setVeto(Gui.rectMY[i+1].getVeto()+1);
                    Gui.rectMY[i - 1].setVeto(Gui.rectMY[i-1].getVeto()+1);
                    Gui.rectMY[i + 10].setVeto(Gui.rectMY[i+10].getVeto()+1);
                    Gui.rectMY[i - 10].setVeto(Gui.rectMY[i-10].getVeto()+1);

                    Gui.rectMY[i + 11].setVeto(Gui.rectMY[i+11].getVeto()+1);
                    Gui.rectMY[i - 11].setVeto(Gui.rectMY[i-11].getVeto()+1);
                    Gui.rectMY[i + 9].setVeto(Gui.rectMY[i+9].getVeto()+1);
                    Gui.rectMY[i - 9].setVeto(Gui.rectMY[i-9].getVeto()+1);
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
