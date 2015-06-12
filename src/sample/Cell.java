package sample;

/**
 * Created by User on 08.06.2015.
 */
public class Cell {
    private int cellX;
    private int cellY;
    private int veto=0;

    public Cell(int cellX, int cellY, String fettle){
        this.cellX=cellX;
        this.cellY=cellY;
        this.fettle=fettle;
    }

    public String getFettle() {
        return fettle;
    }

    public void setFettle(String fettle) {
        this.fettle = fettle;
    }

    private String fettle;
    public int getVeto(){
        return veto;
    }
    public void setVeto(int v){
        this.veto=v;
    }
}
