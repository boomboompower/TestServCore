package me.boomboompower.testserv;

/*
* Made for TestServ Core
* by boomboompower 25/04/2016
*/

import me.boomboompower.testserv.commands.TestServCoreCommands;
import me.boomboompower.testserv.interactor.InteractorListeners;
import me.boomboompower.testserv.listeners.TestServCoreListeners;

import org.bukkit.plugin.java.JavaPlugin;

public class TestServCore extends JavaPlugin {

    @Override
    public void onEnable() {

        // Register all listeners
        new TestServCoreListeners(this);

        // Register commands
        new TestServCoreCommands(this);

        /** Register Interactor events */
        new InteractorListeners(this);
    }
}
