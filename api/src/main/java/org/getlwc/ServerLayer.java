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
package org.getlwc;

import org.getlwc.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Server mod specific methods
 */
public abstract class ServerLayer {

    /**
     * A map of all of the currently loaded players
     */
    protected final Map<String, Player> players = new HashMap<>();

    /**
     * A map of all of the currently known worlds
     */
    protected final Map<String, World> worlds = new HashMap<>();

    /**
     * A map of all of the currently known worlds
     */
    protected final Map<UUID, World> worldsByUniqueId = new HashMap<>();

    /**
     * Returns whether or not the server layer supports UUID's
     * 
     * @return true if UUID is supported, false if not
     */
    public abstract boolean isUUIDSupported();

    /**
     * Get the title of the implementation software that runs the server
     *
     * @return
     */
    public abstract String getImplementationTitle();

    /**
     * Get the version of the server that is running
     *
     * @return
     */
    public abstract String getImplementationVersion();

    /**
     * Get the data folder that can be used for the engine.
     * For most servers, this is typically plugins/LWC/ or the equivalent.
     *
     * @return
     */
    public abstract File getDataFolder();

    /**
     * Get the default world
     *
     * @return
     */
    public abstract World getDefaultWorld();

    /**
     * Load a player directly from the server without using any caches
     *
     * @param playerName
     */
    protected abstract Player internalGetPlayer(String playerName);

    /**
     * Load a world directly from the server without using any caches
     *
     * @param worldName
     */
    protected abstract World internalGetWorld(String worldName);

    /**
     * Load a world directly from the server without using any caches
     *
     * @param uuid
     */
    protected abstract World internalGetWorld(UUID uuid);

    /**
     * Resolves an offline player for the ident; either a player name or their UUID.
     * TODO:- Player info/Offline player obj?
     *
     * @param ident
     * @return
     */
    public abstract UUID getOfflinePlayer(String ident);

    /**
     * Gets the data path to the given subdirectory in the data folder
     *
     * @param dir
     * @return
     */
    public String getDataPathTo(String dir) {
        String path = new File(getDataFolder(), dir).getPath() + File.separator;
        path = path.replaceAll("\\\\", "/"); // normalize

        return path;
    }

    /**
     * Get a player from the server
     *
     * @param playerName
     * @return
     */
    public Player getPlayer(String playerName) {
        if (players.containsKey(playerName)) {
            return players.get(playerName);
        }

        Player player = internalGetPlayer(playerName);

        if (player != null) {
            players.put(playerName, player);
        }

        return player;
    }

    /**
     * Get a world from the server
     *
     * @param worldName
     * @return
     */
    public World getWorld(String worldName) {
        if (worlds.containsKey(worldName)) {
            return worlds.get(worldName);
        }

        World world = internalGetWorld(worldName);

        if (world != null) {
            worlds.put(worldName, world);
        }

        return world;
    }

    /**
     * Get a world from the server by UUID
     *
     * @param uuid
     * @return
     */
    public World getWorld(UUID uuid) {
        if (worldsByUniqueId.containsKey(uuid)) {
            return worldsByUniqueId.get(uuid);
        }

        World world = internalGetWorld(uuid);

        if (world != null) {
        	worldsByUniqueId.put(uuid, world);
        }

        return world;
    }

    /**
     * Remove a player from the cache
     *
     * @param playerName
     */
    public void removePlayer(String playerName) {
        players.remove(playerName);
    }

    /**
     * Remove a world from the cache
     *
     * @param worldName
     */
    public void removeWorld(String worldName) {
        worlds.remove(worldName);
    }

}
