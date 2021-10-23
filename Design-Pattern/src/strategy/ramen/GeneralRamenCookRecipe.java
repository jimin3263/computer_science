package strategy.ramen;

public class GeneralRamenCookRecipe implements RamenRecipe{
    @Override
    public void cook() {
        System.out.println("일반 방법");
    }
}
