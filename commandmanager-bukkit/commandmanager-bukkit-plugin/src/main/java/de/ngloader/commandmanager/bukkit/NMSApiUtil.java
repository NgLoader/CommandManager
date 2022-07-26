package de.ngloader.commandmanager.bukkit;

import de.ngloader.commandmanager.api.bukkit.util.MCReflectionUtil;
import de.ngloader.commandmanager.bukkit.nms.api.NmsApi;

public class NMSApiUtil {

	public static NmsApi newInstance() {
		try {
			String version = MCReflectionUtil.getServerVersion();
			System.out.println("Checking for NMS version \"" + version + "\"");
			Class<?> versionClass = Class.forName("de.ngloader.commandmanager.bukkit.nms" + version + ".NMSInstance.class");
			System.out.println("NMS version \"" + version + "\" found");
			return (NmsApi) versionClass.getConstructors()[0].newInstance();
		} catch (Exception e) {
			System.out.println("Error by location minecraft version");
		}
		throw new IllegalArgumentException("Unable to recive minecraft nms version");
	}
}