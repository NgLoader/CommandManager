package de.ngloader.commandmanager.api.bukkit.util;

import java.util.Objects;

import org.bukkit.util.Vector;

public class Vector2 {

	public final float x;
	public final float y;

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector toVector() {
		return new Vector(this.x, 0, this.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vector2)) {
			return false;
		}
		Vector2 other = (Vector2) obj;
		return x == other.x && y == other.y;
	}
}