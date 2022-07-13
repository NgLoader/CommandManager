package de.ngloader.commandmanager.api.bukkit.util;

import java.util.UUID;

public class GameProfile {

	public final UUID uuid;
	public final String name;

	public GameProfile(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public String getName() {
		return this.name;
	}
}
