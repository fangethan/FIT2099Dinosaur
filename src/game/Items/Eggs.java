package game.Items;

import edu.monash.fit2099.engine.Location;
import game.Dinosaur.*;
import game.Player.EcoPoints;

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
            if(dinosaur.getDisplayChar() == 'B') {
                if (currentLocation.canActorEnter(dinosaur)) {
                    currentLocation.removeItem(this);
                    currentLocation.addActor(new Brachiosaur("Brachiosaur", 'M'));
                    EcoPoints.addPoints(100);
                }
            } else if (dinosaur.getDisplayChar() == 'P') {
                if (currentLocation.canActorEnter(dinosaur)) {
                    currentLocation.removeItem(this);
                    currentLocation.addActor(new Pterodactyls("Pterodactyl", 'M'));
                    EcoPoints.addPoints(100);
                }
            } else if (dinosaur.getDisplayChar() == 'S') {
                if (currentLocation.canActorEnter(dinosaur)) {
                    currentLocation.removeItem(this);
                    currentLocation.addActor(new Stegosaur("Stegosaur", 'M'));
                    EcoPoints.addPoints(100);
                }
            } else if (dinosaur.getDisplayChar() == 'A') {
                if (currentLocation.canActorEnter(dinosaur)) {
                    currentLocation.removeItem(this);
                    currentLocation.addActor(new Allosaur("Allosaur", 'M'));
                    EcoPoints.addPoints(100);
                }
            }
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
