package me.boomboompower.testserv.listeners;

/*
* Made for TestServ Core
* by boomboompower 27/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

public class TestServCoreListeners {

    private TestServCore testServCore;

    public TestServCoreListeners(TestServCore testServCore) {
        this.testServCore = testServCore;

        new TestServCoreBlockHandler(testServCore);
        new TestServCoreMobSpawning(testServCore);
        new TestServCorePlayerDeath(testServCore);
        // new TestServCorePlayerItems(testServCore); // DOESN'T WORK!
        new TestServCoreProjectiles(testServCore);
        new TestServCorePlayerMove(testServCore);
        new TestServCoreExplosions(testServCore);
        new TestServCorePaintings(testServCore);
        new TestServCoreFurnaces(testServCore);
        new TestServCoreMobDeath(testServCore);
        // new TestServCoreVehicles(testServCore); // - DOESN'T WORK!
        new TestServCorePortals(testServCore);
        new TestServCoreDragon(testServCore);
        new TestServCoreSigns(testServCore);
        new TestServCoreMaps(testServCore);
        new TestServCoreJoin(testServCore);
        new TestServCoreFire(testServCore);
        new TestServCoreQuit(testServCore);
        new TestServCorePing(testServCore);
        new TestServCoreSwap(testServCore);
        new TestServCoreChat(testServCore);
        new TestServCorePVP(testServCore);
    }
}
