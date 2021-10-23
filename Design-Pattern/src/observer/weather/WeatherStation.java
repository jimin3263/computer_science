package observer.weather;

import observer.weather.observer.CurrentConditionsDisplay;
import observer.weather.subject.WeatherData;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setTemperature(80, 65,30.4f);
    }
}
