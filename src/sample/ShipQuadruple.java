package sample;

/**
 * Created by User on 29.04.2015.
 */
public class ShipQuadruple implements InterfaceShip {

    private Engine engine;
    private int countOfAliveRect = 4;

    private int xNO1;
    private int yNO1;
    private int xNO2;
    private int yNO2;
    private int xNO3;
    private int yNO3;
    private int xNO4;
    private int yNO4;

    public ShipQuadruple(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean make(int x, int y) {
        if (engine.getCount4() == 0) {
            xNO1 = x;
            yNO1 = y;
            engine.setSaveX(x);
            engine.setSaveY(y);
            engine.setCount4(engine.getCount4() + 1);
            return true;
        } else if ((xNO1 == x || yNO1 == y) && engine.getCount4() == 1
                && (xNO1 == x + 1 || yNO1 == y + 1 || xNO1 == x - 1 || yNO1 == y - 1)
                && engine.getFourAmount() != 0) {
            xNO2 = x;
            yNO2 = y;
            engine.setSaveX1(x);
            engine.setSaveY1(y);
            engine.setCount4(engine.getCount4() + 1);
            return true;
        } else if ((xNO1 == x || yNO1 == y) && engine.getFourAmount() != 0
                && engine.getCount4() == 2) {
            if (xNO2 == x
                    && (yNO2 == y + 1 || yNO2 == y - 1)) {
                xNO3 = x;
                yNO3 = y;
                engine.setSaveX2(x);
                engine.setSaveY2(y);
                engine.setCount4(engine.getCount4() + 1);
                return true;
            } else if (yNO1 == y
                    && (xNO2 == x + 1 || xNO2 == x - 1)) {
                xNO3 = x;
                yNO3 = y;
                engine.setSaveX2(x);
                engine.setSaveY2(y);
                engine.setCount4(engine.getCount4() + 1);
                return true;
            }
        } else if ((xNO1 == x || yNO1 == y) && engine.getCount4() == 3
                && engine.getFourAmount() != 0) {
            if (xNO3 == x
                    && (yNO3 == y + 1 || yNO3 == y - 1)) {
                xNO4 = x;
                yNO4 = y;
                engine.setCount4(0);
                engine.decreaseAmountByOne("four");
                return true;
            } else if (yNO3 == y
                    && (xNO3 == x + 1 || xNO3 == x - 1)
                    && engine.getThreeAmount() != 0) {
                xNO4 = x;
                yNO4 = y;
                engine.setCount4(0);
                engine.decreaseAmountByOne("four");
                return true;
            }
        }
        return false;
    }

    @Override
    public void clean() {}

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
        return xNO4;
    }

    @Override
    public int getY4() {
        return yNO4;
    }
}
