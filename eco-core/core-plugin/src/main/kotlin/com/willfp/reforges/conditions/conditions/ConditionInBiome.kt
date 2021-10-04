package com.willfp.reforges.conditions.conditions

import com.willfp.eco.core.config.interfaces.JSONConfig
import com.willfp.reforges.conditions.Condition
import com.willfp.reforges.reforges.util.ReforgeLookup
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent

class ConditionInBiome: Condition("in_biome") {
    @EventHandler(
        priority = EventPriority.MONITOR,
        ignoreCancelled = true
    )
    fun handle(event: PlayerMoveEvent) {
        val player = event.player

        if (event.from.world?.getBiome(event.from) == event.to.world?.getBiome(event.to)) {
            return
        }

        ReforgeLookup.updateReforges(player)
    }

    override fun isConditionMet(player: Player, config: JSONConfig): Boolean {
        return config.getStrings("biomes").contains(player.world.getBiome(
            player.location.blockX,
            player.location.blockY,
            player.location.blockZ
        ).name.lowercase())
    }
}