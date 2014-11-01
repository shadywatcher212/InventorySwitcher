package me.forge.shadywatcher212;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChatComponentText;

public class CommandSwitchInventory extends CommandBase {	
	
	private static final int MAX_PROFILES = 3;
	
	/* Overrides for abstract base methods */
	
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "switch";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/switch [profile]";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		
		// Must be a real player
		if(sender instanceof EntityPlayer) {			
			EntityPlayer player = (EntityPlayer) sender;
			
			// Print profiles
			if (args.length == 0) {
				String[] profiles = InventorySwitcher.getInventoryProfiles(player);
				if(profiles == null) {
					// TODO: Add colors!
					player.addChatMessage(new ChatComponentText("You don't have any profiles!"));
					return;
				}
				
				String msg = new String();
				for(String s : profiles){
					msg = msg.concat(s).concat("\n");
				}
				player.addChatMessage(new ChatComponentText(msg));
				
				return;
			}

			// This is a save command
			if (args[0].equals("save")) {
				if (args.length == 2) {
					if(!InventorySwitcher.saveInventory(player, args[1])) {
						player.addChatMessage(new ChatComponentText("Couldn't save the profile!"));
						return;
					} else {
						player.addChatMessage(new ChatComponentText("Saved the profile!"));
						return;
					}
				} else {
					player.addChatMessage(new ChatComponentText("No specified profile!"));
					printUsage(player);
					return;
				}
			}
			
			// Print usage
			if (args.length > 2) {
				printUsage(player);
				return;
			}
			
			// Swap current inventory to the specified profile inventory profile
			InventorySwitcher.swapInventory(player, args[0]);
			return;
			
		} else {
			sender.addChatMessage(new ChatComponentText("You must be a player to use this command."));
		}
		return;
	}

	/* Additional Overrides */

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		// If the player is in Single Player, this command won't succeed.
		// TODO: Maybe adjust this for permissions on servers. See the EntityPlayer class
		//       for usage restraints.
		return (sender instanceof EntityPlayerMP);
	}
	
	/* Addional Methods */
	private void printUsage(EntityPlayer player) {
		player.addChatMessage(new ChatComponentText(this.getCommandUsage(player)));
	}
}
