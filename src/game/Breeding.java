package game;

import edu.monash.fit2099.engine.Capable;

public enum Breeding {
    male,
    female,
    // male looking for a mate
    eligibleMale,
    // female looking for a mate
    eligibleFemale,
    pregnantFemale,
    baby;

    // This should be stored as a capability to allow other dinosaurs to search this one out as a mate.
    public Breeding findMate() {
        if (this == male) {
            return eligibleFemale;
        } else {
            return eligibleMale;
        }
    }



    //
    public static boolean canMate(Capable capable) {
        return capable.hasCapability(eligibleFemale) || capable.hasCapability(eligibleMale);
    }
}
