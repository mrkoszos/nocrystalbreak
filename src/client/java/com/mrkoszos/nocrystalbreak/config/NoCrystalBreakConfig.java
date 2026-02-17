package com.mrkoszos.nocrystalbreak.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "assets/nocrystalbreak")
public class NoCrystalBreakConfig implements ConfigData {

    @ConfigEntry.Gui.Tooltip
    public boolean enabled = true; // true = mod active, false = mod deactivated

}
