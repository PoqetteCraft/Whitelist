package com.github.pocketkid2.whitelist;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WhitelistCommand implements CommandExecutor {

	private WhitelistPlugin plugin;

	public WhitelistCommand(WhitelistPlugin p) {
		plugin = p;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			// Display stuff
		} else if (args.length > 0) {
			if (args[0].equalsIgnoreCase("add")) {
				if (sender.hasPermission("whitelist.add")) {
					if (args.length > 1) {
						String name = args[1];
						OfflinePlayer player = Bukkit.getOfflinePlayer(name);
						plugin.getWhitelistManager().add(player.getUniqueId());
						sender.sendMessage(String.format(Messages.WHITELIST_ADD_SUCCESSFUL, player.getName()));
					} else {
						sender.sendMessage(Messages.NOT_ENOUGH_ARGS);
						return false;
					}
				} else {
					sender.sendMessage(Messages.NO_PERM);
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("whitelist.remove")) {
					if (args.length > 1) {
						String name = args[1];
						OfflinePlayer player = Bukkit.getOfflinePlayer(name);
						plugin.getWhitelistManager().remove(player.getUniqueId());
						sender.sendMessage(String.format(Messages.WHITELIST_REMOVE_SUCCESSFUL, player.getName()));
					} else {
						sender.sendMessage(Messages.NOT_ENOUGH_ARGS);
						return false;
					}
				} else {
					sender.sendMessage(Messages.NO_PERM);
				}
			} else if (args[0].equalsIgnoreCase("info")) {
				if (sender.hasPermission("whitelist.info")) {
					if (args.length > 1) {
						String name = args[1];
						OfflinePlayer player = Bukkit.getOfflinePlayer(name);
						boolean status = plugin.getWhitelistManager().contains(player.getUniqueId());
						if (status) {
							sender.sendMessage(String.format(Messages.PLAYER_ON_WHITELIST, player.getName()));
						} else {
							sender.sendMessage(String.format(Messages.PLAYER_NOT_ON_WHITELIST, player.getName()));
						}
					} else {
						sender.sendMessage(Messages.NOT_ENOUGH_ARGS);
						return false;
					}
				} else {
					sender.sendMessage(Messages.NO_PERM);
				}
			} else if (args[0].equalsIgnoreCase("list")) {
				if (sender.hasPermission("whitelist.list")) {
					List<UUID> list = plugin.getWhitelistManager().all();
					int size = list.size();
					int pages = size / 10;
					if ((size % 10) != 0) {
						pages++;
					}
					int page = 0;

					if (args.length == 1) {
						page = 1;
					} else {
						try {
							int p = Integer.parseInt(args[1]);
							if ((p >= 1) && (p <= pages)) {
								page = p;
							} else {
								sender.sendMessage(String.format(Messages.INVALID_PAGE, 1, pages));
								return true;
							}
						} catch (NumberFormatException e) {
							sender.sendMessage(String.format(Messages.INVALID_ARGUMENT, args[1]));
							return false;
						}
					}

					int start = (page - 1) * 10;
					int end = (start + 10) > (size - 1) ? (size - 1) : (start + 10);
					List<UUID> display = list.subList(start, end);

					sender.sendMessage(String.format(Messages.DISPLAYING_PAGE, page, pages));
					for (UUID id : display) {
						sender.sendMessage(Bukkit.getOfflinePlayer(id).getName());
					}
				} else {
					sender.sendMessage(Messages.NO_PERM);
				}
			} else {
				// Whitelist player argument
				OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
				plugin.getWhitelistManager().add(player.getUniqueId());
				sender.sendMessage(String.format(Messages.WHITELIST_ADD_SUCCESSFUL, player.getName()));
			}
		}
		return true;
	}

}
