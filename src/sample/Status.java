package sample;

public class Status {
    private boolean followStep = true;
    private boolean ready = false;

    public boolean isFollowStep() {
        return followStep;
    }

    public void setFollowStep(boolean followStep) {
        this.followStep = followStep;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

}
