package composite.menu;

public class MenuItem implements MenuComponent{
    String name;
    String description;
    boolean vegetarian;
    double price;
    public MenuItem(String name, String desc,
                    boolean vege, double price) {
        this.name = name;
        this.description = desc;
        this.vegetarian = vege;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public void print() {
        System.out.print(" " + getName());
        if (isVegetarian()) {
            System.out.print("(v)");
        }

        System.out.println(", " + getPrice());
        System.out.println(" -- " + getDescription());
    }

}
