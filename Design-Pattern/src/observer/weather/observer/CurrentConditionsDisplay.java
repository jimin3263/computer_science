package observer.weather.observer;

import observer.weather.subject.Subject;

//보여주고 업데이트
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this); //생성시 등록까지
    }

    @Override
    public void display() {
        System.out.println("Current conditions: "+ temperature + "F degrees and "+ humidity + "% humidity" );

    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature =temp;
        this.humidity = humidity;
        display();
    }
}
