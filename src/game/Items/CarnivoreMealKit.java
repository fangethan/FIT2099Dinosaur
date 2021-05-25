package game.Items;

import game.Items.MealKits;

/**
 * THis class creates a carnivoreMealKit which extends Mealkit class
 */
public class CarnivoreMealKit extends MealKits {
    public String name;

    /**
     * This is the cosntructor for it
     */
    public CarnivoreMealKit() {
        super("CarnivoreMealKit", 'C');
        this.name = "CarnivoreMealKit";
    }

    /**
     * This retruns the name of the kit
     * @return string carnivoreMealKit
     */
    public String getName(){
        return "CarnivoreMealKit";
    }
}
