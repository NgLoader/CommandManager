package de.ngloader.commandmanager.api.bukkit.util;

import java.util.Objects;

import org.bukkit.util.Vector;

public class Point2D {

	public final int x;
	public final int z;

	public Point2D(int x, int z) {
		this.x = x;
		this.z = z;
	}

	public Vector toVector() {
		return new Vector(this.x, 0, this.z);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Point2D)) {
			return false;
		}
		Point2D other = (Point2D) obj;
		return x == other.x && z == other.z;
	}
}