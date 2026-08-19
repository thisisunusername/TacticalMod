package com.example.tactical;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import java.util.EnumMap;
import java.util.List;
public class ModArmorMaterials {
    public static final ArmorMaterial S70 = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.BOOTS, 2);
            }),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(Identifier.of("tactical", "s70"))),
            0.5f, 0.0f
    );
    public static final ArmorMaterial TITAN = new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 9);
                map.put(ArmorItem.Type.LEGGINGS, 7);
                map.put(ArmorItem.Type.BOOTS, 4);
            }),
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.EMPTY,
            List.of(new ArmorMaterial.Layer(Identifier.of("tactical", "titan"))),
            2.0f, 0.1f
    );
}
