package game;

import edu.monash.fit2099.engine.Location;

/**
 * This class is for eggs which extend food
 */
public class Eggs extends Food{
    private Dinosaur dinosaur;
    private int hatch = 0;

    /**
     * the egg constructor
     * @param dinosaur is the dinosaur that laid the egg
     */
    public Eggs(Dinosaur dinosaur) {
        super("Egg", 'e', true);
        this.dinosaur = dinosaur;
    }

    /**
     * the tick to check when to hatch
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        hatch++;
        if (hatch == 3) {
            currentLocation.removeItem(this);
            currentLocation.addActor(new Pterodactyls("Pterodactyl", 'M'));
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
