package game;


import edu.monash.fit2099.engine.*;

import java.lang.reflect.Constructor;
import java.util.List;

public class VendingMachine extends Ground {

    private int fruit;
    private int vegetarianMealKit;
    private int carnivoreMealKit;
    private int stegosaurEgg;
    private int brachiosaurEgg;
    private int allosaurEgg;
    private int laserGun;

    public VendingMachine() {
        super('V');
        this.fruit = 30;
        this.vegetarianMealKit = 100;
        this.carnivoreMealKit = 500;
        this.stegosaurEgg = 200;
        this.brachiosaurEgg = 500;
        this.allosaurEgg = 1000;
        this.laserGun = 500;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }


    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions buyActions = new Actions();
        buyActions.add(new Buy("mealKit", 30));

        if (actor instanceof Player) {
            //buyac

        }

    return buyActions;
    }
}




