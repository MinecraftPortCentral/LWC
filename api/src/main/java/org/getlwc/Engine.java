/*
 * Copyright (c) 2011-2013 Tyler Blair
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

import org.getlwc.command.CommandHandler;
import org.getlwc.command.ConsoleCommandSender;
import org.getlwc.configuration.Configuration;
import org.getlwc.db.Database;
import org.getlwc.economy.EconomyHandler;
import org.getlwc.factory.AbstractFactoryRegistry;
import org.getlwc.permission.PermissionHandler;
import org.getlwc.role.RoleFactory;

public interface Engine {

    /**
     * Get the version of minecraft this was targeted to
     *
     * @return
     */
    public String getTargetMinecraftVersion();

    /**
     * Get the {@link AbstractFactoryRegistry} which manages role associations
     *
     * @return
     */
    public AbstractFactoryRegistry<RoleFactory> getRoleRegistry();

    /**
     * Get the protection manager
     *
     * @return
     */
    public ProtectionManager getProtectionManager();

    /**
     * Get the server layer that provides some server specific utilities
     *
     * @return
     */
    public ServerLayer getServerLayer();

    /**
     * Get information about the server mod itself
     *
     * @return
     */
    public ServerInfo getServerInfo();

    /**
     * Get the API version
     *
     * @return
     */
    public String getBackendVersion();

    /**
     * Get the command handler
     *
     * @return
     */
    public CommandHandler getCommandHandler();

    /**
     * Get the console sender, used to send messages to the console
     *
     * @return
     */
    public ConsoleCommandSender getConsoleSender();

    /**
     * Get the database object
     *
     * @return
     */
    public Database getDatabase();

    /**
     * Gets the configuration file
     *
     * @return
     */
    public Configuration getConfiguration();

    /**
     * Get the library downloader object
     *
     * @return
     */
    public LibraryDownloader getLibraryDownloader();

    /**
     * Get the economy handler for the server
     *
     * @return
     */
    public EconomyHandler getEconomyHandler();

    /**
     * Get the permission handler for the server
     *
     * @return
     */
    public PermissionHandler getPermissionHandler();

    /**
     * Called when the engine is ready to be fully loaded. Initialization is completed here.
     */
    public void startup();

    /**
     * Called when the engine needs to be shutdown
     */
    public void shutdown();

}
