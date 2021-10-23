package decorator.MP04.deco;

import decorator.MP04.Display;

public abstract class DisplayDecorator extends Display {
    DisplayDecorator(Display display, int width, int height) {
        super(width, height);
    }
}

