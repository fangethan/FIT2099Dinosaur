package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Drink extends Action {
    private Location location;
    private Fruit fruit;

    public Drink(Location location, Fruit fruit){
        this.location = location;
        this.fruit = fruit;
    };

    /**
     * This returns the string after eating
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 10);
        location.removeItem(fruit);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "drank water";
    }
}
