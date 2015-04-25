package sample;

public class Status {

    private boolean followStep = true;
    private boolean startGame = false;
    private Engine engine;

    public Status(Engine engine) {
        this.engine = engine;
    }

    public boolean checkAndInstallStart() {
        if (engine.isAllShipInstall()) {
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
