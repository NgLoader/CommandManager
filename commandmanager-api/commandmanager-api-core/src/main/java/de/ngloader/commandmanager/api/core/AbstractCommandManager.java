package de.ngloader.commandmanager.api.core;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.CommandNode;

public interface AbstractCommandManager<T> {

	void registerObject(Object object);

	void registerClass(Class<?> clazz);

	void unregister(Object object);

	void unregister(Class<?> clazz);

	void unregister(String command);

	void unregisterNode(CommandNode<T> node);

	CommandDispatcher<T> getDispatcher();
}
