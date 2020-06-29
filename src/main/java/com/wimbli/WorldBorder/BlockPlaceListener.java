package com.wimbli.WorldBorder;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;


public class BlockPlaceListener implements Listener 
{
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Location loc = event.getBlockPlaced().getLocation();

		World world = loc.getWorld();
		if (world == null) return;
		BorderData border = Config.Border(world.getName());
		if (border == null) return;

		Player p = event.getPlayer();
		if (p != null) {
			if (p.hasPermission("worldborder.bypassplacement")) {
				return;
			}
		}

		if (!border.insideBorder(loc.getX(), loc.getZ(), Config.ShapeRound())) 
		{
			event.setCancelled(true);
		}
	}

	public void unregister() 
	{
		HandlerList.unregisterAll(this);
	}
}
