package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;



public class Harvest extends Action {

    private Harvestable harvestable;
    public Harvest(Harvestable harvestable) {
        this.harvestable = harvestable;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return harvestable.harvest(actor, map.locationOf(actor));
                }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvests " + harvestable;
    }

}


