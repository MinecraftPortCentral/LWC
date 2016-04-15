/**
 * Copyright (c) 2011-2014 Tyler Blair
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */
package org.getlwc.sponge.listeners;

import java.util.Optional;

import org.getlwc.Block;
import org.getlwc.EventHelper;
import org.getlwc.entity.Entity;
import org.getlwc.entity.Player;
import org.getlwc.sponge.SpongePlugin;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.IsCancelled;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.util.Tristate;

public class SpongeEventListener {

    private SpongePlugin plugin;

    public SpongeEventListener(SpongePlugin plugin) {
        this.plugin = plugin;
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event) {
        EventHelper.onPlayerJoin(plugin.wrapPlayer(event.getTargetEntity()));
    }

    @Listener
    public void onPlayerQuit(ClientConnectionEvent.Disconnect event) {
        EventHelper.onPlayerQuit(plugin.wrapPlayer(event.getTargetEntity()));
    }

    @IsCancelled(Tristate.UNDEFINED)
    @Listener(order = Order.FIRST)
    public void onPlayerInteractBlock(InteractBlockEvent event) {
        Optional<org.spongepowered.api.entity.living.player.Player> playerOpt = event.getCause().first(org.spongepowered.api.entity.living.player.Player.class);
        if (!playerOpt.isPresent()) {
            return;
        }

        Player player = plugin.wrapPlayer(playerOpt.get());
        Block block = plugin.wrapBlock(event.getTargetBlock().getLocation().get());

        if (EventHelper.onBlockInteract(player, block)) {
            event.setCancelled(true);
        }
    }

    @IsCancelled(Tristate.UNDEFINED)
    @Listener(order = Order.FIRST)
    public void onPlayerInteractEntity(InteractEntityEvent event) {
        Player player = plugin.wrapPlayer((org.spongepowered.api.entity.living.player.Player) event.getTargetEntity());
        Entity target = plugin.wrapEntity(event.getTargetEntity());

        if (EventHelper.onEntityInteract(player, target)) {
            event.setCancelled(true);
        }
    }

    @IsCancelled(Tristate.UNDEFINED)
    @Listener(order = Order.FIRST)
    public void onEntityInteractBlock(InteractBlockEvent event) {
        Optional<org.spongepowered.api.entity.Entity> entityOpt = event.getCause().first(org.spongepowered.api.entity.Entity.class);
        if (!entityOpt.isPresent()) {
            return;
        }

        Entity entity = plugin.wrapEntity(entityOpt.get());
        Block block = plugin.wrapBlock(event.getTargetBlock().getLocation().get());

        if (EventHelper.onBlockInteract(entity, block)) {
            event.setCancelled(true);
        }
    }

}
