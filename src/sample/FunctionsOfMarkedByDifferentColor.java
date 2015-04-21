package sample;

/**
 * Created by User on 22.02.2015.
 */


import javafx.scene.paint.Color;
/*
 * Xранить функции для раскрашивания в разные цвета
 * квадратов
 */
public class FunctionsOfMarkedByDifferentColor {

    Rects rects;

    public FunctionsOfMarkedByDifferentColor(Rects rects) {
        this.rects = rects;
    }

    public void setColorYellowRect(int i) {
        if (!(rects.getMyRect(i).getFill() == Color.BLUE) && !(rects.getMyRect(i).getFill() == Color.YELLOW)) {
            rects.getMyRect(i).setFill(Color.YELLOW);
        }
    }

    public void setVeto(int s) {

        if (rects.getMyRect(s).getVeto() == 1) {
            rects.getMyRect(s).setFill(Color.GREEN);
            rects.getMyRect(s).setVeto(rects.getMyRect(s).getVeto() - 1);
        } else {
            rects.getMyRect(s).setVeto(rects.getMyRect(s).getVeto() - 1);
        }
    }

    public void marketYellow(int x, int y) {
        int i = y * 10 + x;
        if (!(rects.getMyRect(i).getFill() == Color.YELLOW)) {

            try {
                if (x == 0 && y == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 11);
                    rects.getMyRect(i + 1).setVeto(rects.getMyRect(i + 1).getVeto() + 1);
                    rects.getMyRect(i + 10).setVeto(rects.getMyRect(i + 10).getVeto() + 1);
                    rects.getMyRect(i + 11).setVeto(rects.getMyRect(i + 1).getVeto() + 11);
                } else if (x == 0 && y == 9) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    rects.getMyRect(i + 1).setVeto(rects.getMyRect(i + 1).getVeto() + 1);
                    rects.getMyRect(i - 10).setVeto(rects.getMyRect(i - 10).getVeto() + 1);
                    rects.getMyRect(i - 9).setVeto(rects.getMyRect(i - 9).getVeto() + 1);
                } else if (x == 9 && y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 11);
                    rects.getMyRect(i - 1).setVeto(rects.getMyRect(i - 1).getVeto() + 1);
                    rects.getMyRect(i - 10).setVeto(rects.getMyRect(i - 10).getVeto() + 1);
                    rects.getMyRect(i - 11).setVeto(rects.getMyRect(i - 11).getVeto() + 1);
                } else if (x == 9 && y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);
                    rects.getMyRect(i - 1).setVeto(rects.getMyRect(i - 1).getVeto() + 1);
                    rects.getMyRect(i + 10).setVeto(rects.getMyRect(i + 10).getVeto() + 1);
                    rects.getMyRect(i + 9).setVeto(rects.getMyRect(i + 9).getVeto() + 1);
                } else if (x == 0) {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i + 11);
                    rects.getMyRect(i + 1).setVeto(rects.getMyRect(i + 1).getVeto() + 1);
                    rects.getMyRect(i + 10).setVeto(rects.getMyRect(i + 10).getVeto() + 1);
                    rects.getMyRect(i - 10).setVeto(rects.getMyRect(i - 10).getVeto() + 1);
                    rects.getMyRect(i - 9).setVeto(rects.getMyRect(i - 9).getVeto() + 1);
                    rects.getMyRect(i + 11).setVeto(rects.getMyRect(i + 11).getVeto() + 1);
                } else if (x == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 11);
                    rects.getMyRect(i - 1).setVeto(rects.getMyRect(i - 1).getVeto() + 1);
                    rects.getMyRect(i + 10).setVeto(rects.getMyRect(i + 10).getVeto() + 1);
                    rects.getMyRect(i - 10).setVeto(rects.getMyRect(i - 10).getVeto() + 1);
                    rects.getMyRect(i + 9).setVeto(rects.getMyRect(i + 9).getVeto() + 1);
                    rects.getMyRect(i - 11).setVeto(rects.getMyRect(i - 11).getVeto() + 1);
                } else if (y == 0) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i + 11);
                    rects.getMyRect(i - 1).setVeto(rects.getMyRect(i - 1).getVeto() + 1);
                    rects.getMyRect(i + 1).setVeto(rects.getMyRect(i + 1).getVeto() + 1);
                    rects.getMyRect(i + 10).setVeto(rects.getMyRect(i + 10).getVeto() + 1);
                    rects.getMyRect(i + 9).setVeto(rects.getMyRect(i + 9).getVeto() + 1);
                    rects.getMyRect(i + 11).setVeto(rects.getMyRect(i + 11).getVeto() + 1);
                } else if (y == 9) {
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i - 9);
                    setColorYellowRect(i - 11);
                    rects.getMyRect(i - 1).setVeto(rects.getMyRect(i - 1).getVeto() + 1);
                    rects.getMyRect(i + 1).setVeto(rects.getMyRect(i + 1).getVeto() + 1);
                    rects.getMyRect(i - 10).setVeto(rects.getMyRect(i - 10).getVeto() + 1);
                    rects.getMyRect(i - 9).setVeto(rects.getMyRect(i - 9).getVeto() + 1);
                    rects.getMyRect(i - 11).setVeto(rects.getMyRect(i - 11).getVeto() + 1);
                } else {
                    setColorYellowRect(i + 1);
                    setColorYellowRect(i - 1);
                    setColorYellowRect(i + 10);
                    setColorYellowRect(i - 10);
                    setColorYellowRect(i + 11);
                    setColorYellowRect(i - 11);
                    setColorYellowRect(i + 9);
                    setColorYellowRect(i - 9);
                    rects.getMyRect(i + 1).setVeto(rects.getMyRect(i + 1).getVeto() + 1);
                    rects.getMyRect(i - 1).setVeto(rects.getMyRect(i - 1).getVeto() + 1);
                    rects.getMyRect(i + 10).setVeto(rects.getMyRect(i + 10).getVeto() + 1);
                    rects.getMyRect(i - 10).setVeto(rects.getMyRect(i - 10).getVeto() + 1);
                    rects.getMyRect(i + 11).setVeto(rects.getMyRect(i + 11).getVeto() + 1);
                    rects.getMyRect(i - 11).setVeto(rects.getMyRect(i - 11).getVeto() + 1);
                    rects.getMyRect(i + 9).setVeto(rects.getMyRect(i + 9).getVeto() + 1);
                    rects.getMyRect(i - 9).setVeto(rects.getMyRect(i - 9).getVeto() + 1);
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

    public void undoTarget(int x, int y) {
        if (rects.getRectENEMY(x + (10 * y)).getFill() == Color.RED) {
            rects.getRectENEMY(x + (10 * y)).setFill(Color.GREEN);
        }
    }
}
