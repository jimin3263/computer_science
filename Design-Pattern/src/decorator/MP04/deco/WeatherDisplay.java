package decorator.MP04.deco;

import decorator.MP04.Display;

public class WeatherDisplay extends InfoDisplayDecorator {

    public WeatherDisplay(Display display, int width, int height) {
        super(display,width, height);
    }

    @Override
    public String getDisplayTest() {
        return "Weather: 온도: 20도, 습도 :60";
    }

}
