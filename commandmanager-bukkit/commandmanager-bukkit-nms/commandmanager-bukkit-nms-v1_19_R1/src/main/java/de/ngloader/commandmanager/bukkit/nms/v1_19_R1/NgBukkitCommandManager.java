package de.ngloader.commandmanager.bukkit.nms.v1_19_R1;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;

import de.ngloader.commandmanager.api.bukkit.CommandManager;
import de.ngloader.commandmanager.core.NgCommandManager;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;

public class NgBukkitCommandManager extends NgCommandManager<CommandSender> implements CommandManager {

	private static Component fromMessage(Message message) {
		return (Component) (message instanceof Component ? (Component) message : Component.literal(message.getString()));
	}

	private SimpleCommandMap commandMap;

	public NgBukkitCommandManager() {
		super(new NgCommandDispatcher());
		this.commandMap = ((CraftServer) Bukkit.getServer()).getCommandMap();
	}

	void registerCommand(CommandNode<CommandSender> command) {
		this.commandMap.register("custom", new NgCommandWrapper(this, command));
	}

	int executeCommand(CommandSourceStack sender, String command, String label, boolean stripSlash) {
		StringReader stringReader = new StringReader(command);
		if (stripSlash && stringReader.canRead() && stringReader.peek() == '/') {
			stringReader.skip();
		}

		try {
			return this.getDispatcher().execute(stringReader, sender.getBukkitSender());
		} catch (CommandSyntaxException e) {
			sender.sendFailure(fromMessage(e.getRawMessage()));

			if (e.getInput() != null && e.getCursor() >= 0) {
				int length = Math.min(e.getInput().length(), e.getCursor());
				MutableComponent component = Component.literal("").withStyle(ChatFormatting.GRAY).withStyle(
						(modifier) -> modifier.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, label)));
				if (length > 10) {
					component.append("...");
				}

				component.append(e.getInput().substring(Math.max(0, length - 10), length));
				if (length < e.getInput().length()) {
					MutableComponent errorComponent = Component.literal(e.getInput().substring(length))
							.withStyle(new ChatFormatting[] { ChatFormatting.RED, ChatFormatting.UNDERLINE });
					component.append(errorComponent);
				}

				component.append(Component.translatable("command.context.here")
						.withStyle(new ChatFormatting[] { ChatFormatting.RED, ChatFormatting.ITALIC }));

				sender.sendFailure(component);
			}
		} catch (Exception e) {
			Component component = Component.literal(
					e.getMessage() == null ? e.getClass().getName() : e.getMessage());
			sender.sendFailure(Component.translatable("command.failed").withStyle(
					modifier -> modifier.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, component))));
		}
		return 0;
	}

	public SimpleCommandMap getCommandMap() {
		return this.commandMap;
	}
}