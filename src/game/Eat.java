package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * This class is for eating
 */
public class Eat extends Action {
    private Location location;

    /**
     * this is the eat constructor
     * @param location which receives the location of where the food is
     */
    public Eat(Location location){
        this.location = location;
    };

    /**
     * execute the eat action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the menuDescription
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;

        // go through every item on that location spot
        for (int i = 0; i < location.getItems().size(); i++) {

            // if it is a stegosaur, improve food level by that amount else if it is a brachiosaur
            if (dinosaur.getDisplayChar() == 'S' || dinosaur.getDisplayChar() == 's') {
                // if location contains fruit
                if (location.getItems().get(i).getDisplayChar() == 'f') {
                    location.removeItem(location.getItems().get(i));
                    dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 10);
                }
            } else if (dinosaur.getDisplayChar() == 'B' || dinosaur.getDisplayChar() == 'b') {
                // if location contains fruit
                if (location.getItems().get(i).getDisplayChar() == 'f') {
                    location.removeItem(location.getItems().get(i));
                    dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 5);
                }
            } else if (dinosaur.getDisplayChar() == 'P' || dinosaur.getDisplayChar() == 'p') {
                // if location contains a corpse or fish then check if it is a pterodactyl
                if (location.getItems().get(i).getDisplayChar() == '%') {
                    if (i == 2) {
                        break;
                    } else {
                        location.removeItem(location.getItems().get(i));
                        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 30);
                        if (dinosaur.getFoodLevel() > 100) {
                            dinosaur.setFoodLevels(100);
                        }
                    }
                } else if(location.getItems().get(i).getDisplayChar() == 'c') {
                    Corpse corpse = (Corpse) location.getItems().get(i);
                    if(corpse.getSpecies() == 'S' || corpse.getSpecies() == 's' ||
                            corpse.getSpecies() == 'A' || corpse.getSpecies() == 'a') {
                        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 50);
                        location.removeItem(location.getItems().get(i));
                        if (dinosaur.getFoodLevel() > 100) {
                            dinosaur.setFoodLevels(100);
                        }
                    } else if (corpse.getSpecies() == 'B' || corpse.getSpecies() == 'b') {
                        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 100);
                        location.removeItem(location.getItems().get(i));
                        if (dinosaur.getFoodLevel() > 100) {
                            dinosaur.setFoodLevels(100);
                        }
                    }
                }
            } else if (dinosaur.getDisplayChar() == 'A' || dinosaur.getDisplayChar() == 'a') {
                // if location contains a corpse and checks if it is an allosaur
                if (location.getItems().get(i).getDisplayChar() == 'c') {
                    Corpse corpse = (Corpse) location.getItems().get(i);
                    if (corpse.getSpecies() == 'B' || corpse.getSpecies() == 'b') {
                        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 100);
                        location.removeItem(location.getItems().get(i));
                        if (dinosaur.getFoodLevel() > 100) {
                            dinosaur.setFoodLevels(100);
                        }
                    } else if (corpse.getSpecies() == 'S' || corpse.getSpecies() == 's' || corpse.getSpecies() == 'A' || corpse.getSpecies() == 'a') {
                        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 50);
                        location.removeItem(location.getItems().get(i));
                        if (dinosaur.getFoodLevel() > 100) {
                            dinosaur.setFoodLevels(100);
                        }
                    } else if (corpse.getSpecies() == 'P' || corpse.getSpecies() == 'p') {
                        dinosaur.setFoodLevels(dinosaur.getFoodLevel() + 30);
                        location.removeItem(location.getItems().get(i));
                        if (dinosaur.getFoodLevel() > 100) {
                            dinosaur.setFoodLevels(100);
                        }
                    }
                }
            }
        }



        return menuDescription(actor);
    }

    /**
     * menu description of eat
     * @param actor The actor performing the action.
     * @return a string of the actor ate food
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " ate food";
    }

}
