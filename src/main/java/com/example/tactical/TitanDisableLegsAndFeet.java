package com.example.tactical;
import net.fabricmc.fabric.api.event.player.PlayerTickCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
public class TitanDisableLegsAndFeet {
    private static final int LEGS_SLOT = 1;   // 护腿槽位
    private static final int FEET_SLOT = 0;   // 靴子槽位
    private static final int CHEST_SLOT = 2;  // 胸甲槽位
    public static void register() {
        PlayerTickCallback.START.register((player) -> {
            if (player.getWorld().isClient) return;
            if (!(player instanceof ServerPlayerEntity serverPlayer)) return;
            ItemStack chest = serverPlayer.getInventory().armor.get(CHEST_SLOT);
            boolean wearingTitan = chest.getItem() == ModItems.TITAN_CHESTPLATE;
            if (wearingTitan) {
                clearSlotIfNeeded(serverPlayer.getInventory().armor, LEGS_SLOT, serverPlayer);
                clearSlotIfNeeded(serverPlayer.getInventory().armor, FEET_SLOT, serverPlayer);
            }
        });
    }
    private static void clearSlotIfNeeded(DefaultedList<ItemStack> armor, int slot, ServerPlayerEntity player) {
        ItemStack stack = armor.get(slot);
        if (!stack.isEmpty()) {
            boolean added = player.getInventory().insertStack(stack);
            if (!added) player.dropItem(stack, false);
            armor.set(slot, ItemStack.EMPTY);
        }
    }
}
