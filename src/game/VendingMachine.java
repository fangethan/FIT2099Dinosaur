package game;


import edu.monash.fit2099.engine.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine extends Ground {

    /**
     * itemMap of whats in the vending machine
     */
    public HashMap<Item, Integer> ItemMap = new HashMap<Item, Integer>();

    /**
     * constructor of vending machine
     */
    public VendingMachine() {
        super('V');
        ItemMap.put(new Fruit(), 30);
        ItemMap.put(new VegitarianMealKit(), 100);
        ItemMap.put(new CarnivoreMealKit(), 500);
        ItemMap.put(new StegosaurEgg(), 200);
        ItemMap.put(new BrachiosaurEgg(), 500);
        ItemMap.put(new AllosaurEgg(), 100);
        ItemMap.put(new LaserGun(), 500);
        ItemMap.put(new PterodactylEggs(), 200);


    }

    /**
     * tells actor can't enter into a vending machine
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * locks thrown objects but not movement
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * this is used to create new attack actions
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {

        Actions buyActions = new Actions();

        if (actor instanceof Player) {
            for (Map.Entry<Item, Integer> items : ItemMap.entrySet()) {
                if (((Player) actor).getEcoPoints() >= items.getValue()) {
                    buyActions.add(new Buy(items.getKey(), items.getValue()));
                }


            }
        }
        return buyActions;
    }
}





