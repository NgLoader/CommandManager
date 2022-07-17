package de.ngloader.commandmanager.bukkit.nms.api;

import org.bukkit.entity.Player;

import de.ngloader.commandmanager.api.bukkit.CommandManager;

public interface NmsApi {

	void inject(Player player);

	void uninject(Player player);

	CommandManager getCommandManager();
}