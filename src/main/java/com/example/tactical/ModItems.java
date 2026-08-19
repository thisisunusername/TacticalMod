package com.example.tactical;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
public class ModItems {
    public static final ArmorMaterial S70_MATERIAL = ModArmorMaterials.S70;
    public static final ArmorMaterial TITAN_MATERIAL = ModArmorMaterials.TITAN;
    public static final Item S70_HELMET = new ArmorItem(S70_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1));
    public static final Item TITAN_CHESTPLATE = new ArmorItem(TITAN_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1));
    public static void register() {
        Registry.register(Registries.ITEM, Identifier.of(TacticalMod.MOD_ID, "s70_elite_tactical_helmet"), S70_HELMET);
        Registry.register(Registries.ITEM, Identifier.of(TacticalMod.MOD_ID, "titan_chestplate"), TITAN_CHESTPLATE);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(S70_HELMET);
            entries.add(TITAN_CHESTPLATE);
        });
    }
}
