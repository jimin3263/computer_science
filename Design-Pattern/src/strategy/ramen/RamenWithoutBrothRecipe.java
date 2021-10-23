package strategy.ramen;

public class RamenWithoutBrothRecipe implements RamenRecipe {
    @Override
    public void cook() {
        System.out.println("물을 적게 넣고 라면을 익힌 뒤에 라면 스프에 볶듯이 끓임");
    }
}
