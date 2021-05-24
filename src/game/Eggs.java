package game;

import edu.monash.fit2099.engine.Location;

/**
 * This class is for eggs which extend food
 */
public class Eggs extends Food{
    private Dinosaur dinosaur;
    private int hatch = 0;

    /**
     * This is the cosntructor
     * display char is e
     */
    public Eggs(Dinosaur dinosaur) {
        super("Egg", 'e', true);
        this.dinosaur = dinosaur;
    }

    @Override
    public void tick(Location currentLocation) {
        hatch++;
        if (hatch == 3) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Pterodactyls("Pterodactyls", 'M'));
        }
    }


    /**
     * THis gets the name
     * @return egg
     */
    public String getName(){
        return "Egg";
    }
}
