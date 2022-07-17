package de.ngloader.commandmanager.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.ngloader.commandmanager.api.bukkit.CommandManager;
import de.ngloader.commandmanager.api.bukkit.CommandManagerPlugin;
import de.ngloader.commandmanager.bukkit.nms.api.NmsApi;

public class NgCommandPlugin extends JavaPlugin implements CommandManagerPlugin, Listener {

	private CommandManager commandManager;
	private NmsApi api;

	@Override
	public void onLoad() {
		this.commandManager = new NgBukkitCommandManager();
	}

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@Override
	public CommandManager getCommandManager() {
		return this.commandManager;
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) {
		this.api.inject(this.commandManager.getDispatcher(), event.getPlayer());
	}
}