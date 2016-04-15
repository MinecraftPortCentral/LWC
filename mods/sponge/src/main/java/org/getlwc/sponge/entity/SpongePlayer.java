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
package org.getlwc.sponge.entity;

import com.flowpowered.math.vector.Vector3d;

import org.getlwc.ItemStack;
import org.getlwc.Location;
import org.getlwc.entity.SimplePlayer;
import org.getlwc.sponge.world.SpongeExtent;
import org.getlwc.util.Color;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class SpongePlayer extends SimplePlayer {

    /**
     * native Sponge handle
     */
    private org.spongepowered.api.entity.living.player.Player handle;

    public SpongePlayer(org.spongepowered.api.entity.living.player.Player handle) {
        this.handle = handle;
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
        // todo remove unnecessary object creation
        Vector3d locHandle = handle.getLocation().getPosition();
        return new Location(new SpongeExtent(handle.getWorld()), locHandle.getX(), locHandle.getY(), locHandle.getZ());
    }

    @Override
    public ItemStack getItemInHand() {
        // TODO
        throw new UnsupportedOperationException("getItemInHand() is not yet supported");
    }

    @Override
    public void sendMessage(String message) {
        for (String line : message.split("\n")) {
            handle.sendMessage(Text.of(Color.replaceColors(line)));
        }
    }

    @Override
    public boolean hasPermission(String node) {
        return handle.hasPermission(node);
    }

}
