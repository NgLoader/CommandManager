package de.ngloader.commandmanager.api.bukkit.util;

import java.util.Objects;

import org.bukkit.util.Vector;

public class Point3D {

	public final int x;
	public final int y;
	public final int z;

	public Point3D(int x, int y, int z) {
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
		if (!(obj instanceof Point3D)) {
			return false;
		}
		Point3D other = (Point3D) obj;
		return x == other.x && y == other.y && z == other.z;
	}
}