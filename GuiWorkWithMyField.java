package sample;

import javafx.scene.paint.Color;

/**
 * Created by User on 10.04.2015.
 */
public class GuiWorkWithMyField {
    private Gui gui;

    public GuiWorkWithMyField(Gui gui){
        this.gui=gui;
    }

    public void main(int x, int y) {
        if (gui.getMyRect(x + (y * 10)).getFill() == Color.BLUE) {
            gui.getMyRect(x + (y * 10)).getPrivateShip().impairment();
            System.out.println("(Gui.rectMY[x + (y * 10)].privateShip.isValidShip())" + (gui.getMyRect(x + (y * 10)).getPrivateShip().isValidShip()));
            if (gui.getMyRect(x + (y * 10)).getPrivateShip().isValidShip()) {
                System.out.println(x+(y*10));
                gui.getMyRect(x + (y * 10)).setFill(Color.ORANGE);
                System.out.println("Gui.gui.getMyRect(x + (y * 10))[x + (y * 10)].setFill(Color.ORANGE); " + (x + (y * 10)));
            } else if (!(gui.getMyRect(x + (y * 10)).getPrivateShip().isValidShip())) {
                gui.getMyRect(x + (y * 10)).setFill(Color.BLACK);
                int lX = gui.getMyRect(x + (y * 10)).getPrivateShip().getX1();
                int lY = gui.getMyRect(x + (y * 10)).getPrivateShip().getY1();
                if(lX+lY<=18) {
                    gui.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
                lX = gui.getMyRect(x + (y * 10)).getPrivateShip().getX2();
                lY = gui.getMyRect(x + (y * 10)).getPrivateShip().getY2();
                if(lX+lY<=18) {
                    gui.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
                lX = gui.getMyRect(x + (y * 10)).getPrivateShip().getX3();
                lY = gui.getMyRect(x + (y * 10)).getPrivateShip().getY3();
                if(lX+lY<=18) {
                    gui.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
                lX = gui.getMyRect(x + (y * 10)).getPrivateShip().getX4();
                lY = gui.getMyRect(x + (y * 10)).getPrivateShip().getY4();
                if(lX+lY<=18) {
                    gui.getMyRect(lX + (lY * 10)).setFill(Color.BLACK);
                }
            }
        }


    }



}
