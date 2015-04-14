package sample;

/**
 * Created by User on 11.04.2015.
 */
public class Status {

    private boolean followStep=true;
    private boolean startGame=false;
    private Counters count;

    public Status(Counters count){
        this.count=count;
    }

    public boolean checkAndInstallStart(){
        if(isAllShipInstall()){
            startGame=true;
        }
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }
    public boolean isStartGame() {
        return startGame;
    }
    public boolean isFollowStep() {
        return followStep;
    }
    public void setFollowStep(boolean followStep) {
        this.followStep = followStep;
    }
    private boolean isAllShipInstall(){
        boolean a=count.getOneAmount()==0;
        boolean b=count.getTwoAmount()==0;
        boolean c=count.getThreeAmount()==0;
        boolean d=count.getFourAmount()==0;

        return a&&b&&c&&d;
    }

}
