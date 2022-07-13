package de.ngloader.commandmanager.bungee;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.md_5.bungee.api.CommandSender;

public class NgCommandDispatcher extends CommandDispatcher<CommandSender> {

	private NgBungeeCommandManager manager;

	@Override
	public LiteralCommandNode<CommandSender> register(LiteralArgumentBuilder<CommandSender> command) {
		LiteralCommandNode<CommandSender> commandNode = super.register(command);
		this.manager.registerCommand(commandNode);
		return commandNode;
	}

	public void setManager(NgBungeeCommandManager manager) {
		this.manager = manager;
	}
}