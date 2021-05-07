package game;

import java.util.Random;

public class Probability {
    /**
     * gets the probability of growing a bush
     * @return a random value that will be tested to see if dirt can grow into bush
     */
    public float getProbability(){
        Random probabilityValue = new Random();
        return probabilityValue.nextFloat();
    }
}
