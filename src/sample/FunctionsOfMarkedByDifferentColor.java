package sample;

import javafx.scene.paint.Color;

/*
 * Contains functions painting over squares by different colors
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
        if (!(rects.getMyRect(x, y).getFill() == Color.YELLOW)) {
            for (int xCircle = x - 1; xCircle <= x + 1; xCircle++) {
                innerCircleForMarkYellow(y, xCircle);
            }
        }
    }

    private void innerCircleForMarkYellow(int y, int xCircle) {
        if (0 <= xCircle && xCircle <= 9) {
            for (int yC = y - 1; yC <= y + 1; yC++) {
                if (0 <= yC && yC <= 9) {
                    setColorYellowRect(xCircle, yC);
                    rects.getMyRect(xCircle, yC).setVeto(rects.getMyRect(xCircle, yC).getVeto() + 1);
                }
            }
        }
    }

    public void marketGreen(int x, int y) {
        for (int xCircle = x - 1; xCircle <= x + 1; xCircle++) {
            innerCircleForVeto(y, xCircle);
        }
    }

    private void innerCircleForVeto(int y, int xCircle) {
        if (0 <= xCircle && xCircle <= 9) {
            for (int yCircle = y - 1; yCircle <= y + 1; yCircle++) {
                if (0 <= yCircle && yCircle <= 9) {
                    setVeto(xCircle, yCircle);
                }
            }
        }
    }

    public void undoTarget(int x, int y) {
        if (rects.getRectENEMY(x + (10 * y)).getFill() == Color.RED) {
            rects.getRectENEMY(x + (10 * y)).setFill(Color.GREEN);
        }
    }
}
