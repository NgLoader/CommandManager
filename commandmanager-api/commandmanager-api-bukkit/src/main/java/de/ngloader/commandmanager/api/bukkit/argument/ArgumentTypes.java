package de.ngloader.commandmanager.api.bukkit.argument;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import org.bukkit.Axis;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import de.ngloader.commandmanager.api.bukkit.util.GameProfile;
import de.ngloader.commandmanager.api.bukkit.util.Vector2;
import de.ngloader.commandmanager.api.bukkit.util.Vector3;
import de.ngloader.commandmanager.api.core.argument.CoreArgumentTypes;

public interface ArgumentTypes extends CoreArgumentTypes {

	/**
	 * Examples: "stone", "minecraft:stone", "stone[foo=bar]", "foo{bar=baz}"
	 */
	public static ArgumentType<?> block() {
		return ArgumentTypesInstance.instance.block0();
	}
	ArgumentType<?> block0();

	public static Material getBlock(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getBlock0(context, name);
	}
	Material getBlock0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "~0.5 ~1 ~-5"
	 */
	public static ArgumentType<?> blockPos() {
		return ArgumentTypesInstance.instance.blockPos0();
	}
	ArgumentType<?> blockPos0();

	public static Vector2 getBlockPosRotation(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getBlockPosRotation0(context, name);
	}
	Vector2 getBlockPosRotation0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	public static Location getBlockPos(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getBlockPos0(context, name);
	}
	Location getBlockPos0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "xyz", "x
	 */
	public static ArgumentType<?> swizzle() {
		return ArgumentTypesInstance.instance.swizzle0();
	}
	ArgumentType<?> swizzle0();

	public static EnumSet<Axis> getSwizzle(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getSwizzle0(context, name);
	}
	EnumSet<Axis> getSwizzle0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "0 0", "~ ~", "0.1 -0.5", "~1 ~-2"
	 */
	public static ArgumentType<?> vec2() {
		return ArgumentTypesInstance.instance.vec20();
	}
	ArgumentType<?> vec20();

	public static ArgumentType<?> vec2(boolean centerCorrect) {
		return ArgumentTypesInstance.instance.vec20(centerCorrect);
	}
	ArgumentType<?> vec20(boolean centerCorrect);

	public static Vector2 getVec2(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getVec20(context, name);
	}
	Vector2 getVec20(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "0.1 -0.5 .9", "~0.5 ~1 ~-5"
	 */
	public static ArgumentType<?> vec3() {
		return ArgumentTypesInstance.instance.vec30();
	}
	ArgumentType<?> vec30();

	public static ArgumentType<?> vec3(boolean centerCorrect) {
		return ArgumentTypesInstance.instance.vec30(centerCorrect);
	}
	ArgumentType<?> vec30(boolean centerCorrect);

	public static Vector3 getVec3(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getVec30(context, name);
	}
	Vector3 getVec30(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "stick", "minecraft:stick", "stick{foo=bar}"
	 */
	public static ArgumentType<?> item() {
		return ArgumentTypesInstance.instance.item0();
	}
	ArgumentType<?> item0();

	public static Material getItem(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getItem0(context, name);
	}
	Material getItem0(CommandContext<CommandSender> context, String name);

	public static ItemStack getItemStack(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getItemStack0(context, name);
	}
	ItemStack getItemStack0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	public static ItemStack getItemStack(CommandContext<CommandSender> context, String name, int amount) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getItemStack0(context, name);
	}
	ItemStack getItemStack0(CommandContext<CommandSender> context, String name, int amount) throws CommandSyntaxException;

	/**
	 * Examples: "0", "~", "~-5"
	 */
	public static ArgumentType<?> angle() {
		return ArgumentTypesInstance.instance.angle0();
	}
	ArgumentType<?> angle0();

	public static float getAngle(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getAngle0(context, name);
	}
	float getAngle0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "red", "green"
	 */
	public static ArgumentType<?> color() {
		return ArgumentTypesInstance.instance.color0();
	}
	ArgumentType<?> color0();

	public static Color getColor(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getColor0(context, name);
	}
	Color getColor0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "overworld", "nether"
	 */
	public static ArgumentType<?> world() {
		return ArgumentTypesInstance.instance.world0();
	}
	ArgumentType<?> world0();

	public static World getWorld(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getWorld0(context, name);
	}
	World getWorld0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "Player", "0123", "@e", "@e[type=foo]", "dd12be42-52a9-4a91-a8a1-11c01849e498"
	 */
	public static ArgumentType<?> entity() {
		return ArgumentTypesInstance.instance.entity0();
	}
	ArgumentType<?> entity0();

	public static Entity getEntity(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getEntity0(context, name);
	}
	Entity getEntity0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "Player", "0123", "@e", "@e[type=foo]", "dd12be42-52a9-4a91-a8a1-11c01849e498"
	 */
	public static ArgumentType<?> entities() {
		return ArgumentTypesInstance.instance.entities0();
	}
	ArgumentType<?> entities0();

	public static List<Entity> getEntities(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getEntities0(context, name);
	}
	List<Entity> getEntities0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	public static List<Entity> getOptionalEntities(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getOptionalEntities0(context, name);
	}
	List<Entity> getOptionalEntities0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	public static List<Player> getOptionalPlayers(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getOptionalPlayers0(context, name);
	}
	List<Player> getOptionalPlayers0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "Player", "0123", "@e", "@e[type=foo]", "dd12be42-52a9-4a91-a8a1-11c01849e498"
	 */
	public static ArgumentType<?> player() {
		return ArgumentTypesInstance.instance.player0();
	}
	ArgumentType<?> player0();

	public static Player getPlayer(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getPlayer0(context, name);
	}
	Player getPlayer0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "Player", "0123", "@e", "@e[type=foo]", "dd12be42-52a9-4a91-a8a1-11c01849e498"
	 */
	public static ArgumentType<?> players() {
		return ArgumentTypesInstance.instance.players0();
	}
	ArgumentType<?> players0();

	public static List<Player> getPlayers(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getPlayers0(context, name);
	}
	List<Player> getPlayers0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "minecraft:pig", "cow"
	 */
	public static ArgumentType<?> entitySummonId() {
		return ArgumentTypesInstance.instance.entitySummonId0();
	}
	ArgumentType<?> entitySummonId0();

	public static NamespacedKey getSummonableEntity(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getSummonableEntity0(context, name);
	}
	NamespacedKey getSummonableEntity0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "Player", "0123", "dd12be42-52a9-4a91-a8a1-11c01849e498", "@e"
	 */
	public static ArgumentType<?> gameProfile() {
		return ArgumentTypesInstance.instance.gameProfile0();
	}
	ArgumentType<?> gameProfile0();

	public static Collection<GameProfile> getGameProfiles(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getGameProfiles0(context, name);
	}
	Collection<GameProfile> getGameProfiles0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	/**
	 * Examples: "unbreaking", "silk_touch"
	 */
	public static ArgumentType<?> enchantment() {
		return ArgumentTypesInstance.instance.enchantment0();
	}
	ArgumentType<?> enchantment0();

	public static Enchantment getEnchantment(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getEnchantment0(context, name);
	}
	Enchantment getEnchantment0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "spooky", "effect", "speed", "slowness"
	 */
	public static ArgumentType<?> potionEffect() {
		return ArgumentTypesInstance.instance.potionEffect0();
	}
	ArgumentType<?> potionEffect0();

	public static PotionEffectType getPotionEffect(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getPotionEffect0(context, name);
	}
	PotionEffectType getPotionEffect0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "foo", "foo:bar", "particle with options"
	 */
	public static ArgumentType<?> particle() {
		return ArgumentTypesInstance.instance.particle0();
	}
	ArgumentType<?> particle0();

	public static Particle getParticle(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getParticle0(context, name);
	}
	Particle getParticle0(CommandContext<CommandSender> context, String name);

	public static Particle readParticle(StringReader reader) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.readParticle0(reader);
	}
	Particle readParticle0(StringReader reader) throws CommandSyntaxException;

	/**
	 * Examples: "foo", "foo:bar", "012"
	 */
	public static ArgumentType<?> resourceLocationId() {
		return ArgumentTypesInstance.instance.resourceLocationId0();
	}
	ArgumentType<?> resourceLocationId0();

	public static Advancement getAdvancement(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getAdvancement0(context, name);
	}
	Advancement getAdvancement0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	public static Attribute getAttribute(CommandContext<CommandSender> context, String name) throws CommandSyntaxException {
		return ArgumentTypesInstance.instance.getAttribute0(context, name);
	}
	Attribute getAttribute0(CommandContext<CommandSender> context, String name) throws CommandSyntaxException;

	public static NamespacedKey getNamespacedKey(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getNamespacedKey0(context, name);
	}
	NamespacedKey getNamespacedKey0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "sidebar", "foo.bar"
	 */
	public static ArgumentType<?> displaySlot() {
		return ArgumentTypesInstance.instance.displaySlot0();
	}
	ArgumentType<?> displaySlot0();

	public static int getDisplaySlot(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getDisplaySlot0(context, name);
	}
	int getDisplaySlot0(CommandContext<CommandSender> context, String name);

	/**
	 * Examples: "container.5", "12", "weapon"
	 */
	public static ArgumentType<?> slot() {
		return ArgumentTypesInstance.instance.slot0();
	}
	ArgumentType<?> slot0();

	public static int getSlot(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getSlot0(context, name);
	}
	int getSlot0(CommandContext<CommandSender> context, String name);

	/**
	 * Converted into tick's
	 * 
	 * Examples: "0d", "0s", "0t", "0"
	 */
	public static ArgumentType<?> time() {
		return ArgumentTypesInstance.instance.time0();
	}
	ArgumentType<?> time0();

	/**
	 * Converted into tick's
	 */
	public static Integer getTime(CommandContext<CommandSender> context, String name) {
		return ArgumentTypesInstance.instance.getTime0(context, name);
	}
	Integer getTime0(CommandContext<CommandSender> context, String name);
}
