package sample;

import javafx.scene.paint.Color;

/**
 * Created by User on 10.04.2015.
 */
public class GuiWorkWithMyField {
    private Rects rects;
    private final int OUT_OF_FIELD=18;

    public GuiWorkWithMyField(Rects rects) {
        this.rects = rects;
    }

    public void main(int x, int y) {
        if (rects.getMyRect(x, y).getFill() == Color.BLUE) {
            rects.getMyRect(x, y).getShip().impairment();
            if (rects.getMyRect(x, y).getShip().isValidShip()) {
                rects.getMyRect(x, y).setFill(Color.ORANGE);
            } else if (!(rects.getMyRect(x, y).getShip().isValidShip())) {
                rects.getMyRect(x, y).setFill(Color.BLACK);
                int lX = rects.getMyRect(x, y).getShip().getX1();
                int lY = rects.getMyRect(x, y).getShip().getY1();
                if (lX + lY <= OUT_OF_FIELD) {
                    rects.getMyRect(lX, lY).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x, y).getShip().getX2();
                lY = rects.getMyRect(x, y).getShip().getY2();
                if (lX + lY <= OUT_OF_FIELD) {
                    rects.getMyRect(lX, lY).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x, y).getShip().getX3();
                lY = rects.getMyRect(x, y).getShip().getY3();
                if (lX + lY <= OUT_OF_FIELD) {
                    rects.getMyRect(lX, lY).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x, y).getShip().getX4();
                lY = rects.getMyRect(x, y).getShip().getY4();
                if (lX + lY <= OUT_OF_FIELD) {
                    rects.getMyRect(lX, lY).setFill(Color.BLACK);
                }
            }
        }
    }
}
