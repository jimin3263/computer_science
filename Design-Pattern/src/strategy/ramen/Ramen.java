package strategy.ramen;

public class Ramen {
    private RamenRecipe ramenRecipe;

    public void setRamenRecipe(RamenRecipe ramenRecipe) {
        this.ramenRecipe = ramenRecipe;
    }

    public void cook(){
        ramenRecipe.cook();
    }
}
