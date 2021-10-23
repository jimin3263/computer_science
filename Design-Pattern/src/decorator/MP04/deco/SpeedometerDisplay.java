package decorator.MP04.deco;

import decorator.MP04.Display;

public class SpeedometerDisplay extends InfoDisplayDecorator{
    public SpeedometerDisplay(Display display, int width, int height){
        super(display, width, height);
    }

    @Override
    public String getDisplayTest() {
        return "Speed: 50";
    }
}
