package de.ngloader.commandmanager.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;

import de.ngloader.commandmanager.api.core.AbstractCommandManager;
import de.ngloader.commandmanager.api.core.BrigadierCommand;

public class NgCommandManager<T> implements AbstractCommandManager<T> {

	private final CommandDispatcher<T> dispatcher;

	public NgCommandManager() {
		this.dispatcher = new CommandDispatcher<>();
	}

	public NgCommandManager(CommandDispatcher<T> dispatcher) {
		this.dispatcher = dispatcher;
	}

	public int execute(StringReader input, T source) throws CommandSyntaxException {
		try {
			return this.dispatcher.execute(input, source);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void registerObject(Object object) {
		for (Method method : object.getClass().getMethods()) {
			if (method.getAnnotationsByType(BrigadierCommand.class).length == 1 && !Modifier.isStatic(method.getModifiers())) {
				this.registerMethod(method, object);
			}
		}
	}

	@Override
	public void registerClass(Class<?> clazz) {
		for (Method method : clazz.getMethods()) {
			if (method.getAnnotationsByType(BrigadierCommand.class).length == 1 && Modifier.isStatic(method.getModifiers())) {
				this.registerMethod(method, null);
			}
		}
	}

	@Override
	public void unregister(Object object) {
	}

	@Override
	public void unregister(Class<?> clazz) {
	}

	@Override
	public void unregister(String command) {
		RootCommandNode<?> node = this.dispatcher.getRoot();
		((Map<?, ?>) ReflectionUtil.getField(CommandNode.class, node, "children")).remove(command);
		((Map<?, ?>) ReflectionUtil.getField(CommandNode.class, node, "literals")).remove(command);
		((Map<?, ?>) ReflectionUtil.getField(CommandNode.class, node, "arguments")).remove(command);
	}

	@Override
	public void unregisterNode(CommandNode<T> node) {
		this.unregister(node.getName());
	}

	private void registerMethod(Method method, Object instance) {
		if (method.getParameterCount() == 1
				&& CommandDispatcher.class.isAssignableFrom(method.getParameters()[0].getType())) {
			try {
				method.invoke(instance, this.dispatcher);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"Unable to register command in class \"" + method.getClass().getSimpleName()
							+ "\"! Parameter count is zero or argument type is not CommandDispatcher");
		}
	}

	@Override
	public CommandDispatcher<T> getDispatcher() {
		return this.dispatcher;
	}
}