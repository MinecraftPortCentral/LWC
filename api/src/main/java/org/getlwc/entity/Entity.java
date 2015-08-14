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
package org.getlwc.entity;

import org.getlwc.Location;
import org.getlwc.World;
import org.getlwc.component.BasicComponentHolder;
import org.getlwc.component.Component;

import java.util.UUID;

public abstract class Entity extends BasicComponentHolder<Component> {

    /**
     * Gets the entity's type
     *
     * @return Entity type.
     */
    public abstract EntityType getType();

    /**
     * Get the UUID of this entity
     *
     * @return uuid
     */ 
    public abstract UUID getUUID();

    public abstract Location getLocation();

    @Override
    public String toString() {
        return String.format("Entity(name=%s loc=[%d %d %d \"%s\"])", getType() == null ? "unknown_type" : getType().getId(), getLocation().getBlockX(), getLocation().getBlockY(), getLocation().getBlockZ(), getLocation().getWorld() == null ? "unknown_world" : getLocation().getWorld().getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Entity)) return false;
        Entity other = (Entity) o;

        return getUUID().equals(other.getUUID());
    }

    @Override
    public int hashCode() {
    	return getUUID().hashCode();
    }

    /**
     * Get the entity's name.
     *
     * @return the entity's name. If the entity is unknown, "unknown" is returned
     */
    public String getName() {
        return getType() == null ? "unknown" : getType().getName();
    }

	/**
     * Check if the entity matches one of the provided types
     *
     * @param names
     * @return
     */
    public boolean isOneOf(String... names) {
        for (String name : names) {
            if (getType().getId().equals(name)) {
                return true;
            }
        }

        return false;
    }

}
