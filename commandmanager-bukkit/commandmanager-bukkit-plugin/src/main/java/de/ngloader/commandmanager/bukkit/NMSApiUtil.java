package de.ngloader.commandmanager.bukkit;

import org.bukkit.Bukkit;

import de.ngloader.commandmanager.bukkit.nms.api.NmsApi;

public class NMSApiUtil {

	private static final String CRAFTBUKKIT_PATH = "org.bukkit.craftbukkit";

	private static final String SERVER_VERSION;

	static {
		String version = Bukkit.getServer().getClass().getName().substring(CRAFTBUKKIT_PATH.length());
		SERVER_VERSION = version.substring(0, version.length() - "CraftServer".length());
	}

	public static NmsApi newInstance() {
		try {
			System.out.println("Checking for NMS version \"" + SERVER_VERSION + "\"");
			Class<?> versionClass = Class.forName("de.ngloader.commandmanager.bukkit.nms" + SERVER_VERSION + ".NMSInstance.class");
			System.out.println("NMS version \"" + SERVER_VERSION + "\" found");
			return (NmsApi) versionClass.getConstructors()[0].newInstance();
		} catch (Exception e) {
			System.out.println("Error by location minecraft version");
		}
		throw new IllegalArgumentException("Unable to recive minecraft nms version");
	}
}