package game.Ground;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Player.EcoPoints;
import game.Items.Fruit;

/**
 * This is a bush which is a ground type
 * this is also harvestable
 */
public class Bush extends Ground implements Harvestable {
    /**
     * This is the constructor
     * The display char is *
     */
    public Bush(){
        super('*');
    }

    /**
     * This is the harvest acton which adds item the fruit to inventory
     *
     * @param actor is the player
     * @param location is the location
     * @return string actor harvested fruit
     */
    @Override
    public String harvest(Actor actor, Location location) {
        // Add fruit to inventory

        Probability value = new Probability();
        float randomNumber = value.getProbability();
        if (randomNumber<=0.4){
            actor.addItemToInventory(new Fruit());
            location.removeItem(new Fruit());
            EcoPoints.addPoints(10);
           return actor + " harvested Fruit";

        }
        else{
            return actor + "did not find fruit ";
        }

    }
}
