package de.ngloader.commandmanager.api.bukkit.argument;

class ArgumentTypesInstance {

	protected static ArgumentTypes instance;

	static void setInstance(ArgumentTypes instance) {
		ArgumentTypesInstance.instance = instance;
	}
}
