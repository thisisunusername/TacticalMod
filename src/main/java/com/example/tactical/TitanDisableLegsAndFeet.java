package com.example.tactical;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 穿上「泰坦胸甲」时，强制清空护腿与靴子槽位。
 *
 * 实现说明：
 * - 用 ServerTickEvents.END_SERVER_TICK 遍历在线玩家（每秒 20 次），
 *   这是 Fabric API 里稳定存在的钩子（旧代码里的 PlayerTickCallback 在 API 中并不存在）。
 * - 装备槽统一走 EquipmentSlot，避免直接访问 PlayerInventory.armor 字段
 *   （该字段在不同映射版本里可见性会变）。
 */
public final class TitanDisableLegsAndFeet {

    /** 胸甲槽（1.21.x 里 PlayerInventory.armor 的索引 2 = 胸甲） */
    private static final int CHEST_INDEX = 2;
    /** 护腿槽索引 */
    private static final int LEGS_INDEX = 1;
    /** 靴子槽索引 */
    private static final int FEET_INDEX = 0;

    private TitanDisableLegsAndFeet() {
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // 遍历所有维度的玩家
            List<ServerPlayerEntity> players = new ArrayList<>();
            for (net.minecraft.server.world.ServerWorld world : server.getWorlds()) {
                players.addAll(world.getPlayers());
            }
            for (ServerPlayerEntity player : players) {
                enforceTitanRule(player);
            }
        });
    }

    private static void enforceTitanRule(ServerPlayerEntity player) {
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        if (!chest.isOf(ModItems.TITAN_CHESTPLATE)) {
            return;
        }

        // 胸甲是泰坦 → 清掉护腿和靴子
        clearSlot(player, EquipmentSlot.LEGS);
        clearSlot(player, EquipmentSlot.FEET);
    }

    /**
     * 清空某个装备槽。物品不会消失，会优先塞回背包，背包满了就掉在脚下。
     */
    private static void clearSlot(ServerPlayerEntity player, EquipmentSlot slot) {
        ItemStack stack = player.getEquippedStack(slot);
        if (stack.isEmpty()) {
            return;
        }

        // 摘下来
        player.equipStack(slot, ItemStack.EMPTY);

        // 塞背包，塞不下就掉出来
        if (!player.getInventory().insertStack(stack)) {
            player.dropItem(stack, false);
        }
    }
}