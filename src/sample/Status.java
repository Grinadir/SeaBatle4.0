package sample;

/**
 * Created by User on 11.04.2015.
 */
public class Status {

    private boolean followStep=true;
    private boolean startGame=false;

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
}
