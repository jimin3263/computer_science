package command.MP03commandVer2.subject;


import command.MP03commandVer2.observer.Observer;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
