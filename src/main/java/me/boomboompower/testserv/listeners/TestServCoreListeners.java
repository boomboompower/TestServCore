package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 26/04/2016
*/

import me.boomboompower.testserv.TestServCore;

public class TestServCoreListeners {

    private TestServCore testServCore;

    public TestServCoreListeners(TestServCore testServCore) {
        this.testServCore = testServCore;

        new TestServCoreBlockHandler(testServCore);
        new TestServCoreMobSpawning(testServCore);
        new TestServCoreExplosions(testServCore);
        new TestServCorePaintings(testServCore);
        new TestServCoreJoin(testServCore);
        new TestServCoreQuit(testServCore);
        new TestServCorePing(testServCore);
        new TestServCoreSwap(testServCore);
        new TestServCoreChat(testServCore);
        new TestServCorePVP(testServCore);
    }
}
