package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.Random;

public class Lake extends Ground {

    int capacity;
    int turnNumber;

    /**
     * Constructor.

     */
    public Lake() {
        super('~');
        this.capacity = 25;
        
    }
    @Override
    public void tick(Location location) {
        super.tick(location);
        turnNumber++;
        if (turnNumber == 2) {
            Probability value = new Probability();
            float randomNumber = value.getProbability();

            if (randomNumber<0.9){
                Random r = new Random();

                float result = r.nextInt(6-1) + 1;
                float finalResult = result / 10;
                //double numberBetween = (Math.random() * ((6 - 1) + 1)) + 1;
                this.capacity += finalResult*20;
                //System.out.println(this.capacity );
                turnNumber = 0;
            }


        }

        }
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.getDisplayChar() == 'B'){
            return false;
        }
        return true;
    }
}
