package prac.mid01.subject;

import observer.MP03.observer.Observer;
import observer.MP03.subject.Subject;

import java.time.LocalTime;
import java.util.ArrayList;

public class TimeThread implements Runnable, Subject {
    private ArrayList<Observer> observers;
    private static final int SLEEPTIME = 1000;
    private boolean stopRunning = false;

    private String now;
    private LocalTime time;

    public TimeThread() {
        observers = new ArrayList<>();
    }

    private void time(){

        while (stopRunning == false){
            time = LocalTime.now();
            now = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
            System.out.println(now);
            notifyObservers();
            try {
                Thread.sleep(SLEEPTIME); // 1초 쉼
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        time();
    }

    public void stopRunning() {
        stopRunning = true;
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if( i>= 0){
            observers.remove(o);
        }

    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.updateText(" "+ now);
        }

    }
}
