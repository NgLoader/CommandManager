package de.ngloader.commandmanager.bukkit;

import org.bukkit.command.CommandSender;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;

public class NgCommandDispatcher extends CommandDispatcher<CommandSender> {

	private NgBukkitCommandManager manager;

	@Override
	public LiteralCommandNode<CommandSender> register(LiteralArgumentBuilder<CommandSender> command) {
		LiteralCommandNode<CommandSender> commandNode = super.register(command);
		this.manager.registerCommand(commandNode);
		return commandNode;
	}

	public void setManager(NgBukkitCommandManager manager) {
		this.manager = manager;
	}
}