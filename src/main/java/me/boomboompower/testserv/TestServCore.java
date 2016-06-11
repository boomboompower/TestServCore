package me.boomboompower.testserv;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*
* THIS CODE IS OUTDATED!
*/

import me.boomboompower.testserv.commands.TestServCoreCommands;
import me.boomboompower.testserv.interactor.InteractorListeners;
import me.boomboompower.testserv.listeners.TestServCoreListeners;
import me.boomboompower.testserv.throwingtnt.ThrowingTNTRegister;
import me.boomboompower.testserv.utils.Register;

import org.bukkit.plugin.java.JavaPlugin;

public class TestServCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register this util
        new Register(this);

        // Register all listeners
        new TestServCoreListeners(this);

        // Register commands
        new TestServCoreCommands(this);

        /** Register Interactor events */
        new InteractorListeners(this);

        /** Register ThrowingTNT events */
        new ThrowingTNTRegister(this);
    }
}
