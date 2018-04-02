package com.github.pocketkid2.whitelist;

import com.github.pocketkid2.database.Database;
import com.github.pocketkid2.database.DatabasePlugin;

public class WhitelistPlugin extends DatabasePlugin {

	@Override
	public void onEnable() {
		Database.register(this);
		getLogger().info("Done!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Done!");
	}
}
