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
package org.getlwc.sponge.permission;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import org.getlwc.entity.Player;
import org.getlwc.permission.PermissionHandler;
import org.spongepowered.api.service.permission.PermissionService;
import org.spongepowered.api.service.permission.Subject;
import org.spongepowered.api.service.permission.SubjectCollection;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

public class SpongePermissionHandler implements PermissionHandler {
    private final PermissionService permService;

    public SpongePermissionHandler(PermissionService permService) {
        this.permService = permService;
    }

    @Override
    public String getName() {
        return "Default";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean hasPermission(Player player, String node) {
        return player.hasPermission(node);
    }

    @Override
    public Set<String> getGroups(Player player) {
        /*if (!this.permService...isPresent()) {
            return ImmutableSet.of();
        }*/
        final SubjectCollection groupColl = this.permService.getGroupSubjects();
        List<Subject> parent = this.permService.getUserSubjects().get(player.getUUID().toString()).getParents();
        return ImmutableSet.copyOf(Iterables.transform(Iterables.filter(parent, new Predicate<Subject>() {
            @Override
            public boolean apply(Subject input) {
                return input.getContainingCollection().equals(groupColl);
            }
        }), new Function<Subject, String>() {
            @Nullable
            @Override
            public String apply(Subject input) {
                return input.getIdentifier();
            }
        }));
    }

}
