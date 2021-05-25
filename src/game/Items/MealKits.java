package game.Items;

public abstract class MealKits extends Food{
    /**
     * Constructor of MealKits
     * @param name the name of the mealkit
     * @param charter is a displayChar to show the character of the mealkit
     */
    public MealKits(String name, char charter) {
        super("MealKit", 'M', true);
    }

}
