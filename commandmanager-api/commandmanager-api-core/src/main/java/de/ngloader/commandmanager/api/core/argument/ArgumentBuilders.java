package de.ngloader.commandmanager.api.core.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

public class ArgumentBuilders {

	public static <S> LiteralArgumentBuilder<S> literal(String name) {
		return LiteralArgumentBuilder.literal(name);
	}

	public static <S, T> RequiredArgumentBuilder<S, T> argument(String name, ArgumentType<T> type) {
		return RequiredArgumentBuilder.argument(name, type);
	}
}