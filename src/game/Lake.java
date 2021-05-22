package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Lake extends Ground {


    int capacity;
    int turnNumber;
    ArrayList<Fish> fishList = new ArrayList<Fish>();

    /**
     * Constructor.

     */
    public Lake() {
        super('~');
        this.capacity = 25;

        for (int i = 0; i< 5; i++){
            fishList.add(new Fish());
        }


    }
    @Override
    public void tick(Location location) {
        super.tick(location);

        //Adding fish each turn probability 60%
        turnNumber++;
        Probability value = new Probability();
        float randomNumber = value.getProbability();

        if (randomNumber<0.6){
            if (fishList.size() <=25) {
                fishList.add(new Fish());
                //System.out.println(fishList.size());
            }
        }



        // Rainfall
        if (turnNumber == 2) {

            if (randomNumber<0.2){
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

        if (actor.getDisplayChar() == 'P' ||actor.getDisplayChar() == 'p'  ){
                return true;
            }
            return false;
        }

    }
