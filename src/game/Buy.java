package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class Buy extends Action {
    private String item ;
    private int cost;
    public Buy(String item, int cost) {
        assert cost >= 0;

        this.item = item;
        this.cost = cost;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
            return "Buy:";
        }
    }

