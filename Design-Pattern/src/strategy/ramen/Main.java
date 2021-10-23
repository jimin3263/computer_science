package strategy.ramen;

public class Main {
    public static void main(String[] args) {
        Ramen ramen = new Ramen();
        ramen.setRamenRecipe(new GeneralRamenCookRecipe());

        ramen.cook();

        ramen.setRamenRecipe(new RamenWithoutBrothRecipe());
        ramen.cook();
    }
}
