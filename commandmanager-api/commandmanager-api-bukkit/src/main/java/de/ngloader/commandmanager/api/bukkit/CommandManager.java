package de.ngloader.commandmanager.api.bukkit;

import org.bukkit.command.CommandSender;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import de.ngloader.commandmanager.api.core.AbstractCommandManager;

public interface CommandManager extends AbstractCommandManager<CommandSender> {

	public static LiteralArgumentBuilder<CommandSender> literal(String name) {
		return LiteralArgumentBuilder.literal(name);
	}

	public static <T> RequiredArgumentBuilder<CommandSender, T> argument(String name, ArgumentType<T> type) {
		return RequiredArgumentBuilder.argument(name, type);
	}
}