package game;

import edu.monash.fit2099.engine.Location;

public class Fruit extends Food{
    /**
     * age of the fruit
     */
    private int age= 0;

    /**
     * constructor of the fruit
     */
    public Fruit() {
        super("Fruit", 'f', true);
    }

    /**
     * tick method is used to see if the item isn't rotten
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation){
        age++;
        if(age == 15){
            currentLocation.removeItem(this);
        }
    }

    /**
     * getName just retrieves the fruit name
     * @return returns the string of Fruit
     */
    public String getName(){
        return "Fruit";
    }
}
