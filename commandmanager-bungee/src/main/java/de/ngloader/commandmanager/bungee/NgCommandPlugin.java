package de.ngloader.commandmanager.bungee;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.ngloader.commandmanager.api.bungee.CommandManagerPlugin;
import de.ngloader.commandmanager.core.ReflectionUtil;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class NgCommandPlugin extends Plugin implements CommandManagerPlugin, Listener {

	private NgBungeeCommandManager commandManager;

	@Override
	public void onLoad() {
		this.commandManager = new NgBungeeCommandManager(this);
	}

	@Override
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, this);
	}

	@Override
	public NgBungeeCommandManager getCommandManager() {
		return this.commandManager;
	}

	@EventHandler
	public void onPostLogin(PostLoginEvent event) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ProxiedPlayer player = event.getPlayer();
		Object playerChannel = ReflectionUtil.getField(player, "ch");
		Method getHandle = ReflectionUtil.getMethod(playerChannel.getClass(), "getHandle");
		Channel channel = (Channel) getHandle.invoke(playerChannel);
		channel.pipeline().addAfter("packet-decoder", "ng-decoder", new NgPacketDecoder(this, player));
	}
}