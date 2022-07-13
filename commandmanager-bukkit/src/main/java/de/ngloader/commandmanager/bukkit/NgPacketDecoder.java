package de.ngloader.commandmanager.bukkit;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class NgPacketDecoder extends MessageToMessageDecoder<Object> {

	private final CommandDispatcher<CommandSender> dispatcher;
	private final Player player;

	private final Object connection;

	public NgPacketDecoder(NgCommandPlugin plugin, Player player, Object connection) {
		this.dispatcher = plugin.getCommandManager().getDispatcher();
		this.connection = connection;
		this.player = player;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		if (msg == null) {
			out.add(msg);
			return;
		}

		if (NMSWrapper.CLASS_SERVERBOUND_COMMAND_SUGGESTION_PACKET.isInstance(msg)) {
			StringReader cursor = new StringReader((String) NMSWrapper.METHOD_GET_SERVERBOUND_COMMAND_SUGGESTION_PACKET_COMMAND.invoke(msg));
			if (cursor.canRead() && cursor.peek() == '/') {
				cursor.skip();
			}

			ParseResults<CommandSender> result = this.dispatcher.parse(cursor, this.player);
			this.dispatcher.getCompletionSuggestions(result).whenComplete((suggestions, error) -> {
				if (error != null) {
					error.printStackTrace();
					return;
				}

				if (this.player.isOnline() && !suggestions.isEmpty()) {
					try {
						int id = (int) NMSWrapper.METHOD_GET_SERVERBOUND_COMMAND_SUGGESTION_PACKET_ID.invoke(msg);
						Object packet = NMSWrapper.CONSTRUCTOR_SERVERBOUND_COMMAND_SUGGESTION_PACKET.newInstance(id, suggestions);
						NMSWrapper.METHOD_CONNECTION_SEND.invoke(this.connection, packet);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					out.add(msg);
				}
			});
		} else {
			out.add(msg);
		}
	}
}