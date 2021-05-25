package game.Action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Dinosaur.Dinosaur;
import game.Player.EcoPoints;
import game.Items.Food;

public class Feed extends Action {
    private Dinosaur dinosaur;
    private Food food;

    /**
     * the feed constructor
     * @param dinosaur is the dinosaur that is getting fed
     * @param food is the food that will be given to the dinosaur
     */
    public Feed(Dinosaur dinosaur, Food food) {
        this.dinosaur = dinosaur;
        this.food = food;
    }

    /**
     * the execute method that executes the feed action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the menuDescription
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (dinosaur.getDisplayChar() == 'S' || dinosaur.getDisplayChar() == 's') {
            actor.removeItemFromInventory(food);
            dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 20);
            EcoPoints.addPoints(10);
        } else if (dinosaur.getDisplayChar() == 'B' || dinosaur.getDisplayChar() == 'b') {
            actor.removeItemFromInventory(food);
            dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 20);
            EcoPoints.addPoints(10);
        } else if (dinosaur.getDisplayChar() == 'P' || dinosaur.getDisplayChar() == 'p') {
            actor.removeItemFromInventory(food);
            dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 20);
            EcoPoints.addPoints(10);
        }
        return menuDescription(actor);
    }

    /**
     * menu description of feed
     * @param actor The actor performing the action.
     * @return a string of the actor fed dinosaur
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fed dinosaur";
    }

}
