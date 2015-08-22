package com.worldcretornica.plotme_core.bukkit.listener;

import com.worldcretornica.plotme_core.PermissionNames;
import com.worldcretornica.plotme_core.Plot;
import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.api.IPlayer;
import com.worldcretornica.plotme_core.api.Location;
import com.worldcretornica.plotme_core.bukkit.BukkitUtil;
import com.worldcretornica.plotme_core.bukkit.PlotMe_CorePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class BukkitPlotDenyListener implements Listener {

    private final PlotMe_CorePlugin plugin;
    private final PlotMeCoreManager manager;

    public BukkitPlotDenyListener(PlotMe_CorePlugin plotMeCorePlugin) {
        plugin = plotMeCorePlugin;
        manager = PlotMeCoreManager.getInstance();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMoveEvent event) {
        Location location = BukkitUtil.adapt(event.getTo());
        if (manager.isPlotWorld(location)) {
            if (event.getPlayer().hasPermission(PermissionNames.ADMIN_BYPASSDENY)) {
                return;
            }
            Plot plot = manager.getPlot(location);
            if (plot != null) {
                if (plot.getOwnerId().equals(event.getPlayer().getUniqueId())) {
                    return;
                }
                if (plot.isDenied(event.getPlayer().getUniqueId())) {
                    event.setTo(event.getFrom());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        IPlayer player = plugin.wrapPlayer(event.getPlayer());

        if (manager.isPlotWorld(player) && !player.hasPermission(PermissionNames.ADMIN_BYPASSDENY)) {
            Plot plot = manager.getPlot(player);
            if (plot != null) {
                if (plot.getOwnerId().equals(event.getPlayer().getUniqueId())) {
                    return;
                }
                if (plot.isDenied(player.getUniqueId())) {
                    player.setLocation(manager.getPlotHome(plot.getId(), player.getWorld()));
                }
            }
        }
    }
}