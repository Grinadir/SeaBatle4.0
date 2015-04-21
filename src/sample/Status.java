package sample;

/**
 * Created by User on 11.04.2015.
 */
public class Status {

    private boolean followStep = true;
    private boolean startGame = false;
    private Counters count;

    public Status(Counters count) {
        this.count = count;
    }

    public boolean checkAndInstallStart() {
        if (count.isAllShipInstall()) {
            startGame = true;
        }
        return startGame;
    }

    public boolean isFollowStep() {
        return followStep;
    }

    public void setFollowStep(boolean followStep) {
        this.followStep = followStep;
    }


}
