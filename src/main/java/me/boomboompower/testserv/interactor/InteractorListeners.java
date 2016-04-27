package me.boomboompower.testserv.interactor;

/**
 * This code is part of *Interactor*
 * A separate plugin by boomboompower
 *
 * http://github.com/boomboompower/Interactor/
 */

import me.boomboompower.testserv.TestServCore;

public class InteractorListeners {

    private TestServCore testServCore;

    public InteractorListeners(TestServCore testServCore) {
        this.testServCore = testServCore;

        new InteractorInteract(testServCore);
    }
}
