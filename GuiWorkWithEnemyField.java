package sample;

import javafx.scene.paint.Color;

/**
 * Created by User on 11.04.2015.
 */
public class GuiWorkWithEnemyField {

    private Gui gui;

    public GuiWorkWithEnemyField(Gui gui) {
        this.gui = gui;
    }

    public void main(int x, int y, String str, int ind1, int ind2, int ind3, int ind4) {
        if (str.equals("DAM")) {
            gui.getRectENEMY(x + (y * 10)).setFill(Color.ORANGE);
        } else if (str.equals("DESTROY")) {
            gui.getRectENEMY(x + (y * 10)).setFill(Color.BLACK);
            if (ind1 != 4400) {
                gui.getRectENEMY(ind1).setFill(Color.BLACK);
            }
            if (ind2 != 4400) {
                gui.getRectENEMY(ind2).setFill(Color.BLACK);
            }
            if (ind3 != 4400) {
                gui.getRectENEMY(ind3).setFill(Color.BLACK);
            }
            if (ind4 != 4400) {
                gui.getRectENEMY(ind4).setFill(Color.BLACK);
            }
        } else if (str.equals("MISS")) {
            gui.getRectENEMY(x + (y * 10)).setFill(Color.YELLOW);
        }
    }
}