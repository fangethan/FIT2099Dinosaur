package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class Pterodactyls extends Dinosaur{

    /**
     *
     * @param name the name of this Stegosaur
     * @param gender the gender of this Stegosaur
     */
    public Pterodactyls(String name, char gender) {
        super(name, 'P', 100, gender);
    }

    /**
     * this is used to create new attack actions
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }
}
