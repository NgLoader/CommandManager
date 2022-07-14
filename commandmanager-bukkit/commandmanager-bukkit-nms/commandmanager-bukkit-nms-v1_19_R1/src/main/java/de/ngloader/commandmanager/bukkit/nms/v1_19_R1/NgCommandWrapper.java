package de.ngloader.commandmanager.bukkit.nms.v1_19_R1;

import java.util.Collections;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_19_R1.command.VanillaCommandWrapper;

import com.google.common.base.Joiner;
import com.mojang.brigadier.tree.CommandNode;

import net.minecraft.commands.CommandSourceStack;

public class NgCommandWrapper extends BukkitCommand {

	private final NgBukkitCommandManager mananger;

	public NgCommandWrapper(NgBukkitCommandManager dispatcher, CommandNode<CommandSender> command) {
		super(command.getName(), "A custom provided command.", command.getUsageText(), Collections.emptyList());
		this.mananger = dispatcher;
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if (!this.testPermission(sender)) {
			return true;
		} else {
			CommandSourceStack sourceStack = VanillaCommandWrapper.getListener(sender);
			this.mananger.executeCommand(sourceStack, this.toDispatcher(args, this.getName()), this.toDispatcher(args, commandLabel), true);
			return true;
		}
	}

	private String toDispatcher(String[] args, String name) {
		return name + (args.length > 0 ? " " + Joiner.on(' ').join(args) : "");
	}
}