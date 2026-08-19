package com.example.tactical;
import net.fabricmc.api.ModInitializer;
public class TacticalMod implements ModInitializer {
    public static final String MOD_ID = "tactical";
    @Override
    public void onInitialize() {
        ModItems.register();
        TitanDisableLegsAndFeet.register();
        System.out.println("Tactical Mod loaded – S70 helmet + Titan chestplate");
    }
}
