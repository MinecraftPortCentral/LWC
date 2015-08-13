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
package org.getlwc.sponge;

import org.getlwc.ServerLayer;
import org.getlwc.World;
import org.getlwc.entity.Player;
import org.getlwc.sponge.entity.SpongePlayer;
import org.getlwc.sponge.world.SpongeExtent;
import org.spongepowered.api.Game;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.UUID;

@Singleton
public class SpongeServerLayer extends ServerLayer {

    private SpongePlugin plugin;
    private Game game;

    @Inject
    public SpongeServerLayer(SpongePlugin plugin, Game game) {
        this.plugin = plugin;
        this.game = game;
    }

    @Override
    public File getDataFolder() {
        return plugin.getConfigDir();
    }

    @Override
    public String getImplementationTitle() {
        return "Sponge";
    }

    @Override
    public String getImplementationVersion() {
        return game.getPlatform().getApiVersion();
    }

    @Override
    public World getDefaultWorld() {
        return getWorld(game.getServer().getWorlds().iterator().next().getUniqueId());
    }

    @Override
    protected Player internalGetPlayer(String playerName) {
        org.spongepowered.api.entity.player.Player player = game.getServer().getPlayer(playerName).orNull();

        if (player != null) {
            return new SpongePlayer(player);
        } else {
            return null;
        }
    }

    @Override
    protected World internalGetWorld(String worldName) {
        org.spongepowered.api.world.World world = game.getServer().getWorld(worldName).orNull();

        if (world != null) {
            return new SpongeExtent(world);
        } else {
            return null;
        }
    }

    @Override
    protected World internalGetWorld(UUID uuid) {
        org.spongepowered.api.world.World world = game.getServer().getWorld(uuid).orNull();

        if (world != null) {
            return new SpongeExtent(world);
        } else {
            return null;
        }
    }

    @Override
    public UUID getOfflinePlayer(String ident) {
        // TODO
        return null;
    }

    @Override
    public boolean isUUIDSupported() {
        return true;
    }

}
