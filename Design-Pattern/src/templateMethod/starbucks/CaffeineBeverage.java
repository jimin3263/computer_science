package templateMethod.starbucks;

public abstract class CaffeineBeverage {
    public final void prepareRecipe(){ //템플릿 메소드
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    public abstract void brew();
    public abstract void addCondiments();
    public void boilWater(){
        System.out.println("물 끓이는 중");
    }
    public void pourInCup(){
        System.out.println("컵에 따르는 중");
    }
}
