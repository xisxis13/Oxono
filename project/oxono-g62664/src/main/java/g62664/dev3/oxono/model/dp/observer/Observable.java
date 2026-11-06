package g62664.dev3.oxono.model.dp.observer;

public interface Observable {
    // methods
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
