package de.ngloader.commandmanager.bukkit.nms.v1_19_R1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import de.ngloader.commandmanager.api.bukkit.util.MCReflectionUtil;
import de.ngloader.commandmanager.core.ReflectionUtil;

class NMSWrapper {

	static final Class<?> CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET = MCReflectionUtil.getMinecraftServerClass("network.protocol.game.ServerboundCommandSuggestionPacket");
	static final Class<?> CLASS_CLIENTBOUND_COMMAND_SUGGESTIONS_PACKET = MCReflectionUtil.getMinecraftServerClass("network.protocol.game.ClientboundCommandSuggestionsPacket");

	static final Method METHOD_CRAFTPLAYER_GET_HANDLE = ReflectionUtil.getMethod(MCReflectionUtil.getCraftBukkit("entity.CraftPlayer"), "getHandle");
	static final Method METHOD_CONNECTION_GET_CONNECTION = ReflectionUtil.getMethod(MCReflectionUtil.getMinecraftServer("network.Connection"), "getConnection");
	static final Method METHOD_CONNECTION_SEND = ReflectionUtil.getMethod(MCReflectionUtil.getMinecraftServer("network.Connection"), "send");

	static final Method METHOD_GET_SERVERBOUND_COMMAND_SUGGESTION_PACKET_COMMAND = ReflectionUtil.getMethod(CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET, "getCommand");
	static final Method METHOD_GET_SERVERBOUND_COMMAND_SUGGESTION_PACKET_ID = ReflectionUtil.getMethod(CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET, "getId");

	static final Constructor<?> CONSTRUCTOR_SERVERBOUND_COMMAND_SUGGESTION_PACKET = ReflectionUtil.getConstructor(CLASS_CLIENTBOUND_COMMAND_SUGGESTIONS_PACKET);
}
