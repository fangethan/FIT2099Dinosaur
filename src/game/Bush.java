package game;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Bush extends Ground implements Harvestable {
    public Bush(){
        super('*');
    }
    @Override
    public void tick(Location location) {
        super.tick(location);

        Probability value = new Probability();
        float randomNumber = value.getProbability();
        if (randomNumber<=0.1){
            location.addItem(new Fruit());
        }


    }
    @Override
    public String harvest(Actor actor, Location location) {
        // Add hay to inventory

        Probability value = new Probability();
        float randomNumber = value.getProbability();
        if (randomNumber<=0.01){
            actor.addItemToInventory(new Fruit());
            location.removeItem(new Fruit());
            return actor + "harvested Fruit";
        }
        else{
            return actor + "did not find fruit ";
        }

    }
}
