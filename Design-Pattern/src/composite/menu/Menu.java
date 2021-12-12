package composite.menu;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu implements MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList();
    String name;
    String description;

    public Menu(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    public void add(MenuComponent comp) {
        menuComponents.add(comp);
    }

    public void remove(MenuComponent comp) {
        menuComponents.remove(comp);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void print() {
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("---------------------");
        Iterator it = menuComponents.iterator();
        while (it.hasNext()) {
            MenuComponent component = (MenuComponent) it.next();
            component.print();
        }

    }
}