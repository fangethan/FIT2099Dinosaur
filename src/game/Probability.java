package game;

import java.util.Random;

public class Probability {

    public float getProbability(){
        Random probabilityValue = new Random();
        return probabilityValue.nextFloat();
    }
}
