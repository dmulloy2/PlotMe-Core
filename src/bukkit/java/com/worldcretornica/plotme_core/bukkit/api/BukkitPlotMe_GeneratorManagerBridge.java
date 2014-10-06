package com.worldcretornica.plotme_core.bukkit.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import com.worldcretornica.plotme_core.api.IBiome;
import com.worldcretornica.plotme_core.api.ICommandSender;
import com.worldcretornica.plotme_core.api.ILocation;
import com.worldcretornica.plotme_core.api.IPlayer;
import com.worldcretornica.plotme_core.api.IPlotMe_GeneratorManager;
import com.worldcretornica.plotme_core.api.IWorld;

public class BukkitPlotMe_GeneratorManagerBridge implements IPlotMe_GeneratorManager {

    private IBukkitPlotMe_GeneratorManager gm;
    
    public BukkitPlotMe_GeneratorManagerBridge(IBukkitPlotMe_GeneratorManager gm) {
        this.gm = gm;
    }
    
    @Override
    public String getPlotId(ILocation loc) {
        return gm.getPlotId(((BukkitLocation) loc).getLocation());
    }

    @Override
    public String getPlotId(IPlayer player) {
        return gm.getPlotId(((BukkitPlayer) player).getPlayer());
    }

    @Override
    public List<IPlayer> getPlayersInPlot(IWorld w, String id) {
        List<IPlayer> players = new ArrayList<>();
        BukkitWorld bw = (BukkitWorld) w;
        
        for(Player p : gm.getPlayersInPlot(bw.getWorld(), id)) {
            players.add(new BukkitPlayer(p));
        }
        
        return players;
    }

    @Override
    public void fillroad(String id1, String id2, IWorld w) {
        gm.fillroad(id1, id2, ((BukkitWorld) w).getWorld());
    }

    @Override
    public void fillmiddleroad(String id1, String id2, IWorld w) {
        gm.fillmiddleroad(id1, id2, ((BukkitWorld) w).getWorld());
    }

    @Override
    public void setOwnerDisplay(IWorld world, String id, String Line1, String Line2, String Line3, String Line4) {
        gm.setOwnerDisplay(((BukkitWorld) world).getWorld(), id, Line1, Line2, Line3, Line4);
    }

    @Override
    public void setSellerDisplay(IWorld world, String id, String Line1, String Line2, String Line3, String Line4) {
        gm.setSellerDisplay(((BukkitWorld) world).getWorld(), id, Line1, Line2, Line3, Line4);
    }

    @Override
    public void setAuctionDisplay(IWorld world, String id, String Line1, String Line2, String Line3, String Line4) {
        gm.setAuctionDisplay(((BukkitWorld) world).getWorld(), id, Line1, Line2, Line3, Line4);
    }

    @Override
    public void removeOwnerDisplay(IWorld world, String id) {
        gm.removeOwnerDisplay(((BukkitWorld) world).getWorld(), id);
    }

    @Override
    public void removeSellerDisplay(IWorld world, String id) {
        gm.removeSellerDisplay(((BukkitWorld) world).getWorld(), id);
    }

    @Override
    public void removeAuctionDisplay(IWorld world, String id) {
        gm.removeAuctionDisplay(((BukkitWorld) world).getWorld(), id);
    }

    @Override
    public int getIdX(String id) {
        return gm.getIdX(id);
    }

    @Override
    public int getIdZ(String id) {
        return gm.getIdZ(id);
    }

    @Override
    public ILocation getPlotBottomLoc(IWorld world, String id) {
        return new BukkitLocation(gm.getPlotBottomLoc(((BukkitWorld) world).getWorld(), id));
    }

    @Override
    public ILocation getPlotTopLoc(IWorld world, String id) {
        return new BukkitLocation(gm.getPlotTopLoc(((BukkitWorld) world).getWorld(), id));
    }

    @Override
    public void setBiome(IWorld w, String id, IBiome biome) {
        gm.setBiome(((BukkitWorld) w).getWorld(), id, ((BukkitBiome) biome).getBiome());
    }

    @Override
    public void refreshPlotChunks(IWorld world, String id) {
        gm.refreshPlotChunks(((BukkitWorld) world).getWorld(), id);
    }

    @Override
    public ILocation getTop(IWorld w, String id) {
        return new BukkitLocation(gm.getTop(((BukkitWorld) w).getWorld(), id));
    }

    @Override
    public ILocation getBottom(IWorld w, String id) {
        return new BukkitLocation(gm.getBottom(((BukkitWorld) w).getWorld(), id));
    }

    @Override
    public void clear(IWorld world, String id) {
        gm.clear(((BukkitWorld) world).getWorld(), id);
    }

    @Override
    public void clear(ILocation bottom, ILocation top) {
        gm.clear(((BukkitLocation) bottom).getLocation(), ((BukkitLocation) top).getLocation());
    }

    @Override
    public Long[] clear(ILocation bottom, ILocation top, long maxBlocks, boolean clearEntities, Long[] start) {
        return gm.clear(((BukkitLocation) bottom).getLocation(), ((BukkitLocation) top).getLocation(), maxBlocks, clearEntities, start);
    }

    @Override
    public Long[] clear(IWorld world, String id, long maxBlocks, boolean clearEntities, Long[] start) {
        return gm.clear(((BukkitWorld) world).getWorld(), id, maxBlocks, clearEntities, start);
    }

    @Override
    public void adjustPlotFor(IWorld world, String id, boolean Claimed, boolean Protect, boolean Auctionned, boolean ForSale) {
        gm.adjustPlotFor(((BukkitWorld) world).getWorld(), id, Claimed, Protect, Auctionned, ForSale);
    }

    @Override
    public boolean isBlockInPlot(String id, ILocation blocklocation) {
        return gm.isBlockInPlot(id, ((BukkitLocation) blocklocation).getLocation());
    }

    @Override
    public boolean movePlot(IWorld wFrom, IWorld wTo, String idFrom, String idTo) {
        return gm.movePlot(((BukkitWorld) wFrom).getWorld(), ((BukkitWorld) wTo).getWorld(), idFrom, idTo);
    }

    @Override
    public int bottomX(String id, IWorld world) {
        return gm.bottomX(id, ((BukkitWorld) world).getWorld());
    }

    @Override
    public int bottomZ(String id, IWorld world) {
        return gm.bottomZ(id, ((BukkitWorld) world).getWorld());
    }

    @Override
    public int topX(String id, IWorld world) {
        return gm.topX(id, ((BukkitWorld) world).getWorld());
    }

    @Override
    public int topZ(String id, IWorld world) {
        return gm.topZ(id, ((BukkitWorld) world).getWorld());
    }

    @Override
    public void regen(IWorld world, String id, ICommandSender sender) {
        gm.regen(((BukkitWorld) world).getWorld(), id, ((BukkitCommandSender) sender).getCommandSender());
    }

    @Override
    public ILocation getPlotHome(IWorld world, String id) {
        return new BukkitLocation(gm.getPlotHome(((BukkitWorld) world).getWorld(), id));
    }

    @Override
    public boolean isValidId(String id) {
        return gm.isValidId(id);
    }

    @Override
    public boolean createConfig(String worldname, Map<String, String> args, ICommandSender sender) {
        return gm.createConfig(worldname, args, ((BukkitCommandSender) sender).getCommandSender());
    }

    @Override
    public Map<String, String> getDefaultGenerationConfig() {
        return gm.getDefaultGenerationConfig();
    }

    @Override
    public int getPlotSize(String worldname) {
        return gm.getPlotSize(worldname);
    }

    @Override
    public int getRoadHeight(String worldname) {
        return gm.getRoadHeight(worldname);
    }

}
