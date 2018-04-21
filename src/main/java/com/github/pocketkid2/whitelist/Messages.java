package com.github.pocketkid2.whitelist;

import net.md_5.bungee.api.ChatColor;

public interface Messages {

	String NO_PERM = ChatColor.RED + "You don't have permission for that!";
	String NOT_ENOUGH_ARGS = ChatColor.RED + "Not enough arguments!";
	String WHITELIST_ADD_SUCCESSFUL = "Successfully whitelisted player %s";
	String WHITELIST_REMOVE_SUCCESSFUL = "Successfully removed %s from the whitelist";
	String PLAYER_ON_WHITELIST = "Player %s is on the whitelist";
	String PLAYER_NOT_ON_WHITELIST = "Player %s is not on the whitelist";
	String INVALID_ARGUMENT = "Invalid argument '%s'";
	String INVALID_PAGE = "Invalid page! Range is %d to %d";
	String DISPLAYING_PAGE = "Displaying page %d of %d";

}
