package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * This class is for eating
 */
public class Eat extends Action {
    private Location location;

    public Eat(Location location){
        this.location = location;
    };

    /**
     * This returns the string after eating
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 10);
        for (int i = 0; i < location.getItems().size(); i++) {
            location.removeItem(location.getItems().get(i));
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " ate fruit";
    }

}
