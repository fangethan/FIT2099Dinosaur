package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Feed extends Action {
    private Dinosaur dinosaur;
    private Food food;

    public Feed(Dinosaur dinosaur, Food food) {
        this.dinosaur = dinosaur;
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {


        if (dinosaur.getDisplayChar() == 'S' || dinosaur.getDisplayChar() == 's') {
            actor.removeItemFromInventory(food);
            dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 20);
        } else if (dinosaur.getDisplayChar() == 'B' || dinosaur.getDisplayChar() == 'b') {
            actor.removeItemFromInventory(food);
            dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 20);
        } else if (dinosaur.getDisplayChar() == 'P' || dinosaur.getDisplayChar() == 'p') {
            actor.removeItemFromInventory(food);
            dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 20);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fed dinosaur";
    }

}
