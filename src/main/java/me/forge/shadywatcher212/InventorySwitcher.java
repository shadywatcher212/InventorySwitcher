package me.forge.shadywatcher212;

// Minecraft imports
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

// Forge imports
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class InventorySwitcher {

	CommandSwitchInventory cmdSwitchInventory = new CommandSwitchInventory();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(cmdSwitchInventory);
	}
	
	/**
	 * Swaps the current inventory of the player with the inventory of the specified profile.
	 * @param player
	 * @param profile
	 */
	public static void swapInventory(EntityPlayer player, String profile) {
		player.addChatMessage(new ChatComponentText(profile));
	}
	
	/**
	 * Saves the current inventory of the player under the specified profile.
	 * @param player
	 * @param profile
	 */
	public static boolean saveInventory(EntityPlayer player, String profile) {
		return false;
	}
	
	/**
	 * Returns an array containing the available profiles to the specified player.
	 * @param player
	 * @return
	 */
	public static String[] getInventoryProfiles(EntityPlayer player) {
		// TODO: return the profile array
		return null;
	}
}
