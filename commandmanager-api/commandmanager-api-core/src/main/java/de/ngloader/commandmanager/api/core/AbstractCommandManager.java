package de.ngloader.commandmanager.api.core;

import com.mojang.brigadier.CommandDispatcher;

public interface AbstractCommandManager<T> {

	void registerObject(Object object);

	void registerClass(Class<?> clazz);

	CommandDispatcher<T> getDispatcher();
}
