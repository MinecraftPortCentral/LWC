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
package org.getlwc.db.jdbc;

import org.getlwc.util.Tuple;
import org.getlwc.util.TwoWayHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Helps JDBC by providing name -> id resolution (and also in reverse)
 */
public class JDBCLookupService {

    /**
     * Enum that identifies a lookup type when looking up a name.
     * The order is very important as the ordinal is used internally
     */
    public enum LookupType {

        /**
         * An attribute's name
         */
        META_NAME("meta_name"),

        /**
         * A role's type
         */
        ROLE_TYPE("role_type"),

        /**
         * A role's name (e.g. player name)
         */
        ROLE_NAME("role_name"),

        /**
         * A world's uuid or name
         */
        WORLD_NAME("world_name"),

        /**
         * An entity's uuid
         */
        ENTITY_UUID("entity_uuid");

        String suffix;

        LookupType(String suffix) {
            this.suffix = suffix;
        }

        public String getSuffix() {
            return suffix;
        }

    }

    /**
     * The lookup table for each type
     */
    private final Map<LookupType, TwoWayHashMap<String, Integer>> lookup = new HashMap<>();

    /**
     * The database being used
     */
    private JDBCDatabase database;

    public JDBCLookupService(JDBCDatabase database) {
        this.database = database;
    }

    /**
     * Populate the service
     */
    public void populate() {
        for (LookupType type : LookupType.values()) {
            TwoWayHashMap<String, Integer> lookupValues = new TwoWayHashMap<>();
            lookup.put(type, lookupValues);

            int size = 0;
            for (Tuple<String, Integer> tuple : database.getLookupAssociations(type)) {
                lookupValues.put(tuple.first(), tuple.second());
                size++;
            }

            System.out.println(type + ": " + size + " lookup values");
        }
    }

    /**
     * Get the id for the given name. If it does not exist then it will be created
     *
     * @param type
     * @param name
     * @return
     */
    public int get(LookupType type, String name) {
        int id;

        TwoWayHashMap<String, Integer> table = lookup.get(type);

        if (!table.getForward().containsKey(name)) {
            id = database.createLookup(type, name);
            table.put(name, id);
        } else {
            id = table.getForward(name);
        }

        return id;
    }

    /**
     * Get an entry by its id
     *
     * @param type
     * @param id
     * @return
     */
    public String get(LookupType type, int id) {
        return lookup.get(type).getBackward(id);
    }

}
