package composite.menu;

public class Waitron {
    MenuComponent allMenus;
    public Waitron(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    public void printMenu() {
        allMenus.print();
    }
}
