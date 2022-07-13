package de.ngloader.commandmanager.api.bukkit.util;

import java.util.Objects;

import org.bukkit.util.Vector;

public class Vector3 {

	public final double x;
	public final double y;
	public final double z;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector toVector() {
		return new Vector(this.x, this.y, this.z);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vector3)) {
			return false;
		}
		Vector3 other = (Vector3) obj;
		return x == other.x && y == other.y && z == other.z;
	}
}