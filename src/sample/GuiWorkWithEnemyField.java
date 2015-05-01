package sample;

import javafx.scene.paint.Color;

public class GuiWorkWithEnemyField {

    private Rects rects;
    private final int INDEX_END_SHIP = 4400;

    public GuiWorkWithEnemyField(Rects rects) {
        this.rects = rects;
    }

    public void main(int x, int y, String str, int ind1, int ind2, int ind3, int ind4) {
        if (str.equals("DAM")) {
            rects.getRectENEMY(x + (y * 10)).setFill(Color.ORANGE);
        } else if (str.equals("DESTROY")) {
            rects.getRectENEMY(x + (y * 10)).setFill(Color.BLACK);
            if (ind1 != INDEX_END_SHIP) {
                rects.getRectENEMY(ind1).setFill(Color.BLACK);
            }
            if (ind2 != INDEX_END_SHIP) {
                rects.getRectENEMY(ind2).setFill(Color.BLACK);
            }
            if (ind3 != INDEX_END_SHIP) {
                rects.getRectENEMY(ind3).setFill(Color.BLACK);
            }
            if (ind4 != INDEX_END_SHIP) {
                rects.getRectENEMY(ind4).setFill(Color.BLACK);
            }
        } else if (str.equals("MISS")) {
            rects.getRectENEMY(x + (y * 10)).setFill(Color.YELLOW);
        }
    }
}