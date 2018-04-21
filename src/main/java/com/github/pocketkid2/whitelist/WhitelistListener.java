package com.github.pocketkid2.whitelist;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class WhitelistListener implements Listener {

	private WhitelistPlugin plugin;

	public WhitelistListener(WhitelistPlugin p) {
		plugin = p;
	}

	public void onPlayerLogin(AsyncPlayerPreLoginEvent e) {
		UUID id = e.getUniqueId();
		if (plugin.getWhitelistManager().contains(id) || Bukkit.getOfflinePlayer(id).isOp()) {
			e.allow();
		} else {
			e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, plugin.getMessage());
		}
	}

}
