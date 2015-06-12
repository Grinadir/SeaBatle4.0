package sample;

import javafx.scene.paint.Color;

public class Rects implements ObserverOfMap {

    private Engine engine;
    private MyRectangle[] rectMY = new MyRectangle[100];
    private EnemyRectangle[] rectENEMY = new EnemyRectangle[100];

    public Rects(Engine engine, ObservableMap map, ObservableMap logicMarked) {
        this.engine = engine;
        map.registerObserver(this);
        logicMarked.registerObserver(this);
    }

    private void makeOneIterationRectMY(int i) {
        rectMY[i] = new MyRectangle(engine.getGui().getSettings(),
                engine, 15, 15, i);
        rectMY[i].setFill(Color.GREEN);
        int numLine = (int) (10 - (10 - i * 0.1));
        rectMY[i].setXinMyRect(i - numLine * 10);
        rectMY[i].setYinMyRect(numLine);
        engine.getGui().addMySeaField(rectMY[i], (i - numLine * 10), numLine);
    }

    private void makeOneIterationRectENEMY(int i) {
        rectENEMY[i] = new EnemyRectangle(engine, 15, 15);
        rectENEMY[i].setFill(Color.GREEN);
        int numLine = (int) (10 - (10 - i * 0.1));
        rectENEMY[i].setXEnemyRect(i - numLine * 10);
        rectENEMY[i].setYEnemyRect(numLine);
        engine.getGui().addEnemySeaField(rectENEMY[i], (i - numLine * 10), numLine);
    }

    public void makeEnemyAndMyField() {
        for (int i = 0; i <= 99; ++i) {
            makeOneIterationRectMY(i);
            makeOneIterationRectENEMY(i);
        }
    }

    public MyRectangle getMyRect(int x, int y) {
        return rectMY[y * 10 + x];
    }

    public EnemyRectangle getRectENEMY(int i) {
        return rectENEMY[i];
    }

    @Override
    public void update(int x, int y, String fettle) {
        if (fettle.equals("non")) {
            rectMY[x + y * 10].setFill(Color.GREEN);
        }

        if (fettle.equals("nearship")) {
            rectMY[x + y * 10].setFill(Color.YELLOW);
        }
        if (fettle.equals("ship")) {
            rectMY[x + y * 10].setFill(Color.BLUE);
        }
    }
}