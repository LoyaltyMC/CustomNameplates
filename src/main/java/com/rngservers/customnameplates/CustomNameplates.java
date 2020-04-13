package com.rngservers.customnameplates;

import com.rngservers.customnameplates.nameplate.NameplateManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomNameplates extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        NameplateManager nameplateManager = new NameplateManager(this);
        nameplateManager.updateNameplateLoop();
    }
}
