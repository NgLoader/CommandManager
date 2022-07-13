package de.ngloader.commandmanager.bungee;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.protocol.PacketWrapper;
import net.md_5.bungee.protocol.packet.TabCompleteRequest;
import net.md_5.bungee.protocol.packet.TabCompleteResponse;

public class NgPacketDecoder extends MessageToMessageDecoder<PacketWrapper> {

	private final CommandDispatcher<CommandSender> dispatcher;
	private final ProxiedPlayer player;

	public NgPacketDecoder(NgCommandPlugin plugin, ProxiedPlayer player) {
		this.dispatcher = plugin.getCommandManager().getDispatcher();
		this.player = player;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, PacketWrapper msg, List<Object> out) throws Exception {
		if (msg.packet == null) {
			out.add(msg);
			return;
		}

		if (msg.packet instanceof TabCompleteRequest) {
			TabCompleteRequest packet = (TabCompleteRequest) msg.packet;
			StringReader cursor = new StringReader(packet.getCursor());
			if (cursor.canRead() && cursor.peek() == '/') {
				cursor.skip();
			}

			ParseResults<CommandSender> result = this.dispatcher.parse(cursor, this.player);
			this.dispatcher.getCompletionSuggestions(result).whenComplete((suggestions, error) -> {
				if (error != null) {
					error.printStackTrace();
					return;
				}

				if (this.player.isConnected() && !suggestions.isEmpty()) {
					this.player.unsafe().sendPacket(new TabCompleteResponse(packet.getTransactionId(), suggestions));
				} else {
					out.add(msg);
				}
			});
		} else {
			out.add(msg);
		}
	}
}