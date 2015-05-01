package sample;

public class ShipDouble implements InterfaceShip {

    private Engine engine;
    private int countOfAliveRect = 2;
    private final int NONEXISTENT_COORDINATE = 400;

    private int xNO1;
    private int yNO1;
    private int xNO2;
    private int yNO2;

    public ShipDouble(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean make(int x, int y) {
        if (engine.getCount2() == 0) {
            xNO1 = x;
            yNO1 = y;
            engine.setSaveX(x);
            engine.setSaveY(y);
            engine.setCount2(engine.getCount2() + 1);
            return true;
        } else if ((engine.getSaveX() == x || engine.getSaveY() == y)
                && engine.getCount2() != 0
                && (engine.getSaveX() == x + 1 || engine.getSaveY() == y + 1
                || engine.getSaveX() == x - 1 || engine.getSaveY() == y - 1)) {
            xNO2 = x;
            yNO2 = y;
            engine.setCount2(0);
            engine.decreaseAmountByOne("two");
            return true;
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
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getY3() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getX4() {
        return NONEXISTENT_COORDINATE;
    }

    @Override
    public int getY4() {
        return NONEXISTENT_COORDINATE;
    }
}
