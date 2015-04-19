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
        if (rects.getMyRect(x + (y * 10)).getFill() == Color.BLUE) {
            rects.getMyRect(x + (y * 10)).getPrivateShip().impairment();
            System.out.println("(rects.rectMY[x + (y * 10)].privateShip.isValidShip())" + (rects.getMyRect(x + (y * 10)).getPrivateShip().isValidShip()));
            if (rects.getMyRect(x + (y * 10)).getPrivateShip().isValidShip()) {
                System.out.println(x + (y * 10));
                rects.getMyRect(x + (y * 10)).setFill(Color.ORANGE);
                System.out.println("rects.rects.getMyRect(x + (y * 10))[x + (y * 10)].setFill(Color.ORANGE); " + (x + (y * 10)));
            } else if (!(rects.getMyRect(x + (y * 10)).getPrivateShip().isValidShip())) {
                rects.getMyRect(x + (y * 10)).setFill(Color.BLACK);
                int lX = rects.getMyRect(x + (y * 10)).getPrivateShip().getX1();
                int lY = rects.getMyRect(x + (y * 10)).getPrivateShip().getY1();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x + (y * 10)).getPrivateShip().getX2();
                lY = rects.getMyRect(x + (y * 10)).getPrivateShip().getY2();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x + (y * 10)).getPrivateShip().getX3();
                lY = rects.getMyRect(x + (y * 10)).getPrivateShip().getY3();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
                lX = rects.getMyRect(x + (y * 10)).getPrivateShip().getX4();
                lY = rects.getMyRect(x + (y * 10)).getPrivateShip().getY4();
                if (lX + lY <= 18) {
                    rects.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
            }
        }


    }


}
