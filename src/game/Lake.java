package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is for the water in the same
 */
public class Lake extends Ground {

    int capacity;
    int turnNumber;
    // Creates an array with the fish in each section
    ArrayList<Fish> fishList = new ArrayList<Fish>();

    /**
     * The lake constructor
     */
    public Lake() {
        super('~');
        this.capacity = 25;
        // Adds 5 fishes at the start of the game
        for (int i = 0; i< 5; i++){
            fishList.add(new Fish());
        }


    }

    /**
     * This is a tick function which adds fishs finsihes each turn with a probabity of 60%
     * THis aso checks every 10 turns if it rains with a probabiity of 20%
     * @param location The location of the Ground
     */
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
                location.addItem(new Fish());
                //System.out.println(fishList.size());
            }
        }

        // Rainfall
        if (turnNumber == 10) {

            if (randomNumber<0.2){
                Random r = new Random();

                float result = r.nextInt(6-1) + 1;
                float finalResult = result / 10;

                this.capacity += finalResult*20;
                //System.out.println(this.capacity );
                turnNumber = 0;
            }


        }

    }

    /**
     * THis makes it so only baby pretoroactyls and adult pterodactyles can enter
     * @param actor the Actor to check
     * @return true or false
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        if (actor.getDisplayChar() == 'P' ||actor.getDisplayChar() == 'p'  ){
                return true;
            }
            return false;
        }

    }
