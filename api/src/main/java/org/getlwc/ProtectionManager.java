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

import org.getlwc.entity.Entity;
import org.getlwc.model.Protection;
import org.getlwc.role.RoleRegistry;

import java.util.UUID;

public interface ProtectionManager {

    /**
     * Check if the given block can be protected
     *
     * @param block
     * @return true if the block can be protected
     */
    public boolean isBlockProtectable(Block block);

    /**
     * Check if the given entity can be protected
     *
     * @param entity
     * @return true if the entity can be protected
     */
    public boolean isEntityProtectable(Entity entity);

    /**
     * Find a protection at the given location
     *
     * @param location
     * @return
     */
    public Protection loadProtection(Location location);

    /**
     * Load protection for given entity
     *
     * @param entity
     * @return
     */
    public Protection loadProtection(Entity entity);

    /**
     * Create a protection in the world
     *
     * @param owner
     * @param location
     * @return
     */
    public Protection createProtection(UUID owner, Location location);

    /**
     * Create a protection for entity in the world
     *
     * @param owner
     * @param target
     * @return
     */
    public Protection createProtection(UUID owner, Entity target);

    /**
     * Get the registry for roles
     *
     * @return
     */
    public RoleRegistry getRoleRegistry();

}
