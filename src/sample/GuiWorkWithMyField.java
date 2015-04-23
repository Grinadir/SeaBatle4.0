package sample;

import javafx.scene.paint.Color;

/**
 * Created by User on 10.04.2015.
 */
public class GuiWorkWithMyField {
    private Rects rects;

    public GuiWorkWithMyField(Rects rects) {
        this.rects = rects;
    }

    public void main(int x, int y) {
        if (rects.getMyRect(x, y).getFill() == Color.BLUE) {
            rects.getMyRect(x, y).getPrivateShip().impairment();
            if (rects.getMyRect(x, y).getPrivateShip().isValidShip()) {
                rects.getMyRect(x, y).setFill(Color.ORANGE);
            } else if (!(rects.getMyRect(x, y).getPrivateShip().isValidShip())) {
                rects.getMyRect(x, y).setFill(Color.BLACK);
                int lX = rects.getMyRect(x, y).getPrivateShip().getX1();
                int lY = rects.getMyRect(x, y).getPrivateShip().getY1();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX, lY ).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x, y).getPrivateShip().getX2();
                lY = rects.getMyRect(x, y).getPrivateShip().getY2();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX,  lY ).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x, y).getPrivateShip().getX3();
                lY = rects.getMyRect(x, y).getPrivateShip().getY3();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX,  lY ).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x, y).getPrivateShip().getX4();
                lY = rects.getMyRect(x, y).getPrivateShip().getY4();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX,  lY ).setFill(Color.BLACK);
                }
            }
        }


    }


}
