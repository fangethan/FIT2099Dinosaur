package game;

import edu.monash.fit2099.engine.Item;

public abstract class Food extends Item {
    public Food(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
