package sample;

import javafx.scene.paint.Color;

/**
 * Created by User on 19.04.2015.
 */
public class Rects {

    private Gui gui;
    private Counters counters;
    private String line = " ";
    private MyRectangle[] rectMY = new MyRectangle[100];
    private EnemyRectangle[] rectENEMY = new EnemyRectangle[100];

    public Rects(Gui gui, Counters counters) {
        this.gui = gui;
        this.counters = counters;
    }

    private void makeOneIterationRectMY(int i) {
        rectMY[i] = new MyRectangle(gui, counters, 10, 10, i);
        rectMY[i].setFill(Color.GREEN);
        int numLine = (int) (10 - (10 - i * 0.1));
        rectMY[i].setXinMyRect(i - numLine * 10);
        rectMY[i].setYinMyRect(numLine);
        gui.addMySeaField(rectMY[i], (i - numLine * 10), numLine);
        //mySeaField.add();
    }

    private void makeOneIterationRectENEMY(int i) {
        rectENEMY[i] = new EnemyRectangle(gui, 10, 10, counters);
        rectENEMY[i].setFill(Color.GREEN);
        int numLine = (int) (10 - (10 - i * 0.1));
        rectENEMY[i].setXenemyRect(i - numLine * 10);
        rectENEMY[i].setYenemyRect(numLine);
        gui.addEnemySeaField(rectENEMY[i], (i - numLine * 10), numLine);
        //enemySeaField.add();
    }

    public void makeEnemyAndMyField() {
        for (int i = 0; i <= 99; ++i) {
            makeOneIterationRectMY(i);
            makeOneIterationRectENEMY(i);
        }
    }

    public MyRectangle getMyRect(int x, int y) {
        return rectMY[x + (10 * y)];
    }

    public MyRectangle getMyRect(int i) {
        return rectMY[i];
    }

    public EnemyRectangle getRectENEMY(int i) {
        return rectENEMY[i];
    }

}
