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

    public void setColorYellowRect(int x, int y) {
        if (!(rects.getMyRect(x, y).getFill() == Color.BLUE) && !(rects.getMyRect(x, y).getFill() == Color.YELLOW)) {
            rects.getMyRect(x, y).setFill(Color.YELLOW);
        }
    }

    public void setVeto(int x, int y) {

        if (rects.getMyRect(x, y).getVeto() == 1) {
            rects.getMyRect(x, y).setFill(Color.GREEN);
            rects.getMyRect(x, y).setVeto(rects.getMyRect(x, y).getVeto() - 1);
        } else {
            rects.getMyRect(x, y).setVeto(rects.getMyRect(x, y).getVeto() - 1);
        }
    }

    public void marketYellow(int x, int y) {
        int i = y * 10 + x;
        if (!(rects.getMyRect(x, y).getFill() == Color.YELLOW)) {

            try {
                if (x == 0 && y == 0) {
                    setColorYellowRect(x + 1, y);
                    setColorYellowRect(x, y+1);
                    setColorYellowRect(x+1, y+1);
                    rects.getMyRect(x+1, y).setVeto(rects.getMyRect(x+1, y).getVeto() + 1);
                    rects.getMyRect(x, y+1).setVeto(rects.getMyRect(x, y+1).getVeto() + 1);
                    rects.getMyRect(x+1, y+1).setVeto(rects.getMyRect(x+1, y+1).getVeto() + 11);
                } else if (x == 0 && y == 9) {
                    setColorYellowRect(x+1, y);
                    setColorYellowRect(x, y-1);
                    setColorYellowRect(x+1, y-1);
                    rects.getMyRect(x+1, y).setVeto(rects.getMyRect(x+1, y).getVeto() + 1);
                    rects.getMyRect(x, y-1).setVeto(rects.getMyRect(x, y-1).getVeto() + 1);
                    rects.getMyRect(x+1, y-1).setVeto(rects.getMyRect(x+1, y-1).getVeto() + 1);
                } else if (x == 9 && y == 9) {
                    setColorYellowRect(x-1, y);
                    setColorYellowRect(x, y-1);
                    setColorYellowRect(x-1, y-1);
                    rects.getMyRect(x-1, y).setVeto(rects.getMyRect(x-1, y).getVeto() + 1);
                    rects.getMyRect(x, y-1).setVeto(rects.getMyRect(x, y-1).getVeto() + 1);
                    rects.getMyRect(x-1, y-1).setVeto(rects.getMyRect(x-1, y-1).getVeto() + 1);
                } else if (x == 9 && y == 0) {
                    setColorYellowRect(x-1, y);
                    setColorYellowRect(x, y+1);
                    setColorYellowRect(x-1, y+1);
                    rects.getMyRect(x-1, y).setVeto(rects.getMyRect(x-1, y).getVeto() + 1);
                    rects.getMyRect(x, y+1).setVeto(rects.getMyRect(x, y+1).getVeto() + 1);
                    rects.getMyRect(x-1, y+1).setVeto(rects.getMyRect(x-1, y+1).getVeto() + 1);
                } else if (x == 0) {
                    setColorYellowRect(x+1, y);
                    setColorYellowRect(x, y+1);
                    setColorYellowRect(x, y-1);
                    setColorYellowRect(x+1, y-1);
                    setColorYellowRect(x+1, y+1);
                    rects.getMyRect(x+1, y).setVeto(rects.getMyRect(x+1, y).getVeto() + 1);
                    rects.getMyRect(x, y+1).setVeto(rects.getMyRect(x, y+1).getVeto() + 1);
                    rects.getMyRect(x, y-1).setVeto(rects.getMyRect(x, y-1).getVeto() + 1);
                    rects.getMyRect(x+1, y-1).setVeto(rects.getMyRect(x+1, y-1).getVeto() + 1);
                    rects.getMyRect(x+1, y+1).setVeto(rects.getMyRect(x+1, y+1).getVeto() + 1);
                } else if (x == 9) {
                    setColorYellowRect(x-1, y);
                    setColorYellowRect(x, y+1);
                    setColorYellowRect(x, y-1);
                    setColorYellowRect(x-1, y+1);
                    setColorYellowRect(x-1, y-1);
                    rects.getMyRect(x-1, y).setVeto(rects.getMyRect(x-1, y).getVeto() + 1);
                    rects.getMyRect(x, y+1).setVeto(rects.getMyRect(x, y+1).getVeto() + 1);
                    rects.getMyRect(x, y-1).setVeto(rects.getMyRect(x, y-1).getVeto() + 1);
                    rects.getMyRect(x-1, y+1).setVeto(rects.getMyRect(x-1, y+1).getVeto() + 1);
                    rects.getMyRect(x-1, y-1).setVeto(rects.getMyRect(x-1, y-1).getVeto() + 1);
                } else if (y == 0) {
                    setColorYellowRect(x-1, y);
                    setColorYellowRect(x+1, y);
                    setColorYellowRect(x, y+1);
                    setColorYellowRect(x-1, y+1);
                    setColorYellowRect(x+1, y+1);
                    rects.getMyRect(x-1, y).setVeto(rects.getMyRect(x-1, y).getVeto() + 1);
                    rects.getMyRect(x+1, y).setVeto(rects.getMyRect(x+1, y).getVeto() + 1);
                    rects.getMyRect(x, y+1).setVeto(rects.getMyRect(x, y+1).getVeto() + 1);
                    rects.getMyRect(x-1, y+1).setVeto(rects.getMyRect(x-1, y+1).getVeto() + 1);
                    rects.getMyRect(x+1, y+1).setVeto(rects.getMyRect(x+1, y+1).getVeto() + 1);
                } else if (y == 9) {
                    setColorYellowRect(x-1, y);
                    setColorYellowRect(x+1, y);
                    setColorYellowRect(x, y-1);
                    setColorYellowRect(x+1, y-1);
                    setColorYellowRect(x-1, y-1);
                    rects.getMyRect(x-1, y).setVeto(rects.getMyRect(x-1, y).getVeto() + 1);
                    rects.getMyRect(x+1, y).setVeto(rects.getMyRect(x+1, y).getVeto() + 1);
                    rects.getMyRect(x, y-1).setVeto(rects.getMyRect(x, y-1).getVeto() + 1);
                    rects.getMyRect(x+1, y-1).setVeto(rects.getMyRect(x+1, y-1).getVeto() + 1);
                    rects.getMyRect(x-1, y-1).setVeto(rects.getMyRect(x-1, y-1).getVeto() + 1);
                } else {
                    setColorYellowRect(x+1, y);
                    setColorYellowRect(x-1, y);
                    setColorYellowRect(x, y+1);
                    setColorYellowRect(x, y-1);
                    setColorYellowRect(x+1, y+1);
                    setColorYellowRect(x-1, y-1);
                    setColorYellowRect(x-1, y+1);
                    setColorYellowRect(x+1, y-1);
                    rects.getMyRect(x+1, y).setVeto(rects.getMyRect(x+1, y).getVeto() + 1);
                    rects.getMyRect(x-1, y).setVeto(rects.getMyRect(x-1, y).getVeto() + 1);
                    rects.getMyRect(x, y+1).setVeto(rects.getMyRect(x, y+1).getVeto() + 1);
                    rects.getMyRect(x, y-1).setVeto(rects.getMyRect(x, y-1).getVeto() + 1);
                    rects.getMyRect(x+1, y+1).setVeto(rects.getMyRect(x+1, y+1).getVeto() + 1);
                    rects.getMyRect(x-1, y-1).setVeto(rects.getMyRect(x-1, y-1).getVeto() + 1);
                    rects.getMyRect(x-1, y+1).setVeto(rects.getMyRect(x-1, y+1).getVeto() + 1);
                    rects.getMyRect(x+1, y-1).setVeto(rects.getMyRect(x+1, y-1).getVeto() + 1);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();

            }
        }
    }


    public void marketGreen(int x, int y) {
        int i = y * 10 + x;
        try {
            if (x == 0 && y == 0) {
                setVeto(x+1, y);
                setVeto(x, y+1);
                setVeto(x+1, y+1);
            } else if (x == 0 && y == 9) {
                setVeto(x+1, y);
                setVeto(x, y-1);
                setVeto(x-1, y+1);
            } else if (x == 9 && y == 9) {
                setVeto(x-1, y);
                setVeto(x, y-1);
                setVeto(x-1, y-1);
            } else if (x == 9 && y == 0) {
                setVeto(x-1, y);
                setVeto(x, y+1);
                setVeto(x-1, y+1);
            } else if (x == 0) {
                setVeto(x+1, y);
                setVeto(x, y+1);
                setVeto(x, y-1);
                setVeto(x+1, y-1);
                setVeto(x+1, y+1);
            } else if (x == 9) {
                setVeto(x-1, y);
                setVeto(x, y+1);
                setVeto(x, y-1);
                setVeto(x-1, y+1);
                setVeto(x-1, y-1);
            } else if (y == 0) {
                setVeto(x-1, y);
                setVeto(x+1, y);
                setVeto(x, y+1);
                setVeto(x-1, y+1);
                setVeto(x+1, y+1);
            } else if (y == 9) {
                setVeto(x-1, y);
                setVeto(x+1, y);
                setVeto(x, y-1);
                setVeto(x+1, y-1);
                setVeto(x-1, y-1);
            } else {
                setVeto(x+1, y);
                setVeto(x-1, y);
                setVeto(x, y+1);
                setVeto(x, y-1);
                setVeto(x+1, y+1);
                setVeto(x-1, y-1);
                setVeto(x-1, y+1);
                setVeto(x+1, y-1);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();

        }
    }

    public void undoTarget(int x, int y) {
        if (rects.getRectENEMY(x + (10 * y)).getFill() == Color.RED) {
            rects.getRectENEMY(x + (10 * y)).setFill(Color.GREEN);
        }
    }
}
