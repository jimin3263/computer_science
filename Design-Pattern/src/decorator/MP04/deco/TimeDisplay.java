package decorator.MP04.deco;

import decorator.MP04.Display;

import java.time.LocalDateTime;

public class TimeDisplay extends InfoDisplayDecorator {

    public TimeDisplay(Display display, int width, int height) {
        super(display,width, height);
    }

    @Override
    public String getDisplayTest() {
        return LocalDateTime.now().toString();
    }

}
