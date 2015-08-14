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
package org.getlwc.bukkit.entity;

import org.getlwc.Engine;
import org.getlwc.ItemStack;
import org.getlwc.Location;
import org.getlwc.bukkit.BukkitPlugin;
import org.getlwc.entity.EntityType;
import org.getlwc.entity.SimplePlayer;
import org.getlwc.lang.Locale;
import org.getlwc.util.Color;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class BukkitPlayer extends SimplePlayer {

    private Engine engine;
    private final BukkitPlugin plugin;

    /**
     * native Bukkit handle
     */
    private final org.bukkit.entity.Player handle;

    public BukkitPlayer(Engine engine, BukkitPlugin plugin, org.bukkit.entity.Player handle) {
        if (handle == null) {
            throw new IllegalArgumentException("Player handle cannot be null");
        }

        this.engine = engine;
        this.plugin = plugin;
        this.handle = handle;
        loadLocale();
    }

    /**
     * Load the player's locale (unsafe)
     */
    private void loadLocale() {
        try {
            Method getHandle = handle.getClass().getDeclaredMethod("getHandle");
            Object entityPlayer = getHandle.invoke(handle);
            Field name = entityPlayer.getClass().getDeclaredField("locale");
            name.setAccessible(true);
            String localeName = (String) name.get(entityPlayer);

            if (localeName != null) {
                setLocale(new Locale(localeName));
                engine.getConsoleSender().sendMessage("Player " + getName() + " loaded using locale: " + getLocale());
            }
        } catch (Exception e) {
            engine.getConsoleSender().sendMessage("Unable to get locale from Player (class: " + handle.getClass().getCanonicalName() + ")");
        }
    }

    @Override
    public UUID getUUID() {
        return handle.getUniqueId();
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public Location getLocation() {
        org.bukkit.Location lhandle = handle.getLocation();
        return new Location(plugin.getWorld(lhandle.getWorld().getName()), lhandle.getX(), lhandle.getY(), lhandle.getZ());
    }

    @Override
    public void sendMessage(String message) {
        for (String line : message.split("\n")) {
            handle.sendMessage(Color.replaceColors(line));
        }
    }

    @Override
    public ItemStack getItemInHand() {
        return plugin.castItemStack(handle.getItemInHand());
    }

    @Override
    public EntityType getType() {
        return null;
    }
}
