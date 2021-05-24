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
     * the execute method on execute the drink action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the menuDescription
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

    /**
     * menu description of drink
     * @param actor The actor performing the action.
     * @return a string of the actor drank water
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "drank water";
    }
}
