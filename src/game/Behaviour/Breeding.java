package game.Behaviour;

import edu.monash.fit2099.engine.Capable;

/**
 * This breeding class checks the status of the dinsour when mating
 */
public enum Breeding {
    male,
    female,
    // male looking for a mate
    eligibleMale,
    // female looking for a mate
    eligibleFemale,
    pregnantFemale,
    baby;

    /**
     * his should be stored as a capability to allow other dinosaurs to search this one out as a mate.
     * @return elibgle person
     */

    public Breeding findMate() {
        if (this == male) {
            return eligibleFemale;
        } else {
            return eligibleMale;
        }
    }


    /**
     * This checks if they can mate
     * @param capable
     * @return true or false
     */
    public static boolean canMate(Capable capable) {
        return capable.hasCapability(eligibleFemale) || capable.hasCapability(eligibleMale);
    }
}
