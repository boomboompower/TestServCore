package me.boomboompower.testserv.interactor;

/**
 * This code is part of *Interactor*
 * A separate plugin by boomboompower
 * <p>
 * http://github.com/boomboompower/Interactor/
 * The link above is no longer valid
 */

import me.boomboompower.testserv.TestServCore;

public class InteractorListeners {

    private TestServCore testServCore;

    public InteractorListeners(TestServCore testServCore) {
        this.testServCore = testServCore;

        new InteractorInteract(testServCore);
        new InteractorDoors(testServCore);
    }
}
