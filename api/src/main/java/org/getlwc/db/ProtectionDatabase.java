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
package org.getlwc.db;

import org.getlwc.Location;
import org.getlwc.entity.Entity;
import org.getlwc.meta.Meta;
import org.getlwc.model.Protection;

import java.util.Set;

public interface ProtectionDatabase {

    /**
     * Create an empty protection not attached to anything.
     *
     * @return
     */
    public Protection createProtection();

    /**
     * Load a protection from the database at the given location
     *
     * @return
     */
    public Protection loadProtection(Location location);

    /**
     * Load a protection from the database for the given entity
     *
     * @return
     */
    public Protection loadProtection(Entity entity);

    /**
     * Load a protection from the database for the given id
     *
     * @param id
     * @return
     */
    public Protection loadProtection(int id);

    /**
     * Save a protection to the database
     *
     * @param protection
     */
    public void saveProtection(Protection protection);

    /**
     * Remove a protection and all associated data about it from the database
     *
     * @param protection
     */
    public void removeProtection(Protection protection);

    /**
     * Load all of a protection's attributes from the database
     *
     * @param protection
     * @return
     */
    public Set<Meta> loadProtectionMetadata(Protection protection);

    /**
     * Save or create an attribute in the database.
     *
     * @param meta
     * @param protection
     * @param meta
     */
    public void saveOrCreateProtectionMetadata(Protection protection, Meta meta);

    /**
     * Remove a protection's attribute from the database
     *
     * @param meta
     * @param protection
     * @param meta
     */
    public void removeProtectionMetadata(Protection protection, Meta meta);

    /**
     * Remove all protection attributes from a protection from the database
     *
     * @param protection
     */
    public void removeAllProtectionMetadata(Protection protection);

}
