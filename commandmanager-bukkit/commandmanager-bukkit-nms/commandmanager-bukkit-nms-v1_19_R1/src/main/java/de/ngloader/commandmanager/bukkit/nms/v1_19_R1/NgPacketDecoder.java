package de.ngloader.commandmanager.bukkit.nms.v1_19_R1;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundCommandSuggestionsPacket;
import net.minecraft.network.protocol.game.ServerboundCommandSuggestionPacket;

public class NgPacketDecoder extends MessageToMessageDecoder<Object> {

	private final CommandDispatcher<CommandSender> dispatcher;
	private final Player player;

	private final Connection connection;

	public NgPacketDecoder(CommandDispatcher<CommandSender> dispatcher, Player player, Connection connection) {
		this.dispatcher = dispatcher;
		this.connection = connection;
		this.player = player;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		if (msg == null) {
			out.add(msg);
			return;
		}

		if (msg instanceof ServerboundCommandSuggestionPacket packet) {
			StringReader cursor = new StringReader(packet.getCommand());
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
						this.connection.send(new ClientboundCommandSuggestionsPacket(packet.getId(), suggestions));
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