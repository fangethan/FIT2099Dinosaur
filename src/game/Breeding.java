package game;

import edu.monash.fit2099.engine.Capable;

public enum Breeding {
    male,
    female,
    // male looking for a mate
    fertileMale,
    // female looking for a mate
    fertileFemale,
    pregnantFemale,
    baby;

    // This should be stored as a capability to allow other dinosaurs to search this one out as a mate.
    public Breeding findMate() {
        if (this == male) {
            return fertileFemale;
        } else {
            return fertileMale;
        }
    }

    //
    public boolean isPregnant(Capable capable) {
        return capable.hasCapability(fertileFemale) || capable.hasCapability(fertileMale);
    }
}
