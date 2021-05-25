package game.Dinosaur;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Action.AttackAction;

public class Pterodactyls extends Dinosaur {

    /**
     *
     * @param name the name of this Pterodactyl
     * @param gender the gender of this Pterodactyl
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
