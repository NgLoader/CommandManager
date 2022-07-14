package de.ngloader.commandmanager.bukkit.nms.api;

import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.CommandNode;

public interface NmsApi {

	SimpleCommandMap getCommandMap();

	Command<?> createWrapper(CommandNode<CommandSender> node);

	void inject(Player player);

	void uninject(Player player);
}