package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.lang.reflect.Constructor;

public class Buy extends Action {
    private Item item ;
    private int cost;
    public Buy(Item item, int cost) {
        assert cost >= 0;

        this.item = item;
        this.cost = cost;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(this.item);
        ((Player) actor).deductEcoPoints(cost);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        Class<?> currentClass = this.item.getClass();

            return "Buy:" + currentClass.getSimpleName() + ",Cost:" + this.cost;

        }
    }

