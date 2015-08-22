package com.worldcretornica.plotme_core.bukkit.api;

import com.worldcretornica.plotme_core.PlotMe_Core;
import com.worldcretornica.plotme_core.TeleportRunnable;
import com.worldcretornica.plotme_core.api.IEntity;
import com.worldcretornica.plotme_core.api.IWorld;
import com.worldcretornica.plotme_core.api.Location;
import com.worldcretornica.plotme_core.api.Vector;
import com.worldcretornica.plotme_core.bukkit.BukkitUtil;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;

import java.util.List;
import java.util.UUID;

public class BukkitEntity implements IEntity {

    public final Entity entity;
    private final Vector coords;

    public BukkitEntity(Entity entity) {
        this.entity = entity;
        coords = BukkitUtil.locationToVector(entity.getLocation());
    }

    @Override
    public Location getLocation() {
        return new Location(getWorld(), getPosition());
    }

    @Override
    public void setLocation(Location location) {
        entity.teleport(BukkitUtil.adapt(location));
    }

    @Override public void teleport(Location location, PlotMe_Core plugin) {
        plugin.getServerBridge().runTaskLater(new TeleportRunnable(this, location), plugin.getConfig().getInt("tp-delay"));
    }

    /**
     * Get the world the entity is currently in.
     *
     * @return the world the entity is in
     */
    @Override
    public IWorld getWorld() {
        return new BukkitWorld(entity.getWorld());
    }

    @Override
    public void remove() {
        entity.remove();
    }

    /**
     * Gets the UUID of the actor
     *
     * @return UUID of the actor
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public String getName() {
        return entity.getName();
    }

    public Vector getPosition() {
        return coords;
    }

    public void setMetadata(String string, MetadataValue value) {
        entity.setMetadata(string, value);
    }

    public List<MetadataValue> getMetadata(String string) {
        return entity.getMetadata(string);
    }
}
