package sample;

/**
 * Created by User on 22.02.2015.
 */


import javafx.scene.paint.Color;

public class FunctionsOfMarkedByDifferentColor {
         /*
    * Класс Function
    * нужен для того, чтобы хранить функции для раскрашивания в разные цвета
    * квадратов
    *
    */

    Gui gui;


    public FunctionsOfMarkedByDifferentColor(Gui gui){
        this.gui=gui;

    }

    public void setColorYellowRect(int i) {

        if (!(gui.getMyRect(i).getFill() == Color.BLUE)
                && !(gui.getMyRect(i).getFill() == Color.YELLOW)) {


            gui.getMyRect(i).setFill(Color.YELLOW);
        } else {
            ;
        }
    }

    public void setVeto(int s) {

        if (gui.getMyRect(s).getVeto() == 1) {

            gui.getMyRect(s).setFill(Color.GREEN);
            gui.getMyRect(s).setVeto(gui.getMyRect(s).getVeto()-1);


        } else {


            gui.getMyRect(s).setVeto(gui.getMyRect(s).getVeto()-1);
        }
    }

    public void marketYellow(int x, int y) {
        int i = y * 10 + x;
        if (!(gui.getMyRect(i).getFill() == Color.YELLOW)) {

            try {

                if (x == 0 && y == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 11);
                    gui.getMyRect(i + 1).setVeto(gui.getMyRect(i + 1).getVeto() + 1);
                    gui.getMyRect(i + 10).setVeto(gui.getMyRect(i + 10).getVeto() + 1);
                    gui.getMyRect(i + 11).setVeto(gui.getMyRect(i + 1).getVeto() + 11);


                } else if (x == 0 && y == 9) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    gui.getMyRect(i + 1).setVeto(gui.getMyRect(i + 1).getVeto() + 1);
                    gui.getMyRect(i - 10).setVeto(gui.getMyRect(i-10).getVeto()+1);
                    gui.getMyRect(i - 9).setVeto(gui.getMyRect(i-9).getVeto()+1);

                } else if (x == 9 && y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 11);

                    gui.getMyRect(i - 1).setVeto(gui.getMyRect(i - 1).getVeto() + 1);
                    gui.getMyRect(i - 10).setVeto(gui.getMyRect(i - 10).getVeto()+1);
                    gui.getMyRect(i - 11).setVeto(gui.getMyRect(i -11).getVeto()+1);

                } else if (x == 9 && y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);

                    gui.getMyRect(i - 1).setVeto(gui.getMyRect(i - 1).getVeto() + 1);
                    gui.getMyRect(i + 10).setVeto(gui.getMyRect(i + 10).getVeto() + 1);
                    gui.getMyRect(i + 9).setVeto(gui.getMyRect(i+9).getVeto()+1);

                } else if (x == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i + 11);

                    gui.getMyRect(i + 1).setVeto(gui.getMyRect(i + 1).getVeto() + 1);
                    gui.getMyRect(i + 10).setVeto(gui.getMyRect(i + 10).getVeto()+1);
                    gui.getMyRect(i - 10).setVeto(gui.getMyRect(i - 10).getVeto()+1);
                    gui.getMyRect(i - 9).setVeto(gui.getMyRect(i-9).getVeto()+1);
                    gui.getMyRect(i + 11).setVeto(gui.getMyRect(i+11).getVeto()+1);

                } else if (x == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 11);

                    gui.getMyRect(i - 1).setVeto(gui.getMyRect(i - 1).getVeto()+1);
                    gui.getMyRect(i + 10).setVeto(gui.getMyRect(i + 10).getVeto()+1);
                    gui.getMyRect(i - 10).setVeto(gui.getMyRect(i-10).getVeto()+1);
                    gui.getMyRect(i + 9).setVeto(gui.getMyRect(i+9).getVeto()+1);
                    gui.getMyRect(i - 11).setVeto(gui.getMyRect(i-11).getVeto()+1);

                } else if (y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i + 11);

                    gui.getMyRect(i - 1).setVeto(gui.getMyRect(i - 1).getVeto()+1);
                    gui.getMyRect(i + 1).setVeto(gui.getMyRect(i + 1).getVeto()+1);
                    gui.getMyRect(i + 10).setVeto(gui.getMyRect(i + 10).getVeto()+1);
                    gui.getMyRect(i + 9).setVeto(gui.getMyRect(i+9).getVeto()+1);
                    gui.getMyRect(i + 11).setVeto(gui.getMyRect(i+11).getVeto()+1);

                } else if (y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i - 11);

                    gui.getMyRect(i - 1).setVeto(gui.getMyRect(i - 1).getVeto()+1);
                    gui.getMyRect(i + 1).setVeto(gui.getMyRect(i + 1).getVeto()+1);
                    gui.getMyRect(i - 10).setVeto(gui.getMyRect(i-10).getVeto()+1);
                    gui.getMyRect(i - 9).setVeto(gui.getMyRect(i-9).getVeto()+1);
                    gui.getMyRect(i - 11).setVeto(gui.getMyRect(i-11).getVeto()+1);
                } else {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);

                    setColorYellowRect(i + 11);
                    setColorYellowRect(i - 11);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 9);

                    gui.getMyRect(i + 1).setVeto(gui.getMyRect(i + 1).getVeto()+1);
                    gui.getMyRect(i - 1).setVeto(gui.getMyRect(i - 1).getVeto()+1);
                    gui.getMyRect(i + 10).setVeto(gui.getMyRect(i + 10).getVeto()+1);
                    gui.getMyRect(i - 10).setVeto(gui.getMyRect(i-10).getVeto()+1);

                    gui.getMyRect(i + 11).setVeto(gui.getMyRect(i+11).getVeto()+1);
                    gui.getMyRect(i - 11).setVeto(gui.getMyRect(i-11).getVeto()+1);
                    gui.getMyRect(i + 9).setVeto(gui.getMyRect(i+9).getVeto()+1);
                    gui.getMyRect(i - 9).setVeto(gui.getMyRect(i - 9).getVeto()+1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }


    public void marketGreen(int x, int y) {

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
