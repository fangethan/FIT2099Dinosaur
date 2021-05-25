package game.Action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Player.Player;

/**
 * This is the buy class which buys an item from the vending machine
 *
 */
public class Buy extends Action {
    private Item item ;
    private int cost;

    /**
     * THis is the buy constructor
     * @param item is the item wanted to buy
     * @param cost is the cost of the item
     */
    public Buy(Item item, int cost) {

        this.item = item;
        this.cost = cost;
    }

    /**
     * This deducts the players points from his inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(this.item);
        ((Player) actor).deductEcoPoints(cost);
        return menuDescription(actor);
    }

    /**
     * This gets the item class name and prints it out
     * @param actor The actor performing the action.
     * @return string buy
     */
    @Override
    public String menuDescription(Actor actor) {
        Class<?> currentClass = this.item.getClass();

            return "Buy:" + currentClass.getSimpleName() + ",Cost:" + this.cost;

        }
    }

