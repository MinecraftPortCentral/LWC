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
package org.getlwc.sponge.world;

import org.getlwc.Block;
import org.getlwc.BlockType;
import org.getlwc.SimpleEngine;
import org.getlwc.World;
import org.getlwc.sponge.SpongePlugin;
import org.spongepowered.api.world.Location;

public class SpongeBlock extends Block {

    private World world;
    private Location handle;

    public SpongeBlock(World world, Location handle) {
        this.world = world;
        this.handle = handle;
    }

    @Override
    public BlockType getType() {
        return SimpleEngine.getInstance().getMinecraftRegistry().getBlockType(handle.getBlockType().getId());
    }

    @Override
    public byte getData() {
    	return 0;
        //return handle.getBlock().getDataValue();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public int getX() {
        return handle.getBlockX();
    }

    @Override
    public int getY() {
        return handle.getBlockY();
    }

    @Override
    public int getZ() {
        return handle.getBlockZ();
    }

    @Override
    public void setType(BlockType type) {
       // org.spongepowered.api.block.BlockType dest = SpongePlugin.instance.getGame().getRegistry().getBlock(type.getId()).orNull();

        /*if (dest != null) {
            handle.setBlockType(dest);
        }*/
    }

    @Override
    public void setData(byte data) {
        throw new UnsupportedOperationException("setData() is not yet supported");
    }

    @Override
    public boolean hasTileEntity() {
        // TODO Sponge will hopefully expose this in some way
        // implemented explicitly so I remember :-)
        return super.hasTileEntity();
    }

}
