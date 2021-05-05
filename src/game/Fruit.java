package game;

import edu.monash.fit2099.engine.Location;

public class Fruit extends Food{
    private int age= 0;
    public Fruit() {
        super("Fruit", 'f', true);
    }

    public void tick(Location currentLocation){
        age++;
        if(age == 15){
            currentLocation.removeItem(this);
        }
    }
    public String getName(){
        return "Fruit";
    }
}
