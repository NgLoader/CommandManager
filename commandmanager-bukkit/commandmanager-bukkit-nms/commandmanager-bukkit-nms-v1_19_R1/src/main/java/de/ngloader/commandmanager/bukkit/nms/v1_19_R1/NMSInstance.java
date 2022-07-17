package de.ngloader.commandmanager.bukkit.nms.v1_19_R1;

import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.ngloader.commandmanager.api.bukkit.CommandManager;
import de.ngloader.commandmanager.bukkit.nms.api.NmsApi;
import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;

class NMSInstance implements NmsApi {

//	static final Class<?> CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET = MCReflectionUtil.getMinecraftServerClass("network.protocol.game.ServerboundCommandSuggestionPacket");
//	static final Class<?> CLASS_CLIENTBOUND_COMMAND_SUGGESTIONS_PACKET = MCReflectionUtil.getMinecraftServerClass("network.protocol.game.ClientboundCommandSuggestionsPacket");
//
//	static final Method METHOD_CRAFTPLAYER_GET_HANDLE = ReflectionUtil.getMethod(MCReflectionUtil.getCraftBukkit("entity.CraftPlayer"), "getHandle");
//	static final Method METHOD_CONNECTION_GET_CONNECTION = ReflectionUtil.getMethod(MCReflectionUtil.getMinecraftServer("network.Connection"), "getConnection");
//	static final Method METHOD_CONNECTION_SEND = ReflectionUtil.getMethod(MCReflectionUtil.getMinecraftServer("network.Connection"), "send");
//
//	static final Method METHOD_GET_SERVERBOUND_COMMAND_SUGGESTION_PACKET_COMMAND = ReflectionUtil.getMethod(CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET, "getCommand");
//	static final Method METHOD_GET_SERVERBOUND_COMMAND_SUGGESTION_PACKET_ID = ReflectionUtil.getMethod(CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET, "getId");
//
//	static final Constructor<?> CONSTRUCTOR_SERVERBOUND_COMMAND_SUGGESTION_PACKET = ReflectionUtil.getConstructor(CLASS_CLIENTBOUND_COMMAND_SUGGESTIONS_PACKET);

	private final NgBukkitCommandManager commandManager = new NgBukkitCommandManager();

	@Override
	public void inject(Player player) {
		ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();
		Connection connection = serverPlayer.connection.getConnection();
		Channel channel = connection.channel;
		channel.pipeline().addAfter("decoder", "commandmanager-decoder", new NgPacketDecoder(this.commandManager.getDispatcher(), player, connection));
	}

	@Override
	public void uninject(Player player) {
		ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();
		Connection connection = serverPlayer.connection.getConnection();
		Channel channel = connection.channel;
		channel.pipeline().remove("commandmanager-decoder");
	}

	@Override
	public CommandManager getCommandManager() {
		return this.commandManager;
	}
}
