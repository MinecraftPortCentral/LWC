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

import org.getlwc.Block;
import org.getlwc.EventHelper;
import org.getlwc.World;
import org.getlwc.entity.Entity;
import org.getlwc.entity.Player;
import org.getlwc.sponge.SpongePlugin;
import org.getlwc.sponge.entity.SpongeEntity;
import org.getlwc.sponge.world.SpongeBlock;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.entity.EntityInteractBlockEvent;
import org.spongepowered.api.event.entity.player.PlayerInteractBlockEvent;
import org.spongepowered.api.event.entity.player.PlayerInteractEntityEvent;
import org.spongepowered.api.event.entity.player.PlayerJoinEvent;
import org.spongepowered.api.event.entity.player.PlayerQuitEvent;

public class SpongeEventListener {

    private SpongePlugin plugin;

    public SpongeEventListener(SpongePlugin plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onPlayerJoin(PlayerJoinEvent event) {
        EventHelper.onPlayerJoin(plugin.wrapPlayer(event.getEntity()));
    }

    @Subscribe
    public void onPlayerQuit(PlayerQuitEvent event) {
        EventHelper.onPlayerQuit(plugin.wrapPlayer(event.getEntity()));
    }

    @Subscribe(order = Order.FIRST, ignoreCancelled = true)
    public void onPlayerInteractBlock(PlayerInteractBlockEvent event) {
        Player player = plugin.wrapPlayer(event.getEntity());
        Block block = plugin.wrapBlock(event.getLocation());

        if (EventHelper.onBlockInteract(player, block)) {
        	event.setCancelled(true);
        }
    }

    @Subscribe(order = Order.FIRST, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = plugin.wrapPlayer(event.getEntity());
        Entity target = plugin.wrapEntity(event.getTargetEntity());

        if (EventHelper.onEntityInteract(player, target)) {
            event.setCancelled(true);
        }
    }

    @Subscribe(order = Order.FIRST, ignoreCancelled = true)
    public void onEntityInteractBlock(EntityInteractBlockEvent event) {
        Entity entity = new SpongeEntity(event.getEntity());
        World world = plugin.getWorld(event.getEntity().getWorld().getName());
        Block block = new SpongeBlock(world, event.getLocation());

        if (EventHelper.onBlockInteract(entity, block)) {
            event.setCancelled(true);
        }
    }

}
