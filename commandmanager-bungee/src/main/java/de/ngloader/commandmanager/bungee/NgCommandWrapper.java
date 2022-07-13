package de.ngloader.commandmanager.bungee;

import com.google.common.base.Joiner;
import com.mojang.brigadier.tree.CommandNode;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class NgCommandWrapper extends Command {

	private final NgBungeeCommandManager mananger;

	public NgCommandWrapper(NgBungeeCommandManager dispatcher, CommandNode<CommandSender> command) {
		super(command.getName());
		this.mananger = dispatcher;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		this.mananger.executeCommand(sender, this.toDispatcher(args, this.getName()), this.toDispatcher(args, this.getName()), true);
	}

	private String toDispatcher(String[] args, String name) {
		return name + (args.length > 0 ? " " + Joiner.on(' ').join(args) : "");
	}
}