package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by User on 04.05.2015.
 */
public class Map implements ObservableMap {


    private Cell[] cellMY = new Cell[100];
    private Cell[] cellENEMY = new Cell[100];
    private ArrayList observers;
    private int mapX;
    private int mapY;
    private int veto = 0;
    private InterfaceShip ship;
    private final Engine engine;

    public Map(Engine engine) {
        this.engine=engine;
        makeEnemyAndMyCells();
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(ObserverOfMap o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverOfMap o) {

    }

    @Override
    public void notify(int x, int y, String fettle) {
        for (int i = 0; i < observers.size(); i++) {
            ObserverOfMap observer = (ObserverOfMap) observers.get(i);
            observer.update(x, y, fettle);
        }

    }


    private void makeOneIterationCellMY(int i) {
        int numLine = (int) (10 - (10 - i * 0.1));
        cellMY[i] = new Cell(i - numLine * 10, numLine, "non");

    }

    private void makeOneIterationCellENEMY(int i) {
        int numLine = (int) (10 - (10 - i * 0.1));
        cellENEMY[i] = new Cell(i - numLine * 10, numLine, "non");
    }

    public void makeEnemyAndMyCells() {
        for (int i = 0; i <= 99; ++i) {
            makeOneIterationCellMY(i);
            makeOneIterationCellENEMY(i);
        }
    }

    void mainFunctionInMap(int x, int y) {
        int i = x + y * 10;
        if (engine.getOneAmount() == 0
                && (!cellMY[i].getFettle().equals("nearship") && !cellMY[i].getFettle().equals("non"))) {
            if (cellMY[i].getFettle().equals("ship")) {
                cellMY[i].getFettle().equals("non");
                engine.increaseAmountByOne("one");
                engine.getLogicMarked().marketGreen(x, y);
                notify(x, y, "non");
            }
        } else if (isSelectedSingleShip(x, y)) {
            if (engine.getOneAmount() <= 4 && engine.getOneAmount() >= 1) {
                makeSingleShipInMap(x, y);
            }
        } else if (isSelectedDoubleShip(x, y)) {
            makeDoubleShipForThreeInMap(x, y);
        } else if (isSelectedTripleShip(x, y)) {
            makeTripleShipForBothInMap(x, y);
        } else if (isSelectedQuadrupleShip(x, y)) {
            makeQuadrupleShipInMap(x, y);
        }
    }

    private boolean isSelectedSingleShip(int x, int y) {
        return (engine.getGui().getSettings().isOne()&&!cellMY[x+10*y].getFettle().equals("nearship"))
                && engine.getOneAmount() != 0;
    }

    private boolean isSelectedDoubleShip(int x, int y) {
        return (engine.getGui().getSettings().isTwo()&&!cellMY[x+10*y].getFettle().equals("nearship"))
                && engine.getTwoAmount() != 0;
    }

    private boolean isSelectedTripleShip(int x, int y) {
        return (engine.getGui().getSettings().isThree()&&!cellMY[x+10*y].getFettle().equals("nearship"))
                && engine.getThreeAmount() != 0;
    }

    private boolean isSelectedQuadrupleShip(int x, int y) {
        return (engine.getGui().getSettings().isFour()&&!cellMY[x+10*y].getFettle().equals("nearship"))
                && engine.getFourAmount() != 0;
    }


    //Extract-function for foundation ship
    private void makeSingleShipInMap(int x, int y) {
        engine.getShipSingle()[engine.getOneAmount()] = new ShipSingle(engine);
        ship = engine.getShipSingle()[engine.getOneAmount()];

        if (cellMY[x + 10 * y].getFettle() == "non" && engine.getShipSingle()[engine.getOneAmount()].make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            engine.getLogicMarked().marketYellow(x, y);
            notify(x, y, "ship");
        } else {
            engine.getShipSingle()[engine.getOneAmount()].clean();
            engine.getLogicMarked().marketGreen(x, y);
            cellMY[x + 10 * y].setFettle("non");
            notify(x, y, "non");
            ship = null;
        }
    }

    private void makeDoubleShipForThreeInMap(int x, int y) {
        if (engine.getCount2() == 0) {
            engine.getShipDouble()[engine.getTwoAmount()] = new ShipDouble(engine);
            ship = engine.getShipDouble()[engine.getTwoAmount()];
            if (ship.make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
            }
        } else if (engine.getCount2() == 1 && engine.getTwoAmount() != -1) {
            ship = engine.getShipDouble()[engine.getTwoAmount()];
            if (ship.make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
                engine.getLogicMarked().marketYellow(x, y);
                if (engine.getSaveX() == x) {
                    engine.getLogicMarked().marketYellow(x, y - 1);
                    engine.getLogicMarked().marketYellow(x, y + 1);
                } else if (engine.getSaveY() == y) {
                    engine.getLogicMarked().marketYellow(x - 1, y);
                    engine.getLogicMarked().marketYellow(x + 1, y);
                }
            }
        }
    }

    private void makeTripleShipForBothInMap(int x, int y) {

        if (engine.getCount3() == 0 && engine.getThreeAmount() != -1) {
            engine.getShipTriple()[engine.getThreeAmount()] = new ShipTriple(engine);
            ship = engine.getShipTriple()[engine.getThreeAmount()];
            if (ship.make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
            }
        } else if (engine.getCount3() == 1 && engine.getThreeAmount() != -1) {
            ship = engine.getShipTriple()[engine.getThreeAmount()];
            if (ship.make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
            }
        } else if (engine.getCount3() == 2 && engine.getThreeAmount() != -1) {
            ship = engine.getShipTriple()[engine.getThreeAmount()];
            if (ship.make(x, y)) {
                cellMY[x + 10 * y].setFettle("ship");
                notify(x, y, "ship");
                engine.getLogicMarked().marketYellow(engine.getSaveX(), engine.getSaveY());
                engine.getLogicMarked().marketYellow(engine.getSaveX1(), engine.getSaveY1());
                engine.getLogicMarked().marketYellow(x, y);
            }
        }
    }

    private void makeQuadrupleShipInMap(int x, int y) {
        ship = engine.getShipQuadruple();
        if (engine.getCount4() == 0 && ship.make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
        } else if (engine.getCount4() == 1 && ship.make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
        } else if (engine.getCount4() == 2 && ship.make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
        } else if (engine.getCount4() == 3 && ship.make(x, y)) {
            cellMY[x + 10 * y].setFettle("ship");
            notify(x, y, "ship");
            engine.getLogicMarked().marketYellow(engine.getSaveX(), engine.getSaveY());
            engine.getLogicMarked().marketYellow(engine.getSaveX1(), engine.getSaveY1());
            engine.getLogicMarked().marketYellow(engine.getSaveX2(), engine.getSaveY2());
            engine.getLogicMarked().marketYellow(x, y);
        }
    }

    public Cell getCellMY(int x, int y) {
        return cellMY[y * 10 + x];
    }

    public Cell getCellENEMY(int i) {
        return cellENEMY[i];
    }


}
