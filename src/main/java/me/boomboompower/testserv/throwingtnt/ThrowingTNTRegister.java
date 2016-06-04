package me.boomboompower.testserv.throwingtnt;

/**
 * This code is part of *ThrowingTNT*
 * A separate plugin by boomboompower
 *
 * http://github.com/boomboompower/ThrowingTNT/
 */

import me.boomboompower.testserv.TestServCore;

public class ThrowingTNTRegister {

    private TestServCore testServCore;

    public ThrowingTNTRegister(TestServCore testServCore) {
        this.testServCore = testServCore;

        new ThrowingTNTInteract(testServCore);
    }
}
