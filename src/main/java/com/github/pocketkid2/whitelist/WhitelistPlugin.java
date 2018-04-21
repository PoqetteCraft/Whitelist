package com.github.pocketkid2.whitelist;

import org.bukkit.Bukkit;

import com.github.pocketkid2.database.Database;
import com.github.pocketkid2.database.DatabasePlugin;

public class WhitelistPlugin extends DatabasePlugin {

	private WhitelistManager manager;
	private String message;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		message = getConfig().getString("disallow-message");
		Database.register(this);
		manager = new WhitelistManager(this);
		Bukkit.getPluginManager().registerEvents(new WhitelistListener(this), this);
		getLogger().info("Done!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Done!");
	}

	public WhitelistManager getWhitelistManager() {
		return manager;
	}

	public String getMessage() {
		return message;
	}

}
