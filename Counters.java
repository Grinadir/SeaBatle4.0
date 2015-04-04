package sample;

/**
 * Created by User on 04.04.2015.
 */
public class Counters {




    private Ship[] shipSingle = new Ship[5];//Эту статику не могу убрать, так как она должна быть по логике
    private Ship[] shipDouble = new Ship[4];//Эту статику не могу убрать, так как она должна быть по логике
    private Ship[] shipTriple = new Ship[3];//Эту статику не могу убрать, так как она должна быть по логике
    private Ship shipQuadruple = new Ship(4);//Эту статику не могу убрать, так как она должна быть по логике

    private int count2 = 0;//Эту статику не могу убрать, так как она должна быть по логике
    private int count3 = 0;//Эту статику не могу убрать, так как она должна быть по логике
    private int count4 = 0;//Эту статику не могу убрать, так как она должна быть по логике

    private int oneAmount = 4;//Эту статику не могу убрать, так как она должна быть по логике
    private int twoAmount = 3;//Эту статику не могу убрать, так как она должна быть по логике
    private int threeAmount = 2;//Эту статику не могу убрать, так как она должна быть по логике
    private int fourAmount = 1;//Эту статику не могу убрать, так как она должна быть по логике

    private int saveX;//Эту статику не могу убрать, так как она должна быть по логике
    private int saveY;//Эту статику не могу убрать, так как она должна быть по логике
    private int saveX1;//Эту статику не могу убрать, так как она должна быть по логике
    private int saveY1;//Эту статику не могу убрать, так как она должна быть по логике
    private int saveX2;//Эту статику не могу убрать, так как она должна быть по логике
    private int saveY2;//Эту статику не могу убрать, так как она должна быть по логике

    public Ship[] getShipSingle() {
        return shipSingle;
    }

    public Ship[] getShipDouble() {
        return shipDouble;
    }

    public Ship[] getShipTriple() {
        return shipTriple;
    }

    public Ship getShipQuadruple() {
        return shipQuadruple;
    }

    public int getCount2() {
        return count2;
    }

    public int getCount3() {
        return count3;
    }

    public int getCount4() {
        return count4;
    }

    public int getOneAmount() {
        return oneAmount;
    }

    public int getTwoAmount() {
        return twoAmount;
    }

    public int getThreeAmount() {
        return threeAmount;
    }

    public int getFourAmount() {
        return fourAmount;
    }

    public int getSaveX() {
        return saveX;
    }

    public int getSaveY() {
        return saveY;
    }

    public int getSaveX1() {
        return saveX1;
    }

    public int getSaveY1() {
        return saveY1;
    }

    public int getSaveX2() {
        return saveX2;
    }

    public int getSaveY2() {
        return saveY2;
    }


    public void setShipSingle(Ship[] shipSingle) {
        this.shipSingle = shipSingle;
    }

    public void setShipDouble(Ship[] shipDouble) {
        this.shipDouble = shipDouble;
    }

    public void setShipTriple(Ship[] shipTriple) {
        this.shipTriple = shipTriple;
    }

    public void setShipQuadruple(Ship shipQuadruple) {
        this.shipQuadruple = shipQuadruple;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public void setOneAmount(int oneAmount) {
        this.oneAmount = oneAmount;
    }

    public void setTwoAmount(int twoAmount) {
        this.twoAmount = twoAmount;
    }

    public void setThreeAmount(int threeAmount) {
        this.threeAmount = threeAmount;
    }

    public void setFourAmount(int fourAmount) {
        this.fourAmount = fourAmount;
    }

    public void setSaveX(int saveX) {
        this.saveX = saveX;
    }

    public void setSaveY(int saveY) {
        this.saveY = saveY;
    }

    public void setSaveX1(int saveX1) {
        this.saveX1 = saveX1;
    }

    public void setSaveY1(int saveY1) {
        this.saveY1 = saveY1;
    }

    public void setSaveX2(int saveX2) {
        this.saveX2 = saveX2;
    }

    public void setSaveY2(int saveY2) {
        this.saveY2 = saveY2;
    }
}
