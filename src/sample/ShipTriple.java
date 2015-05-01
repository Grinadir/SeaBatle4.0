package sample;

/**
 * Created by User on 29.04.2015.
 */
public class ShipTriple implements InterfaceShip {
    private Engine engine;
    private int countOfAliveRect = 3;
    private final int NONEXISTENT_COORDINATE = 400;

    private int xNO1;
    private int yNO1;
    private int xNO2;
    private int yNO2;
    private int xNO3;
    private int yNO3;

    public ShipTriple(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean make(int x, int y) {
        if (isFirstRectangle()) {
            xNO1 = x;
            yNO1 = y;
            engine.setSaveX(x);
            engine.setSaveY(y);
            engine.setCount3(engine.getCount3() + 1);
            return true;
        } else if (isSecondRectangle(x, y)) {
            xNO2 = x;
            yNO2 = y;
            engine.setSaveX1(x);
            engine.setSaveY1(y);
            engine.setCount3(engine.getCount3() + 1);
            return true;
        } else if (isThirtyRectangle(x, y)) {
            if (engine.getSaveX1() == x
                    && (engine.getSaveY1() == y + 1 || engine.getSaveY1() == y - 1)) {
                xNO3 = x;
                yNO3 = y;
                engine.setCount3(0);
                engine.decreaseAmountByOne("three");
                return true;
            } else if (engine.getSaveY() == y
                    && (engine.getSaveX1() == x + 1 || engine.getSaveX1() == x - 1)
                    && engine.getThreeAmount() != -1) {
                xNO3 = x;
                yNO3 = y;
                engine.setCount3(0);
                engine.decreaseAmountByOne("three");
                return true;
            }
        }
        return false;
    }

    @Override
    public void clean() {

    }

    @Override
    public boolean isValidShip() {
        if (countOfAliveRect != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void impairment() {
        countOfAliveRect--;
    }

    @Override
    public int getX1() {
        return xNO1;
    }

    @Override
    public int getY1() {
        return yNO1;
    }

    @Override
    public int getX2() {
        return xNO2;
    }

    @Override
    public int getY2() {
        return yNO2;
    }

    @Override
    public int getX3() {
        return xNO3;
    }

    @Override
    public int getY3() {
        return yNO3;
    }

    @Override
    public int getX4() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getY4() {
        return NONEXISTENT_COORDINATE;
    }

    private boolean isThirtyRectangle(int x, int y) {
        return (engine.getSaveX() == x || engine.getSaveY() == y) && engine.getCount3() == 2
                && engine.getThreeAmount() != -1;
    }

    private boolean isSecondRectangle(int x, int y) {
        return (xNO1 == x || yNO1 == y) && engine.getCount3() == 1 && (xNO1 == x + 1 || yNO1 == y + 1 || xNO1 == x - 1 || yNO1 == y - 1)
                && engine.getThreeAmount() != -1;
    }

    private boolean isFirstRectangle() {
        return engine.getCount3() == 0;
    }
}
