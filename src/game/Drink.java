package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Drink extends Action {
    private Location location;

    public Drink(Location location){
        this.location = location;
    };

    /**
     * This returns the string after eating
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        if (dinosaur.getDisplayChar() == 'B' || dinosaur.getDisplayChar() == 'b') {
            dinosaur.setWaterLevels(dinosaur.getFoodLevel() + 80);
            if (dinosaur.getWaterLevels() > 200) {
                dinosaur.setWaterLevels(200);
            }
        } else {
            dinosaur.setWaterLevels(dinosaur.getFoodLevel() + 30);
            if (dinosaur.getWaterLevels() > 100) {
                dinosaur.setWaterLevels(100);
            }
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "drank water";
    }
}
