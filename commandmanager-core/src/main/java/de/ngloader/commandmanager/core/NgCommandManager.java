package de.ngloader.commandmanager.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;

import de.ngloader.commandmanager.api.core.AbstractCommandManager;
import de.ngloader.commandmanager.api.core.BrigadierCommand;

public class NgCommandManager<T> implements AbstractCommandManager<T> {

	private final CommandDispatcher<T> dispatcher;

	private final Map<Object, List<String>> registeredCommands = new HashMap<>();

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
		List<String> children = this.registeredCommands.get(object);
		if (children == null) {
			children = new ArrayList<>();
			this.registeredCommands.put(object, children);
		}

		for (Method method : object.getClass().getMethods()) {
			if (method.getAnnotationsByType(BrigadierCommand.class).length == 1 && !Modifier.isStatic(method.getModifiers())) {
				children.addAll(this.registerMethod(method, object));
			}
		}
	}

	@Override
	public void registerClass(Class<?> clazz) {
		List<String> children = this.registeredCommands.get(clazz);
		if (children == null) {
			children = new ArrayList<>();
			this.registeredCommands.put(clazz, children);
		}

		for (Method method : clazz.getMethods()) {
			if (method.getAnnotationsByType(BrigadierCommand.class).length == 1 && Modifier.isStatic(method.getModifiers())) {
				children.addAll(this.registerMethod(method, null));
			}
		}
	}

	@Override
	public void unregister(Object object) {
		List<String> children = this.registeredCommands.get(object);
		if (children != null) {
			children.forEach(this::unregister);
		}
	}

	@Override
	public void unregister(Class<?> clazz) {
		List<String> children = this.registeredCommands.get(clazz);
		if (children != null) {
			children.forEach(this::unregister);
		}
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

	private List<String> registerMethod(Method method, Object instance) {
		if (method.getParameterCount() == 1
				&& CommandDispatcher.class.isAssignableFrom(method.getParameters()[0].getType())) {
			try {
				Set<String> childrenExist = this.getCurrentChildren();
				method.invoke(instance, this.dispatcher);
				List<String> childrenAdded = new ArrayList<>(this.getCurrentChildren());
				childrenAdded.removeAll(childrenExist);
				return Collections.unmodifiableList(childrenAdded);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new IllegalArgumentException(
						"Unable to register command in class \"" + method.getClass().getSimpleName()
								+ "\"!", e);
			}
		} else {
			throw new IllegalArgumentException(
					"Unable to register command in class \"" + method.getClass().getSimpleName()
							+ "\"! Parameter count is zero or argument type is not CommandDispatcher");
		}
	}

	@SuppressWarnings("unchecked")
	private Set<String> getCurrentChildren() {
		Map<String, ?> children = ((Map<String, ?>) ReflectionUtil.getField(CommandNode.class, this.dispatcher.getRoot(), "children"));
		return children.keySet();
	}

	@Override
	public CommandDispatcher<T> getDispatcher() {
		return this.dispatcher;
	}
}