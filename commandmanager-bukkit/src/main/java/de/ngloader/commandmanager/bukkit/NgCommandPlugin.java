package de.ngloader.commandmanager.bukkit;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.ngloader.commandmanager.api.bukkit.CommandManagerPlugin;
import de.ngloader.commandmanager.core.ReflectionUtil;
import io.netty.channel.Channel;

public class NgCommandPlugin extends JavaPlugin implements CommandManagerPlugin, Listener {

	private NgBukkitCommandManager commandManager;

	@Override
	public void onLoad() {
		this.commandManager = new NgBukkitCommandManager();
	}

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@Override
	public NgBukkitCommandManager getCommandManager() {
		return this.commandManager;
	}

	@EventHandler
	public void onLogin(PlayerJoinEvent event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Player player = event.getPlayer();
		Object serverPlayer = NMSWrapper.METHOD_CRAFTPLAYER_GET_HANDLE.invoke(player);
		Object packetListener = ReflectionUtil.getField(serverPlayer, "connection");
		Object connection = NMSWrapper.METHOD_CONNECTION_GET_CONNECTION.invoke(packetListener);
		Channel channel = (Channel) ReflectionUtil.getField(connection, "channel");
		channel.pipeline().addAfter("decoder", "stoffi-decoder", new NgPacketDecoder(this, player, connection));
	}
}