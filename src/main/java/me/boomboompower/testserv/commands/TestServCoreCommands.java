package me.boomboompower.testserv.commands;

/*
* Made for TestServCore
* by boomboompower 27/04/2016
*
* THIS CODE IS OUTDATED
*/

import me.boomboompower.testserv.TestServCore;

public class TestServCoreCommands {

    private TestServCore testServCore;

    public TestServCoreCommands(TestServCore testServCore) {
        this.testServCore = testServCore;

        new TestServCoreRemove(testServCore);
        new TestServCoreSpawn(testServCore);
        new TestServCoreAlert(testServCore);
        new TestServCoreSudo(testServCore);
        new TestServCoreKick(testServCore);
        new TestServCoreHelp(testServCore);
        new TestServCoreBan(testServCore);
        new TestServCoreOp(testServCore);
    }
}
