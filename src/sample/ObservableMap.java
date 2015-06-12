package sample;

/**
 * Created by User on 10.06.2015.
 */
public interface ObservableMap {

    void registerObserver(ObserverOfMap o);
    void removeObserver(ObserverOfMap o);
    void notify(int x, int y, String fettle);
}
