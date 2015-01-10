package com.worldcretornica.plotme_core.api.event;

import com.worldcretornica.plotme_core.Plot;
import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.PlotMe_Core;
import com.worldcretornica.plotme_core.api.ILocation;
import com.worldcretornica.plotme_core.api.IPlayer;
import com.worldcretornica.plotme_core.api.World;

public class InternalPlotMoveEvent extends InternalPlotEvent implements ICancellable {

    private final String fromId;
    private final String toId;
    private final World world;
    private final IPlayer mover;
    private boolean canceled;

    public InternalPlotMoveEvent(PlotMe_Core instance, World world, String fromId, String toId, IPlayer mover) {
        super(instance, null, world);
        this.fromId = fromId;
        this.toId = toId;
        this.world = world;
        this.mover = mover;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean cancel) {
        canceled = cancel;
    }

    @Override
    public Plot getPlot() {
        return plugin.getPlotMeCoreManager().getPlotById(fromId, world);
    }

    public Plot getPlotTo() {
        return plugin.getPlotMeCoreManager().getPlotById(toId, world);
    }

    public IPlayer getPlayer() {
        return mover;
    }

    public String getId() {
        return fromId;
    }

    public String getIdTo() {
        return toId;
    }

    @Override
    public ILocation getUpperBound() {
        return PlotMeCoreManager.getPlotTopLoc(world, fromId);
    }

    @Override
    public ILocation getLowerBound() {
        return PlotMeCoreManager.getPlotBottomLoc(world, fromId);
    }

    public ILocation getUpperBoundTo() {
        return PlotMeCoreManager.getPlotTopLoc(world, toId);
    }

    public ILocation getLowerBoundTo() {
        return PlotMeCoreManager.getPlotBottomLoc(world, toId);
    }

    public String getOwnerTo() {
        Plot plot = getPlotTo();
        if (plot != null) {
            return plot.getOwner();
        } else {
            return "";
        }
    }
}