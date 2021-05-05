package game;

public class CarnivoreMealKit extends MealKits{
    public String name;
    public CarnivoreMealKit() {
        super("CarnivoreMealKit", 'C');
        this.name = "CarnivoreMealKit";
    }
    public String getName(){
        return "CarnivoreMealKit";
    }
}
